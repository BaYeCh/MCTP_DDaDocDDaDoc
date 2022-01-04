package mc.sn.hospital;

import org.springframework.stereotype.Component;

@Component("evalVO")
public class evalVO {
	private String title;
	private String writer;
	private String hosp_name;
	private String location;
	private String content;
	private String hpid;
		
	public evalVO() {

	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getHosp_name() {
		return hosp_name;
	}
	public void setHosp_name(String hosp_name) {
		this.hosp_name = hosp_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getHpid() {
		return hpid;
	}
	public void setHpid(String hpid) {
		this.hpid = hpid;
	}
	@Override
	public String toString() {
		return "evalVO [title=" + title + ", writer=" + writer + ", hosp_name=" + hosp_name + ", location=" + location
				+ ", content=" + content + ", hpid=" + hpid + "]";
	}
	
}
