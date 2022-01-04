package mc.sn.hospital;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("hospitalVO2")
public class hospitalVO2 {
	private String hp_name;
	private String hp_subject;
	private String hpid;
	private String hp_addr;
	private String hp_tell;
	private List<String> hp_dutyTimeC;
	private List<String> hp_dutyTimeS;
		
	public hospitalVO2() {
		
	}

	public String getHp_name() {
		return hp_name;
	}

	public void setHp_name(String hp_name) {
		this.hp_name = hp_name;
	}

	public String getHp_subject() {
		return hp_subject;
	}

	public void setHp_subject(String hp_subject) {
		this.hp_subject = hp_subject;
	}

	public String getHpid() {
		return hpid;
	}

	public void setHpid(String hpid) {
		this.hpid = hpid;
	}

	public String getHp_addr() {
		return hp_addr;
	}

	public void setHp_addr(String hp_addr) {
		this.hp_addr = hp_addr;
	}

	public String getHp_tell() {
		return hp_tell;
	}

	public void setHp_tell(String hp_tell) {
		this.hp_tell = hp_tell;
	}

	public List<String> getHp_dutyTimeC() {
		return hp_dutyTimeC;
	}

	public void setHp_dutyTimeC(List<String> hp_dutyTimeC) {
		this.hp_dutyTimeC = hp_dutyTimeC;
	}

	public List<String> getHp_dutyTimeS() {
		return hp_dutyTimeS;
	}

	public void setHp_dutyTimeS(List<String> hp_dutyTimeS) {
		this.hp_dutyTimeS = hp_dutyTimeS;
	}

	@Override
	public String toString() {
		return "hospitalVO2 [hp_name=" + hp_name + ", hp_subject=" + hp_subject + ", hpid=" + hpid + ", hp_addr="
				+ hp_addr + ", hp_tell=" + hp_tell + ", hp_dutyTimeC=" + hp_dutyTimeC + ", hp_dutyTimeS=" + hp_dutyTimeS
				+ "]";
	}

	
	
}
