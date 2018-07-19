package com.fjd.ssm.mapper;




import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.fjd.ssm.core.Member;

@Component
@Mapper
public interface MemberMapper {
	Member selectMemberByName(@Param("name")String name)throws Exception;
}
