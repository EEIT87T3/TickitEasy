package order.bean;

import java.sql.Date;

public class ProdOrderDetailsBean {

//封裝prodOrderDetails的數據
	int prodOrderDetailID;
    int prodOrderID;
    int productID;
    int price;
    int quantity;
    String content;
    Date reviewTime;
    int score;
    //配合購物車
    public ProdOrderDetailsBean(int productID, int price, int quantity) {
		super();
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
	}
   //配合購物車
	public ProdOrderDetailsBean(int prodOrderID, int productID, int price, int quantity) {
		super();
		this.prodOrderID = prodOrderID;
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
	}
	//不含prodOrderDetailID
    public ProdOrderDetailsBean(int prodOrderID, int productID, int price, int quantity, String content,
			Date reviewTime, int score) {
		super();
		this.prodOrderID = prodOrderID;
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
		this.content = content;
		this.reviewTime = reviewTime;
		this.score = score;
	}
	//含prodOrderDetailID
	public ProdOrderDetailsBean(int prodOrderDetailID, int prodOrderID, int productID, int price, int quantity,
			String content, Date reviewTime, int score) {
		super();
		this.prodOrderDetailID = prodOrderDetailID;
		this.prodOrderID = prodOrderID;
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
		this.content = content;
		this.reviewTime = reviewTime;
		this.score = score;
	}
	public int getProdOrderDetailID() {
		return prodOrderDetailID;
	}
	public void setProdOrderDetailID(int prodOrderDetailID) {
		this.prodOrderDetailID = prodOrderDetailID;
	}
	public int getProdOrderID() {
		return prodOrderID;
	}
	public void setProdOrderID(int prodOrderID) {
		this.prodOrderID = prodOrderID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "ProdOrderDetailsBean [prodOrderDetailID=" + prodOrderDetailID + ", prodOrderID=" + prodOrderID
				+ ", productID=" + productID + ", price=" + price + ", quantity=" + quantity + ", content=" + content
				+ ", reviewTime=" + reviewTime + ", score=" + score + "]" + "\n";
	}


}
