package event.object.po;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "ticketTypes")
public class TicketTypesPO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "ticketTypeID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketTypeID;  // IDENTITY(1,1)
	
//	private Integer sessionID;  // FK, NOT NULL
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sessionID")
	private SessionsPO session;
	
	@Column(name = "ticketTypeNo", nullable = false)
	private Short ticketTypeNo;  // NOT NULL
	
	@Column(name = "typeName", nullable = false)
	private String typeName;  // NOT NULL
	
	@Column(name = "typeDesc")
	private String typeDesc;
	
	@Column(name = "price", nullable = false)
	private Integer price;  // NOT NULL
	
	@Column(name = "quantityAvailable")
	private Integer quantityAvailable;
	
	@Column(name = "quantityPurchased")
	private Integer quantityPurchased;
	
	@Column(name = "startSaleTime", nullable = false)
	private Timestamp startSaleTime;  // NOT NULL
	
	@Column(name = "endSaleTime", nullable = false)
	private Timestamp endSaleTime;  // NOT NULL

	public Integer getTicketTypeID() {
		return ticketTypeID;
	}
	public void setTicketTypeID(Integer ticketTypeID) {
		this.ticketTypeID = ticketTypeID;
	}
	public SessionsPO getSession() {
		return session;
	}
	public void setSession(SessionsPO session) {
		this.session = session;
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
		return "TicketTypesPO [ticketTypeID=" + ticketTypeID + ", session=" + session + ", ticketTypeNo="
				+ ticketTypeNo + ", typeName=" + typeName + ", typeDesc=" + typeDesc + ", price=" + price
				+ ", quantityAvailable=" + quantityAvailable + ", quantityPurchased=" + quantityPurchased
				+ ", startSaleTime=" + startSaleTime + ", endSaleTime=" + endSaleTime + "]";
	}
}
