package mc.sn.login;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	private String id;
	private String pwd;
	private String tel;
	private String name;
	private String email;
	private String birth;
	private String nickname;
	private String address1;
	private String address2;
	private String address3;
	private int admin;
	
	
	
	public MemberVO() {
	}



	public MemberVO(String id, String pwd, String tel, String name, String email, String birth, String nickname,
			String address1, String address2, String address3) {
		this.id = id;
		this.pwd = pwd;
		this.tel = tel;
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.nickname = nickname;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getBirth() {
		return birth;
	}



	public void setBirth(String birth) {
		this.birth = birth;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getAddress3() {
		return address3;
	}



	public void setAddress3(String address3) {
		this.address3 = address3;
	}



	public int getAdmin() {
		return admin;
	}



	public void setAdmin(int admin) {
		this.admin = admin;
	}



	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", tel=" + tel + ", name=" + name + ", email=" + email
				+ ", birth=" + birth + ", nickname=" + nickname + ", address1=" + address1 + ", address2=" + address2
				+ ", address3=" + address3 + ", admin=" + admin + "]";
	}

	
	
}
