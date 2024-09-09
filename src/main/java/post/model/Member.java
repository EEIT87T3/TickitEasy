package post.model;

import java.time.LocalDate;
import java.sql.Date;
import jakarta.persistence.*; // 引入Hibernate相關註解
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "members") // 將該類映射到資料庫中的 "members" 表
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動生成主鍵
    @Column(name = "memberID")
    private int memberID;

    @Column(name = "email", nullable = false, unique = true) // Email不能為空且必須唯一
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @CreationTimestamp // 註解用來自動填充日期
    @Column(name = "registerDate", updatable = false)
    private LocalDate registerDate;

    @Column(name = "status")
    private String status;

    @Column(name = "profilePic")
    private String profilePic;

    @Column(name = "verificationToken")
    private String verificationToken;

    private static final String DEFAULT_PROFILE_PIC = "/images/default-avatar.png"; // 預設頭貼路徑

    // Constructors, getters, and setters ...

    public Member() {
        super();
    }

    public Member(String email, String password, String name, String nickname, LocalDate birthDate, String phone,
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

    // Getters and Setters...

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

    // 剩餘的 Getters 和 Setters...
}
