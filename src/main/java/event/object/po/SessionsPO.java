package event.object.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "sessions")
public class SessionsPO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "sessionID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sessionID;  // IDENTITY(1,1)
	
	@Column(name = "eventID", insertable = false, updatable = false)
	private Integer eventID;  // FK, NOT NULL
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventID")
	private EventsPO event;
	
	@Column(name = "sessionNo", nullable = false)
	private Short sessionNo;  // NOT NULL
	
	@Column(name = "sessionName", nullable = false)
	private String sessionName;  // NOT NULL
	
	@Column(name = "sessionDesc")
	private String sessionDesc;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "address", nullable = false)
	private String address;  // NOT NULL
	
	@Column(name = "sessionStartTime", nullable = false)
	private Timestamp sessionStartTime;  // NOT NULL
	
	@Column(name = "startEntryTime", nullable = false)
	private Timestamp startEntryTime;  // NOT NULL
	
	@Column(name = "endEntryTime", nullable = false)
	private Timestamp endEntryTime;  // NOT NULL
	
	@Column(name = "quantityTotalAvailable", nullable = false)
	private Integer quantityTotalAvailable;  // NOT NULL
	
	@Column(name = "quantityTotalPurchased", nullable = false)
	private Integer quantityTotalPurchased;  // NOT NULL
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "session", cascade = CascadeType.ALL)
	private List<TicketTypesPO> ticketTypes = new ArrayList<TicketTypesPO>(1);

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
	public EventsPO getEvent() {
		return event;
	}
	public void setEvent(EventsPO event) {
		this.event = event;
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
	public List<TicketTypesPO> getTicketTypes() {
		return ticketTypes;
	}
	public void setTicketTypes(List<TicketTypesPO> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}
	
	@Override
	public String toString() {
		return "SessionsPO [sessionID=" + sessionID + ", event=" + event + ", sessionNo=" + sessionNo
				+ ", sessionName=" + sessionName + ", sessionDesc=" + sessionDesc + ", place=" + place + ", address="
				+ address + ", sessionStartTime=" + sessionStartTime + ", startEntryTime=" + startEntryTime
				+ ", endEntryTime=" + endEntryTime + ", quantityTotalAvailable=" + quantityTotalAvailable
				+ ", quantityTotalPurchased=" + quantityTotalPurchased + "]";
	}
}
