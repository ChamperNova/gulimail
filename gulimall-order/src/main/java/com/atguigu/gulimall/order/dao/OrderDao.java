package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author sss
 * @email sss@gmail.com
 * @date 2021-07-29 11:29:08
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
