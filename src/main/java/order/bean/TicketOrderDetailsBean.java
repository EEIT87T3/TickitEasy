package order.bean;

import java.sql.Date;

public class TicketOrderDetailsBean {

		int	ticketOrderDetailID;
	    int ticketOrderID;
	    int ticketTypeID;
	    String ticketCollectionMethod;
	    int price;
	    String ticketUUID;
	    int ticketStatus;
	    String content;
	    Date reviewTime;
	    int score;
	    
	    
	    public TicketOrderDetailsBean(int ticketOrderDetailID, int ticketOrderID, int ticketTypeID,
				String ticketCollectionMethod, int price, String ticketUUID, int ticketStatus, String content,
				Date reviewTime, int score) {
			super();
			this.ticketOrderDetailID = ticketOrderDetailID;
			this.ticketOrderID = ticketOrderID;
			this.ticketTypeID = ticketTypeID;
			this.ticketCollectionMethod = ticketCollectionMethod;
			this.price = price;
			this.ticketUUID = ticketUUID;
			this.ticketStatus = ticketStatus;
			this.content = content;
			this.reviewTime = reviewTime;
			this.score = score;
		}
	    
		public int getTicketOrderDetailID() {
			return ticketOrderDetailID;
		}
		public void setTicketOrderDetailID(int ticketOrderDetailID) {
			this.ticketOrderDetailID = ticketOrderDetailID;
		}
		public int getTicketOrderID() {
			return ticketOrderID;
		}
		public void setTicketOrderID(int ticketOrderID) {
			this.ticketOrderID = ticketOrderID;
		}
		public int getTicketTypeID() {
			return ticketTypeID;
		}
		public void setTicketTypeID(int ticketTypeID) {
			this.ticketTypeID = ticketTypeID;
		}
		public String getTicketCollectionMethod() {
			return ticketCollectionMethod;
		}
		public void setTicketCollectionMethod(String ticketCollectionMethod) {
			this.ticketCollectionMethod = ticketCollectionMethod;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getTicketUUID() {
			return ticketUUID;
		}
		public void setTicketUUID(String ticketUUID) {
			this.ticketUUID = ticketUUID;
		}
		public int getTicketStatus() {
			return ticketStatus;
		}
		public void setTicketStatus(int ticketStatus) {
			this.ticketStatus = ticketStatus;
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
			return "TicketOrderDetailsBean [ticketOrderDetailID=" + ticketOrderDetailID + ", ticketOrderID="
					+ ticketOrderID + ", ticketTypeID=" + ticketTypeID + ", ticketCollectionMethod="
					+ ticketCollectionMethod + ", price=" + price + ", ticketUUID=" + ticketUUID + ", ticketStatus="
					+ ticketStatus + ", content=" + content + ", reviewTime=" + reviewTime + ", score=" + score + "]";
		}
		
	  
	    
}
