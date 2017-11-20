package com.future.membership.mapper;

import com.future.membership.bean.PtDto;
import com.future.membership.bean.PtDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PtDtoMapper {
    int countByExample(PtDtoExample example);

    int deleteByExample(PtDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PtDto record);

    int insertSelective(PtDto record);

    List<PtDto> selectByExample(PtDtoExample example);

    PtDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PtDto record, @Param("example") PtDtoExample example);

    int updateByExample(@Param("record") PtDto record, @Param("example") PtDtoExample example);

    int updateByPrimaryKeySelective(PtDto record);

    int updateByPrimaryKey(PtDto record);
}