package mc.sn.find;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.sn.login.MemberVO;

@Repository("findDAO")
public class findDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public String selectByNameEmail(String name, String email, String birth) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("name",name);
		map.put("email",email);
		map.put("birth",birth);
		MemberVO vo = sqlSession.selectOne("mapper.member.selectByNameEmail",map);
		String result = "";
		if(vo!=null) {
			result = vo.getId();
		}
		return result;
	}

	public String selectByIdEmail(String id, String email, String birth) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("id",id);
		map.put("email",email);
		map.put("birth",birth);
		MemberVO vo = sqlSession.selectOne("mapper.member.selectByIdEmail",map);
		String result = "";
		if(vo!=null) {
			result = vo.getPwd();
		}
		return result;
	}
}
