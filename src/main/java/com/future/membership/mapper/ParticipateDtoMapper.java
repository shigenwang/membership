package com.future.membership.mapper;

import com.future.membership.bean.ParticipateDto;
import com.future.membership.bean.ParticipateDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParticipateDtoMapper {
    int countByExample(ParticipateDtoExample example);

    int deleteByExample(ParticipateDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ParticipateDto record);

    int insertSelective(ParticipateDto record);

    List<ParticipateDto> selectByExample(ParticipateDtoExample example);

    ParticipateDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ParticipateDto record, @Param("example") ParticipateDtoExample example);

    int updateByExample(@Param("record") ParticipateDto record, @Param("example") ParticipateDtoExample example);

    int updateByPrimaryKeySelective(ParticipateDto record);

    int updateByPrimaryKey(ParticipateDto record);
}