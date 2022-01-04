package mc.sn.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.sn.login.MemberVO;

@Repository("adminDAO")
public class adminDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public MemberVO selectById(String id) {
		// TODO Auto-generated method stub
		MemberVO vo = sqlSession.selectOne("mapper.member.selectById",id);
		return vo;
	}

	public boolean update(MemberVO vo) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int a = sqlSession.update("mapper.member.updateById",vo);
		if(a>0) {
			flag=true;
		}
		return flag;
	}

	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		List<MemberVO> memberList = new ArrayList<MemberVO>();		
		memberList = sqlSession.selectList("mapper.member.selectAll");
		for(MemberVO vo : memberList) {
			System.out.println(vo);
		}
		return memberList;
	}

	public boolean deleteMember(String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int a = sqlSession.delete("mapper.member.deleteById",id);
		if(a>0) {
			flag=true;
		}
		return flag;
	}
	
	public boolean updateToAdmin(String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int a = sqlSession.update("mapper.member.updateToAdmin",id);
		if(a>0) {
			flag=true;
		}
		return flag;
	}
	
	public boolean updateToMember(String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int a = sqlSession.update("mapper.member.updateToMember",id);
		if(a>0) {
			flag=true;
		}
		return flag;
	}
}
