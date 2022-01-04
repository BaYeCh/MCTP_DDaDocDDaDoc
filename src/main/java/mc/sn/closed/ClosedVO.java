package mc.sn.closed;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("ClosedVO")
public class ClosedVO {
	private int closedId;
	private String hospitalName;
	private String hospitalAddr;
	private String closedStart;
	private String closedEnd;
	private String writerId;
	
	

	public ClosedVO() {

	}

	public int getClosedId() {
		return closedId;
	}

	public void setClosedId(int closedId) {
		this.closedId = closedId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddr() {
		return hospitalAddr;
	}

	public void setHospitalAddr(String hospitalAddr) {
		this.hospitalAddr = hospitalAddr;
	}	

	public String getClosedStart() {
		return closedStart;
	}

	public void setClosedStart(String closedStart) {
		this.closedStart = closedStart;
	}

	public String getClosedEnd() {
		return closedEnd;
	}

	public void setClosedEnd(String closedEnd) {
		this.closedEnd = closedEnd;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	@Override
	public String toString() {
		return "ClosedVO [closedId=" + closedId + ", hospitalName=" + hospitalName + ", hospitalAddr=" + hospitalAddr
				+ ", closedStart=" + closedStart + ", closedEnd=" + closedEnd + ", writerId=" + writerId + "]";
	}
	
	
	
}
