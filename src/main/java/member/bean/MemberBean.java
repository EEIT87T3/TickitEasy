package member.bean;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;


@Entity 
@Table(name = "members")
@Component
public class MemberBean {
	
	@Id
	@Column(name = "memberID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberID;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "nickname")
    private String nickname;
	
	@Column(name = "birthDate")
    private LocalDate birthDate;
	
	@Column(name = "phone")
    private String phone;
	
	@Column(name = "registerDate")
    private LocalDate registerDate;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "profilePic")
    private String profilePic;
	
	@Column(name = "verificationToken")
    private String verificationToken;
	
	/*使用@Transient 可簡化僅在應用程式執行期間才重要的資料管理，並有助於避免不必要的資料庫資源使用，以儲存不需要長期保存的資料。*/
	//預設頭貼不需要存在資料庫故使用Transient
	@Transient
    private static final String DEFAULT_PROFILE_PIC = "/images/default-avatar.png"; // 預設頭貼路徑
	public MemberBean() {
	
	}

	public MemberBean(String email, String password, String name, String nickname, LocalDate birthDate, String phone,
			LocalDate registerDate, String status, String profilePic) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.phone = phone;
		this.registerDate = registerDate;
		this.status = status;
		this.profilePic = profilePic;
	}
	public MemberBean(int memberID, String email, String password, String name, String nickname, LocalDate birthDate,
			String phone, LocalDate registerDate, String status, String profilePic) {
		super();
		this.memberID = memberID;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.phone = phone;
		this.registerDate = registerDate;
		this.status = status;
		this.profilePic = profilePic;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthDateFromSqlDate(Date sqlDate) {
        if (sqlDate != null) {
            this.birthDate = sqlDate.toLocalDate();
        }
    }
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public void setRegisterDateFromSqlDate(Date sqlDate) {
        if (sqlDate != null) {
            this.registerDate = sqlDate.toLocalDate();
        }
    }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	// 會員頭貼
	public String getProfilePic() {
        return (profilePic != null && !profilePic.isEmpty()) ? profilePic : DEFAULT_PROFILE_PIC;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    // 是否有自訂頭貼
    public boolean hasCustomProfilePic() {
        return profilePic != null && !profilePic.isEmpty();
    }
	public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

}
