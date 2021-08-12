package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author sss
 * @email sss@gmail.com
 * @date 2021-07-29 11:05:47
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
