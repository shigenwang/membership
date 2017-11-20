package com.future.membership.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.future.membership.bean.UserDto;
import com.future.membership.bean.UserDtoExample;

public interface UserDtoMapper {
    int countByExample(UserDtoExample example);

    int deleteByExample(UserDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDto record);

    int insertSelective(UserDto record);

    List<UserDto> selectByExample(UserDtoExample example);

    UserDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDto record, @Param("example") UserDtoExample example);

    int updateByExample(@Param("record") UserDto record, @Param("example") UserDtoExample example);

    int updateByPrimaryKeySelective(UserDto record);

    int updateByPrimaryKey(UserDto record);
}