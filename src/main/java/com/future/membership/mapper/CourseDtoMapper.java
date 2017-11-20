package com.future.membership.mapper;

import com.future.membership.bean.CourseDto;
import com.future.membership.bean.CourseDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseDtoMapper {
    int countByExample(CourseDtoExample example);

    int deleteByExample(CourseDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseDto record);

    int insertSelective(CourseDto record);

    List<CourseDto> selectByExample(CourseDtoExample example);

    CourseDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseDto record, @Param("example") CourseDtoExample example);

    int updateByExample(@Param("record") CourseDto record, @Param("example") CourseDtoExample example);

    int updateByPrimaryKeySelective(CourseDto record);

    int updateByPrimaryKey(CourseDto record);
}