package com.future.membership.mapper;

import com.future.membership.bean.GymDto;
import com.future.membership.bean.GymDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GymDtoMapper {
    int countByExample(GymDtoExample example);

    int deleteByExample(GymDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GymDto record);

    int insertSelective(GymDto record);

    List<GymDto> selectByExample(GymDtoExample example);

    GymDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GymDto record, @Param("example") GymDtoExample example);

    int updateByExample(@Param("record") GymDto record, @Param("example") GymDtoExample example);

    int updateByPrimaryKeySelective(GymDto record);

    int updateByPrimaryKey(GymDto record);
}