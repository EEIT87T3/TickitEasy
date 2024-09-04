package event.object.dto.createevent;

import java.io.Serializable;
import java.sql.Timestamp;

public class TicketTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Short ticketTypeNo;  // NOT NULL
	private String typeName;  // NOT NULL
	private String typeDesc;
	private Integer price;  // NOT NULL
	private Integer quantityAvailable;
	private Timestamp startSaleTime;  // NOT NULL
	private Timestamp endSaleTime;  // NOT NULL
	
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
		return "TicketTypeDTO [ticketTypeNo=" + ticketTypeNo + ", typeName=" + typeName + ", typeDesc=" + typeDesc
				+ ", price=" + price + ", totalQuantity=" + quantityAvailable + ", startSaleTime=" + startSaleTime
				+ ", endSaleTime=" + endSaleTime + "]";
	}
}
