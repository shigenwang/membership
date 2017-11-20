package com.future.membership.mapper;

import com.future.membership.bean.GymEquipmentDto;
import com.future.membership.bean.GymEquipmentDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GymEquipmentDtoMapper {
    int countByExample(GymEquipmentDtoExample example);

    int deleteByExample(GymEquipmentDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GymEquipmentDto record);

    int insertSelective(GymEquipmentDto record);

    List<GymEquipmentDto> selectByExample(GymEquipmentDtoExample example);

    GymEquipmentDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GymEquipmentDto record, @Param("example") GymEquipmentDtoExample example);

    int updateByExample(@Param("record") GymEquipmentDto record, @Param("example") GymEquipmentDtoExample example);

    int updateByPrimaryKeySelective(GymEquipmentDto record);

    int updateByPrimaryKey(GymEquipmentDto record);
}