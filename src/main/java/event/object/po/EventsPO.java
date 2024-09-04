package event.object.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class EventsPO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer eventID;  // IDENTITY(1,1)
	private String eventName;  // UNIQUE, NOT NULL
	private String eventPic;
	private String eventType;  // FK, NOT NULL
	private String eventDesc;
	private Timestamp earliestSessionTime;
	private Timestamp latestSessionTime;
	private Integer totalReviews;
	private Integer totalScore;
	
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
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
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
	
	@Override
	public String toString() {
		return "EventsPO [eventID=" + eventID + ", eventName=" + eventName + ", eventPic=" + eventPic + ", eventType="
				+ eventType + ", eventDesc=" + eventDesc + ", earliestSessionTime=" + earliestSessionTime
				+ ", latestSessionTime=" + latestSessionTime + ", totalReviews=" + totalReviews + ", totalScore="
				+ totalScore + "]";
	}
}
