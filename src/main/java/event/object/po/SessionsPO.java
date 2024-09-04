package event.object.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class SessionsPO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer sessionID;  // IDENTITY(1,1)
	private Integer eventID;  // FK, NOT NULL
	private Short sessionNo;  // NOT NULL
	private String sessionName;  // NOT NULL
	private String sessionDesc;
	private String place;
	private String address;  // NOT NULL
	private Timestamp sessionStartTime;  // NOT NULL
	private Timestamp startEntryTime;  // NOT NULL
	private Timestamp endEntryTime;  // NOT NULL
	private Integer quantityTotalAvailable;  // NOT NULL
	private Integer quantityTotalPurchased;  // NOT NULL
	
	public Integer getSessionID() {
		return sessionID;
	}
	public void setSessionID(Integer sessionID) {
		this.sessionID = sessionID;
	}
	public Integer getEventID() {
		return eventID;
	}
	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}
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
	public Integer getQuantityTotalAvailable() {
		return quantityTotalAvailable;
	}
	public void setQuantityTotalAvailable(Integer quantityTotalAvailable) {
		this.quantityTotalAvailable = quantityTotalAvailable;
	}
	public Integer getQuantityTotalPurchased() {
		return quantityTotalPurchased;
	}
	public void setQuantityTotalPurchased(Integer quantityTotalPurchased) {
		this.quantityTotalPurchased = quantityTotalPurchased;
	}
	
	@Override
	public String toString() {
		return "SessionsPO [sessionID=" + sessionID + ", eventID=" + eventID + ", sessionNo=" + sessionNo
				+ ", sessionName=" + sessionName + ", sessionDesc=" + sessionDesc + ", place=" + place + ", address="
				+ address + ", sessionStartTime=" + sessionStartTime + ", startEntryTime=" + startEntryTime
				+ ", endEntryTime=" + endEntryTime + ", quantityTotalAvailable=" + quantityTotalAvailable
				+ ", quantityTotalPurchased=" + quantityTotalPurchased + "]";
	}
}
