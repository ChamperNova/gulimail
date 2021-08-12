package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    //    @Autowired
    //    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {

        List<CategoryEntity> entities = baseMapper.selectList(null);

        List<CategoryEntity> level1Menus = entities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildrens(categoryEntity,entities));
                    return categoryEntity;})
                .sorted((o1, o2) -> {
                    return (o1.getSort()==null?0:o1.getSort()) - (o2.getSort()==null?0:o2.getSort());
                })
                .collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO  1、检查当前删除的菜单，是否被别的地方引用

        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    // 找出root节点的所有子节点，并递归地对所有子节点进行setChildren，
    // 希望返回的是已经对其每个子节点setChildren好的root的子节点们
    private List<CategoryEntity> getChildrens(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> list = all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildrens(categoryEntity,all));
                    return categoryEntity;})
                .sorted((o1, o2) -> {
                    return (o1.getSort()==null?0:o1.getSort()) - (o2.getSort()==null?0:o2.getSort());
                }).collect(Collectors.toList());
        return list;
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        CategoryEntity entity = this.getById(catelogId);
        List<Long> path = new ArrayList<>();
        findParentPath(catelogId,path);

        Collections.reverse(path);

        return path.toArray(new Long[1]);
    }

    private void findParentPath(Long catelogId,List<Long> path){
        path.add(catelogId);
        CategoryEntity entity = this.getById(catelogId);
        if(entity.getParentCid()!=0){
            findParentPath(entity.getParentCid(),path);
        }
    }

}
