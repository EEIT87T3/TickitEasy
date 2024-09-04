package event.object.dto.updateevent;

import java.io.Serializable;
import java.sql.Timestamp;

public class OneTicketTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer ticketTypeID;
	private String eventName;
	private String sessionName;
	private String typeName;
	private String typeDesc;
	private Integer price;
	private Integer quantityAvailable;
	private Integer quantityPurchased;
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
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
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
	public Integer getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(Integer quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
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
		return "OneTicketTypeDTO [ticketTypeID=" + ticketTypeID + ", eventName=" + eventName + ", sessionName="
				+ sessionName + ", typeName=" + typeName + ", typeDesc=" + typeDesc + ", price=" + price
				+ ", quantityAvailable=" + quantityAvailable + ", quantityPurchased=" + quantityPurchased
				+ ", startSaleTime=" + startSaleTime + ", endSaleTime=" + endSaleTime + "]";
	}
}
