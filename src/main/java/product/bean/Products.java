package product.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity @Table(name="products")
public class Products implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @Column(name = "productID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productID;
	
	@Column(name = "category")
    private String category;
	
	@Column(name = "productName")
    private String productName;
	
	@Column(name = "productPic")
    private String productPic;
	
	@Column(name = "productDesc")
    private String productDesc;
	
	@Column(name = "price")
    private int price;
	
	@Column(name = "stock")
    private int stock;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "prodTotalReviews")
    private int prodTotalReviews;
	
	@Column(name = "prodTotalScore")
    private int prodTotalScore;

    public Products() {
        super();
    }

    public Products(int productID, String category, String productName, String productPic, String productDesc,
			int price, int stock, String status, int prodTotalReviews, int prodTotalScore) {
		super();
		this.productID = productID;
		this.category = category;
		this.productName = productName;
		this.productPic = productPic;
		this.productDesc = productDesc;
		this.price = price;
		this.stock = stock;
		this.status = status;
		this.prodTotalReviews = prodTotalReviews;
		this.prodTotalScore = prodTotalScore;
	}



	public Products(String category, String productName, String productPic, String productDesc, int price, int stock,
			String status, int prodTotalReviews, int prodTotalScore) {
		super();
		this.category = category;
		this.productName = productName;
		this.productPic = productPic;
		this.productDesc = productDesc;
		this.price = price;
		this.stock = stock;
		this.status = status;
		this.prodTotalReviews = prodTotalReviews;
		this.prodTotalScore = prodTotalScore;
	}



	public int getProdTotalReviews() {
		return prodTotalReviews;
	}

	public void setProdTotalReviews(int prodTotalReviews) {
		this.prodTotalReviews = prodTotalReviews;
	}

	public int getProdTotalScore() {
		return prodTotalScore;
	}

	public void setProdTotalScore(int prodTotalScore) {
		this.prodTotalScore = prodTotalScore;
	}

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
