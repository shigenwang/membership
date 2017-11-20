package com.future.membership.mapper;

import com.future.membership.bean.MaintainDto;
import com.future.membership.bean.MaintainDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaintainDtoMapper {
    int countByExample(MaintainDtoExample example);

    int deleteByExample(MaintainDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MaintainDto record);

    int insertSelective(MaintainDto record);

    List<MaintainDto> selectByExample(MaintainDtoExample example);

    MaintainDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MaintainDto record, @Param("example") MaintainDtoExample example);

    int updateByExample(@Param("record") MaintainDto record, @Param("example") MaintainDtoExample example);

    int updateByPrimaryKeySelective(MaintainDto record);

    int updateByPrimaryKey(MaintainDto record);
}