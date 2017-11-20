package com.future.membership.mapper;

import com.future.membership.bean.TeacherDto;
import com.future.membership.bean.TeacherDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherDtoMapper {
    int countByExample(TeacherDtoExample example);

    int deleteByExample(TeacherDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TeacherDto record);

    int insertSelective(TeacherDto record);

    List<TeacherDto> selectByExample(TeacherDtoExample example);

    TeacherDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TeacherDto record, @Param("example") TeacherDtoExample example);

    int updateByExample(@Param("record") TeacherDto record, @Param("example") TeacherDtoExample example);

    int updateByPrimaryKeySelective(TeacherDto record);

    int updateByPrimaryKey(TeacherDto record);
}