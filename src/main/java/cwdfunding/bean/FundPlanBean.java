package cwdfunding.bean;

public class FundPlanBean {
	private int planID;
	private int projectID;
	private int unitPrice;
	private String planContent;
	private int totalAmount;
	private int buyAmount;
	
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getPlanContent() {
		return planContent;
	}
	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}

}
