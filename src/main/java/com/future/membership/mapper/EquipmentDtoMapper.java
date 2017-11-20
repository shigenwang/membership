package com.future.membership.mapper;

import com.future.membership.bean.EquipmentDto;
import com.future.membership.bean.EquipmentDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentDtoMapper {
    int countByExample(EquipmentDtoExample example);

    int deleteByExample(EquipmentDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentDto record);

    int insertSelective(EquipmentDto record);

    List<EquipmentDto> selectByExample(EquipmentDtoExample example);

    EquipmentDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentDto record, @Param("example") EquipmentDtoExample example);

    int updateByExample(@Param("record") EquipmentDto record, @Param("example") EquipmentDtoExample example);

    int updateByPrimaryKeySelective(EquipmentDto record);

    int updateByPrimaryKey(EquipmentDto record);
}