package board.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	public boolean isMember(String id);
	
}
