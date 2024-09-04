package event.object.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReadAllTicketTypesVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer ticketTypeID;
	private String eventName;
	private String sessionName;
	private String typeName;
	private Integer price;
	private Integer quantityAvailable;
	private Timestamp startSaleTime;
	private Timestamp endSaleTime;
	
	public Integer getTicketTypeID() {
		return ticketTypeID;
	}
	public void setTicketTypeID(Integer ticketTypeID) {
		this.ticketTypeID = ticketTypeID;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}
	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public Timestamp getStartSaleTime() {
		return startSaleTime;
	}
	public void setStartSaleTime(Timestamp startSaleTime) {
		this.startSaleTime = startSaleTime;
	}
	public Timestamp getEndSaleTime() {
		return endSaleTime;
	}
	public void setEndSaleTime(Timestamp endSaleTime) {
		this.endSaleTime = endSaleTime;
	}
	
	@Override
	public String toString() {
		return "ReadAllTicketTypesVO [ticketTypeID=" + ticketTypeID + ", eventName=" + eventName + ", sessionName="
				+ sessionName + ", typeName=" + typeName + ", price=" + price + ", quantityAvailable="
				+ quantityAvailable + ", startSaleTime=" + startSaleTime + ", endSaleTime=" + endSaleTime + "]";
	}
}
