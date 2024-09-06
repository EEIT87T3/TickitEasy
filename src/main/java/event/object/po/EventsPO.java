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

@Entity @Table(name = "events")
public class EventsPO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "eventID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventID;  // IDENTITY(1,1)

	@Column(name = "eventName", unique = true, nullable = false)
	private String eventName;  // UNIQUE, NOT NULL

	@Column(name = "eventPic")
	private String eventPic;

//	private String eventType;  // FK, NOT NULL
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventType")
	private EventTypesPO eventType;

	@Column(name = "eventDesc")
	private String eventDesc;

	@Column(name = "earliestSessionTime")
	private Timestamp earliestSessionTime;

	@Column(name = "latestSessionTime")
	private Timestamp latestSessionTime;

	@Column(name = "totalReviews")
	private Integer totalReviews;

	@Column(name = "totalScore")
	private Integer totalScore;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
	private List<SessionsPO> sessions = new ArrayList<>(1);

	public Integer getEventID() {
		return eventID;
	}
	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventPic() {
		return eventPic;
	}
	public void setEventPic(String eventPic) {
		this.eventPic = eventPic;
	}
	public EventTypesPO getEventType() {
		return eventType;
	}
	public void setEventType(EventTypesPO eventType) {
		this.eventType = eventType;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public Timestamp getEarliestSessionTime() {
		return earliestSessionTime;
	}
	public void setEarliestSessionTime(Timestamp earliestSessionTime) {
		this.earliestSessionTime = earliestSessionTime;
	}
	public Timestamp getLatestSessionTime() {
		return latestSessionTime;
	}
	public void setLatestSessionTime(Timestamp latestSessionTime) {
		this.latestSessionTime = latestSessionTime;
	}
	public Integer getTotalReviews() {
		return totalReviews;
	}
	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public List<SessionsPO> getSessions() {
		return sessions;
	}
	public void setSessions(List<SessionsPO> sessions) {
		this.sessions = sessions;
	}
	
	@Override
	public String toString() {
		return "EventsPO [eventID=" + eventID + ", eventName=" + eventName + ", eventPic=" + eventPic + ", eventType="
				+ eventType + ", eventDesc=" + eventDesc + ", earliestSessionTime=" + earliestSessionTime
				+ ", latestSessionTime=" + latestSessionTime + ", totalReviews=" + totalReviews + ", totalScore="
				+ totalScore + "]";
	}
}
