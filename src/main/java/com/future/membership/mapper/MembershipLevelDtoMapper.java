package com.future.membership.mapper;

import com.future.membership.bean.MembershipLevelDto;
import com.future.membership.bean.MembershipLevelDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MembershipLevelDtoMapper {
    int countByExample(MembershipLevelDtoExample example);

    int deleteByExample(MembershipLevelDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MembershipLevelDto record);

    int insertSelective(MembershipLevelDto record);

    List<MembershipLevelDto> selectByExample(MembershipLevelDtoExample example);

    MembershipLevelDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MembershipLevelDto record, @Param("example") MembershipLevelDtoExample example);

    int updateByExample(@Param("record") MembershipLevelDto record, @Param("example") MembershipLevelDtoExample example);

    int updateByPrimaryKeySelective(MembershipLevelDto record);

    int updateByPrimaryKey(MembershipLevelDto record);
}