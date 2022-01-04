package mc.sn.hospital;

import org.springframework.stereotype.Component;

@Component("hospitalVO")
public class hospitalVO {
	private String lang;
	private String lat;
	private String hp_name;
	private String hp_subject;
	private String hp_diagTime;	
	private String hpid;
	private int week_day;
	private String hp_addr;
	private String hp_tell;
	private String hp_dutyTime1;
	private String hp_dutyTime2;
	private String week_day_str;
	private String message;
	private String distance;
		
	public hospitalVO() {
		
	}

	public String getLang() {
		return lang;
	}



	public void setLang(String lang) {
		this.lang = lang;
	}



	public String getLat() {
		return lat;
	}



	public void setLat(String lat) {
		this.lat = lat;
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



	public String getHp_diagTime() {
		return hp_diagTime;
	}



	public void setHp_diagTime(String hp_diagTime) {
		this.hp_diagTime = hp_diagTime;
	}



	public String getHpid() {
		return hpid;
	}



	public void setHpid(String hpid) {
		this.hpid = hpid;
	}



	public int getWeek_day() {
		return week_day;
	}



	public void setWeek_day(int week_day) {
		this.week_day = week_day;
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



	public String getHp_dutyTime1() {
		return hp_dutyTime1;
	}



	public void setHp_dutyTime1(String hp_dutyTime1) {
		this.hp_dutyTime1 = hp_dutyTime1;
	}



	public String getHp_dutyTime2() {
		return hp_dutyTime2;
	}



	public void setHp_dutyTime2(String hp_dutyTime2) {
		this.hp_dutyTime2 = hp_dutyTime2;
	}



	public String getWeek_day_str() {
		return week_day_str;
	}



	public void setWeek_day_str(String week_day_str) {
		this.week_day_str = week_day_str;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public String getDistance() {
		return distance;
	}


	public void setDistance(String distance) {
		this.distance = distance;
	}


	@Override
	public String toString() {
		return "hospitalVO [lang=" + lang + ", lat=" + lat + ", hp_name=" + hp_name + ", hp_subject=" + hp_subject
				+ ", hp_diagTime=" + hp_diagTime + ", hpid=" + hpid + ", week_day=" + week_day + ", hp_addr=" + hp_addr
				+ ", hp_tell=" + hp_tell + ", hp_dutyTime1=" + hp_dutyTime1 + ", hp_dutyTime2=" + hp_dutyTime2
				+ ", week_day_str=" + week_day_str + ", message=" + message + ", distance=" + distance + "]";
	}
	
}
