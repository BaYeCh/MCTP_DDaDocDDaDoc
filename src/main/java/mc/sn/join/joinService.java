package mc.sn.join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.sn.login.MemberVO;

@Service("joinService")
public class joinService {
	@Autowired
	private joinDAO dao;
	
	public boolean addMember(MemberVO member) {
		boolean flag = false;
		flag = dao.insertMember(member);
		return flag;
	}
	public boolean findMemberId(String id) {
		boolean flag = false;
		flag = dao.selectMemberId(id);
		return flag;
	}
	public boolean findMemberEmail(String email) {
		boolean flag = false;
		flag = dao.selectMemberEmail(email);
		return flag;
	}
	public boolean findMemberSubName(String nickname) {
		boolean flag = false;
		flag = dao.selectMemberNickName(nickname);
		return flag;
	}
}
