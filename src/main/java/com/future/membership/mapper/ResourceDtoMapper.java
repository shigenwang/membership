package com.future.membership.mapper;

import com.future.membership.bean.ResourceDto;
import com.future.membership.bean.ResourceDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceDtoMapper {
    int countByExample(ResourceDtoExample example);

    int deleteByExample(ResourceDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceDto record);

    int insertSelective(ResourceDto record);

    List<ResourceDto> selectByExample(ResourceDtoExample example);

    ResourceDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResourceDto record, @Param("example") ResourceDtoExample example);

    int updateByExample(@Param("record") ResourceDto record, @Param("example") ResourceDtoExample example);

    int updateByPrimaryKeySelective(ResourceDto record);

    int updateByPrimaryKey(ResourceDto record);
}