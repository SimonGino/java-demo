package com.example.demo.metainfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.metainfo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper 接口
 *
 * @author wupx
 * @since 2020-12-30
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}