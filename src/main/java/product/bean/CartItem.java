package product.bean;

import java.io.Serializable;

public class CartItem implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer count;
	private Integer price;
	private Integer totalPrice;
	
	public CartItem() {
		super();
	}
	public CartItem(Integer id, String name, Integer count, Integer price, Integer totalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
		this.totalPrice = totalPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getTotalPrice() {
		return count*price;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", count=" + count + ", price=" + price + ", totalPrice="
				+ totalPrice + "]";
	}
	
}
