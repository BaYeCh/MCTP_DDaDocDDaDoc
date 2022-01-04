package mc.sn.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("findService")
public class findService {
	@Autowired
	private findDAO dao;

	public String findId(String name, String email,String birth) {
		// TODO Auto-generated method stub
		String result = dao.selectByNameEmail(name,email,birth);
		return result;
	}
	
	public String findPwd(String name, String email,String birth) {
		// TODO Auto-generated method stub
		String result = dao.selectByIdEmail(name,email,birth);
		return result;
	}
}
