package event.object.po;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "eventTypes")
public class EventTypesPO {

	@Id @Column(name = "eventType")
	private String eventType;
	
	@Column(name = "eventName")
	private String eventName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventType", cascade = CascadeType.ALL)
	private Set<EventsPO> events = new HashSet<EventsPO>();

	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Set<EventsPO> getEvents() {
		return events;
	}
	public void setEvents(Set<EventsPO> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "EventTypesPO [eventType=" + eventType + ", eventName=" + eventName + "]";
	}
}
