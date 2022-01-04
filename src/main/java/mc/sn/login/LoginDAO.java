package mc.sn.login;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("loginDAO")
public class LoginDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean selectUser(String id, String pwd) {
		boolean flag = false; 
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		MemberVO vo = sqlSession.selectOne("mapper.member.loginByIdPwd",map);
		if(vo!=null) {
			//System.out.println(vo.getName());
			flag = true;
		}
		return flag;
	}
}
