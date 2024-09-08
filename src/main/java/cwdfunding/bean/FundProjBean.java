package cwdfunding.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "fundingProj")
public class FundProjBean{

	
	@Id @Column(name = "projectID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "startDate")
	private Timestamp startDate;
	
	@Column(name = "endDate")
	private Timestamp endDate;
	
	@Column(name = "targetAmount")
	private String targetAmount;
	
	@Column(name = "currentAmount")
	private String currentAmount;
	
	@Column(name = "threshold")
	private String threshold;
	
	@Column(name = "postponeDate")
	private Timestamp postponeDate;
	
	@Column(name = "category")
	private String category;
	
	public FundProjBean() {
		
	}
	
	public FundProjBean(String title, String description, String image, Timestamp startDate, Timestamp endDate,
			String targetAmount, String currentAmount, String threshold, Timestamp postponeDate, String category) {
		super();
		this.title = title;
		this.description = description;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
		this.threshold = threshold;
		this.postponeDate = postponeDate;
		this.category = category;
	}
	
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(String targetAmount) {
		this.targetAmount = targetAmount;
	}
	public String getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public Timestamp getPostponeDate() {
		return postponeDate;
	}
	public void setPostponeDate(Timestamp postponeDate) {
		this.postponeDate = postponeDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
    // 返回格式化後的日期
    public String getFormattedStartDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(startDate);
    }

    public String getFormattedEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(endDate);
    }
    
    public String getFormattedPostponeDate() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(postponeDate);
    }
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	
}
