package product.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    //private Integer totalCount;
    //private Integer totalPrice;

    private Map<Integer,CartItem> items = new LinkedHashMap<>();
//
//	public void addItem(CartItem cartItem) {
//
//			CartItem item = items.get(cartItem.getId());
//			if(item==null) {
//				items.put(cartItem.getId(),cartItem);
//			}else {
//				item.setCount(item.getCount()+1);
//				item.setTotalPrice(item.getPrice()*((item.getCount())));
//			}
//	    }
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());  // 設置初始總金額
            items.put(cartItem.getId(), cartItem);
        } else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice() * item.getCount());
        }
    }

    public void deleteItem(Integer id) {
		items.remove(id);
	}

    public void clear() {
		items.clear();
    }

    public void updateCount(Integer id,Integer count) {
    	CartItem cartItem = items.get(id);
    	if(cartItem!=null) {
    		cartItem.setCount(count);
    		cartItem.setTotalPrice(cartItem.getPrice()*cartItem.getCount());
    	}
    }

	public Integer getTotalCount() {
		int totalCount = 0;

		for(Map.Entry<Integer, CartItem>entry:items.entrySet()) {
			totalCount += entry.getValue().getCount();
		}

		return totalCount;
	}

	public int getTotalPrice() {
		int totalPrice = 0;

		for(Map.Entry<Integer, CartItem>entry:items.entrySet()) {
			totalPrice += entry.getValue().getTotalPrice();
		}
		return totalPrice;
	}



	public Map<Integer, CartItem> getItems() {
		return items;
	}

	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}


    public Cart(Integer totalCount, int totalPrice, Map<Integer, CartItem> items) {
    	super();
//		this.totalCount = totalCount;
//		this.totalPrice = totalPrice;
    	this.items = items;
    }

	public Cart() {
		super();
	}


	@Override
	public String toString() {
		return "Cart [totalCount=" + getTotalCount() +
				", totalPrice=" + getTotalPrice() +
				", items=" + items + "]";
	}


}

