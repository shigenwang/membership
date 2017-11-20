package com.future.membership.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.future.membership.bean.HistoryDto;
import com.future.membership.bean.HistoryDtoExample;

public interface HistoryDtoMapper {
    int countByExample(HistoryDtoExample example);

    int deleteByExample(HistoryDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HistoryDto record);

    int insertSelective(HistoryDto record);

    List<HistoryDto> selectByExample(HistoryDtoExample example);

    HistoryDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HistoryDto record, @Param("example") HistoryDtoExample example);

    int updateByExample(@Param("record") HistoryDto record, @Param("example") HistoryDtoExample example);

    int updateByPrimaryKeySelective(HistoryDto record);

    int updateByPrimaryKey(HistoryDto record);
}