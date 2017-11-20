package com.future.membership.mapper;

import com.future.membership.bean.EquipmentTypeDto;
import com.future.membership.bean.EquipmentTypeDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentTypeDtoMapper {
    int countByExample(EquipmentTypeDtoExample example);

    int deleteByExample(EquipmentTypeDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentTypeDto record);

    int insertSelective(EquipmentTypeDto record);

    List<EquipmentTypeDto> selectByExample(EquipmentTypeDtoExample example);

    EquipmentTypeDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentTypeDto record, @Param("example") EquipmentTypeDtoExample example);

    int updateByExample(@Param("record") EquipmentTypeDto record, @Param("example") EquipmentTypeDtoExample example);

    int updateByPrimaryKeySelective(EquipmentTypeDto record);

    int updateByPrimaryKey(EquipmentTypeDto record);
}