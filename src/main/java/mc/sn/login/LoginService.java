package mc.sn.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginService {
	@Autowired
	private LoginDAO loginDAO;

	public boolean checkLogin(String id, String pwd) {
		boolean flag = loginDAO.selectUser(id, pwd);
		return flag;
	}
}
