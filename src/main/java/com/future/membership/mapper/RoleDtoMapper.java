package com.future.membership.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.future.membership.bean.RoleDto;
import com.future.membership.bean.RoleDtoExample;

public interface RoleDtoMapper {
    int countByExample(RoleDtoExample example);

    int deleteByExample(RoleDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleDto record);

    int insertSelective(RoleDto record);

    List<RoleDto> selectByExample(RoleDtoExample example);

    RoleDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleDto record, @Param("example") RoleDtoExample example);

    int updateByExample(@Param("record") RoleDto record, @Param("example") RoleDtoExample example);

    int updateByPrimaryKeySelective(RoleDto record);

    int updateByPrimaryKey(RoleDto record);
}