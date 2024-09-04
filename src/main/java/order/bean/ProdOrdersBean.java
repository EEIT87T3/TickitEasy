package order.bean;

import java.sql.Date;

//封裝prodOrder的數據
public class ProdOrdersBean {
	int prodOrderID; //自動生成 PK
	int memberID; //FK
	Date orderDate;
	String payments;
	String paymenInfo;
	String status;
	int totalAmount;
	String shippingStatus;
	int shippingID;
	String recipientName;
	String address;
	String phone;

	//不含prodOrderID
	public ProdOrdersBean(int memberID, Date orderDate, String payments, String paymenInfo, String status,
			int totalAmount, String shippingStatus, int shippingID, String recipientName, String address,
			String phone) {
		super();
		this.memberID = memberID;
		this.orderDate = orderDate;
		this.payments = payments;
		this.paymenInfo = paymenInfo;
		this.status = status;
		this.totalAmount = totalAmount;
		this.shippingStatus = shippingStatus;
		this.shippingID = shippingID;
		this.recipientName = recipientName;
		this.address = address;
		this.phone = phone;
	}
	//含prodOrderID
	public ProdOrdersBean(int prodOrderID,int memberID, Date orderDate, String payments, String paymenInfo, String status,
			int totalAmount, String shippingStatus, int shippingID, String recipientName, String address,
			String phone) {
		super();
		this.prodOrderID = prodOrderID;
		this.memberID = memberID;
		this.orderDate = orderDate;
		this.payments = payments;
		this.paymenInfo = paymenInfo;
		this.status = status;
		this.totalAmount = totalAmount;
		this.shippingStatus = shippingStatus;
		this.shippingID = shippingID;
		this.recipientName = recipientName;
		this.address = address;
		this.phone = phone;
	}
	//含prodOrderID set get
	public int getProdOrderID() {
		return prodOrderID;
	}
	public void setProdOrderID(int prodOrderID) {
		this.prodOrderID = prodOrderID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberlD(int memberID) {
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
	public String getPaymenInfo() {
		return paymenInfo;
	}
	public void setPaymenInfo(String paymenInfo) {
		this.paymenInfo = paymenInfo;
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
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public int getShippingID() {
		return shippingID;
	}
	public void setShippingID(int shippingID) {
		this.shippingID = shippingID;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "ProductOrderBean [prodOrderID=" + prodOrderID + ", memberID=" + memberID + ", orderDate=" + orderDate
				+ ", payments=" + payments + ", paymenInfo=" + paymenInfo + ", status=" + status + ", totalAmount="
				+ totalAmount + ", shippingStatus=" + shippingStatus + ", shippingID=" + shippingID + ", recipientName="
				+ recipientName + ", address=" + address + ", phone=" + phone + "]" + "\n";
	}
	
	
	
}
