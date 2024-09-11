package order.bean;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//封裝ticketOrder的數據
@Entity @Table(name = "tickedOrders")
public class TicketOrdersBean {
	
	@Id
	@Column(name = "tickedOrderID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int tickedOrderID; //自動生成 PK
	
	@Column(name = "memberID")
	int memberID; //FK
	
	@Column(name = "orderDate")
	Date orderDate;
	
	@Column(name = "payments")
	String payments;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "totalAmount")
	int totalAmount;

	//不含tickedOrderID
	public TicketOrdersBean(int memberID, Date orderDate, String payments, String status, int totalAmount) {
		super();
		this.memberID = memberID;
		this.orderDate = orderDate;
		this.payments = payments;
		this.status = status;
		this.totalAmount = totalAmount;
	}
	//含tickedOrderID
	public TicketOrdersBean(int tickedOrderID,int memberID, Date orderDate, String payments, String status, int totalAmount) {
		super();
		this.tickedOrderID = tickedOrderID;
		this.memberID = memberID;
		this.orderDate = orderDate;
		this.payments = payments;
		this.status = status;
		this.totalAmount = totalAmount;
	}
	//含tickedOrderID set get
	public int getTickedOrderID() {
		return tickedOrderID;
	}
	public void setTickedOrderID(int tickedOrderID) {
		this.tickedOrderID = tickedOrderID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPayments() {
		return payments;
	}
	public void setPayments(String payments) {
		this.payments = payments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "TicketOrderBean [tickedOrderID=" + tickedOrderID + ", memberID=" + memberID + ", orderDate=" + orderDate
				+ ", payments=" + payments + ", status=" + status + ", totalAmount=" + totalAmount + "]" + "\n";
	}


}
