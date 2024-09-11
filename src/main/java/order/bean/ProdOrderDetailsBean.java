package order.bean;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity @Table(name = "prodOrderDetails")
public class ProdOrderDetailsBean {

//封裝prodOrderDetails的數據
	@Id @Column
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int prodOrderDetailID;
	
	@ManyToOne
	@JoinColumn(name = "prodOrderID", referencedColumnName = "prodOrderID")
	ProdOrdersBean prodOrderID;
	
	@Column(name = "productID")
    int productID;
    
    @Column(name = "price")
    int price;

	@Column(name = "quantity")
    int quantity;
    
    @Column(name = "content")
    String content;
    
    @Column(name = "reviewTime")
    Date reviewTime;
    
    @Column(name = "score")
    int score;
    public ProdOrderDetailsBean() {
    	
    }
    
     public ProdOrderDetailsBean(int prodOrderDetailID, ProdOrdersBean prodOrderID, int productID, int price,
			int quantity, String content, Date reviewTime, int score) {
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
		this.prodOrderID.setProdOrderID(prodOrderID);
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
	}
	//不含prodOrderDetailID
    public ProdOrderDetailsBean(int prodOrderID, int productID, int price, int quantity, String content,
			Date reviewTime, int score) {
		super();
		this.prodOrderID.setProdOrderID(prodOrderID);
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
		this.prodOrderID.setProdOrderID(prodOrderID);
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
	public ProdOrdersBean getProdOrderID() {
		return prodOrderID;
	}
	public void setProdOrderID(int prodOrderID) {
		this.prodOrderID.setProdOrderID(prodOrderID);
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
