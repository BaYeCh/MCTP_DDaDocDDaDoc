package mc.sn.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.sn.login.MemberVO;

@Service("adminService")
public class adminService {
	
	@Autowired
	private adminDAO dao;
	
	public MemberVO findById(String id) {
		// TODO Auto-generated method stub
		MemberVO vo = dao.selectById(id);
		return vo;
	}

	public boolean change(MemberVO vo) {
		// TODO Auto-generated method stub
		boolean flag = dao.update(vo);
		return flag;
	}

	public List<MemberVO> searchAll() {
		// TODO Auto-generated method stub
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		memberList = dao.selectAll();
		return memberList;
	}

	public boolean memberDelete(String id) {
		// TODO Auto-generated method stub
		boolean flag = dao.deleteMember(id);
		return flag;
	}
	
	public boolean updateToAdmin(String id) {
		// TODO Auto-generated method stub
		boolean flag = dao.updateToAdmin(id);
		return flag;
	}
	
	public boolean updateToMember(String id) {
		// TODO Auto-generated method stub
		boolean flag = dao.updateToMember(id);
		return flag;
	}

}
