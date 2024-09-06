package event.object.dto.createevent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String eventName;  // UNIQUE, NOT NULL
	private String eventPic;
	private String eventType;  // FK, NOT NULL
	private String eventDesc;
	private List<SessionDTO> sessionList = new ArrayList<>();

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
	public List<SessionDTO> getSessionList() {
		return sessionList;
	}
	public void setSessionList(List<SessionDTO> sessionList) {
		this.sessionList = sessionList;
	}

	@Override
	public String toString() {
		return "EventDTO [eventName=" + eventName + ", eventPic=" + eventPic + ", eventType=" + eventType
				+ ", eventDesc=" + eventDesc + ", sessionList=" + sessionList + "]";
	}
}
