package com.future.membership.mapper;

import com.future.membership.bean.MembershipDto;
import com.future.membership.bean.MembershipDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MembershipDtoMapper {
    int countByExample(MembershipDtoExample example);

    int deleteByExample(MembershipDtoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MembershipDto record);

    int insertSelective(MembershipDto record);

    List<MembershipDto> selectByExample(MembershipDtoExample example);

    MembershipDto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MembershipDto record, @Param("example") MembershipDtoExample example);

    int updateByExample(@Param("record") MembershipDto record, @Param("example") MembershipDtoExample example);

    int updateByPrimaryKeySelective(MembershipDto record);

    int updateByPrimaryKey(MembershipDto record);
}