package event.object.dto.createevent;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SessionDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Short sessionNo;  // NOT NULL
	private String sessionName;  // NOT NULL
	private String sessionDesc;
	private String place;
	private String address;  // NOT NULL
	private Integer quantityTotalAvailable;  // NOT NULL
	private Timestamp sessionStartTime;  // NOT NULL
	private Timestamp startEntryTime;  // NOT NULL
	private Timestamp endEntryTime;  // NOT NULL
	private List<TicketTypeDTO> ticketTypeList = new ArrayList<>();

	public Short getSessionNo() {
		return sessionNo;
	}
	public void setSessionNo(Short sessionNo) {
		this.sessionNo = sessionNo;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getSessionDesc() {
		return sessionDesc;
	}
	public void setSessionDesc(String sessionDesc) {
		this.sessionDesc = sessionDesc;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getQuantityTotalAvailable() {
		return quantityTotalAvailable;
	}
	public void setQuantityTotalAvailable(Integer quantityTotalAvailable) {
		this.quantityTotalAvailable = quantityTotalAvailable;
	}
	public Timestamp getSessionStartTime() {
		return sessionStartTime;
	}
	public void setSessionStartTime(Timestamp sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public Timestamp getStartEntryTime() {
		return startEntryTime;
	}
	public void setStartEntryTime(Timestamp startEntryTime) {
		this.startEntryTime = startEntryTime;
	}
	public Timestamp getEndEntryTime() {
		return endEntryTime;
	}
	public void setEndEntryTime(Timestamp endEntryTime) {
		this.endEntryTime = endEntryTime;
	}
	public List<TicketTypeDTO> getTicketTypeList() {
		return ticketTypeList;
	}
	public void setTicketTypeList(List<TicketTypeDTO> ticketTypeList) {
		this.ticketTypeList = ticketTypeList;
	}

	@Override
	public String toString() {
		return "SessionDTO [sessionNo=" + sessionNo + ", sessionName=" + sessionName + ", sessionDesc=" + sessionDesc
				+ ", place=" + place + ", address=" + address + ", quantityTotalAvailable=" + quantityTotalAvailable
				+ ", sessionStartTime=" + sessionStartTime + ", startEntryTime=" + startEntryTime + ", endEntryTime="
				+ endEntryTime + ", ticketTypeList=" + ticketTypeList + "]";
	}
}
