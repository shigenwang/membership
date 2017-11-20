package com.future.membership.mapper;

import com.future.membership.bean.CardDto;
import com.future.membership.bean.CardDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardDtoMapper {
    int countByExample(CardDtoExample example);

    int deleteByExample(CardDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardDto record);

    int insertSelective(CardDto record);

    List<CardDto> selectByExample(CardDtoExample example);

    CardDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardDto record, @Param("example") CardDtoExample example);

    int updateByExample(@Param("record") CardDto record, @Param("example") CardDtoExample example);

    int updateByPrimaryKeySelective(CardDto record);

    int updateByPrimaryKey(CardDto record);
}