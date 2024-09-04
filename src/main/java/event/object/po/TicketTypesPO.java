package event.object.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class TicketTypesPO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ticketTypeID;  // IDENTITY(1,1)
	private Integer sessionID;  // FK, NOT NULL
	private Short ticketTypeNo;  // NOT NULL
	private String typeName;  // NOT NULL
	private String typeDesc;
	private Integer price;  // NOT NULL
	private Integer quantityAvailable;
	private Integer quantityPurchased;
	private Timestamp startSaleTime;  // NOT NULL
	private Timestamp endSaleTime;  // NOT NULL

	public Integer getTicketTypeID() {
		return ticketTypeID;
	}
	public void setTicketTypeID(Integer ticketTypeID) {
		this.ticketTypeID = ticketTypeID;
	}
	public Integer getSessionID() {
		return sessionID;
	}
	public void setSessionID(Integer sessionID) {
		this.sessionID = sessionID;
	}
	public Short getTicketTypeNo() {
		return ticketTypeNo;
	}
	public void setTicketTypeNo(Short ticketTypeNo) {
		this.ticketTypeNo = ticketTypeNo;
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
	public void setQuantityAvailable(Integer totalQuantity) {
		this.quantityAvailable = totalQuantity;
	}
	public Integer getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(Integer quantity) {
		this.quantityPurchased = quantity;
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
		return "TicketTypesPO [ticketTypeID=" + ticketTypeID + ", sessionID=" + sessionID + ", ticketTypeNo="
				+ ticketTypeNo + ", typeName=" + typeName + ", typeDesc=" + typeDesc + ", price=" + price
				+ ", quantityAvailable=" + quantityAvailable + ", quantityPurchased=" + quantityPurchased
				+ ", startSaleTime=" + startSaleTime + ", endSaleTime=" + endSaleTime + "]";
	}
}
