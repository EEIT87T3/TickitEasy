package member.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import member.bean.MemberBean;
import util.ConnectionUtil;

public class MemberDAO {
	 private static final Logger LOGGER = Logger.getLogger(MemberDAO.class.getName());
	// 根據電子郵件查詢會員
	public MemberBean getMemberByEmail(String email) {
		String sql = "SELECT * FROM members WHERE email = ?";
		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return extractMemberFromResultSet(rs);// 將 ResultSet 轉換成 MemberBean
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 添加新會員
	public int addMember(MemberBean member) {
        String sql = "INSERT INTO members (email, password, name, nickname, birthDate, phone, registerDate, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {// 生成自增主鍵
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getNickname());
            pstmt.setDate(5, Date.valueOf(member.getBirthDate()));
            pstmt.setString(6, member.getPhone());
            pstmt.setDate(7, Date.valueOf(LocalDate.now()));
            pstmt.setString(8, member.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating member failed, no rows affected.");// 沒有影響任何資料
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {//獲取生成的鍵（ID）如果成功，返回這個 ID；否則拋出異常。
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating member failed, no ID obtained.");// 沒有取得 ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;//如果一切正常，方法會返回新插入記錄的 ID。如果發生異常，則返回 -1。
    }
	 

	// 更新會員資料
	public void updateMember(MemberBean member) {
		String sql = "UPDATE members SET name = ?, nickname = ?, birthDate = ?, phone = ?, profilePic = ? WHERE memberID = ?";
		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getNickname());
			pstmt.setDate(3, member.getBirthDate() != null ? Date.valueOf(member.getBirthDate()) : null);
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getProfilePic());
			pstmt.setInt(6, member.getMemberID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 移除頭像
	public void removeProfilePic(int memberId) {
		String sql = "UPDATE members SET profilePic = NULL WHERE memberID = ?";
		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//根據會員ID取得會員
	public MemberBean getMemberById(int memberID) {
		String sql = "SELECT * FROM members WHERE memberID = ?";
		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memberID);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return extractMemberFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 取得所有會員
	public List<MemberBean> getAllMembers() {
		List<MemberBean> members = new ArrayList<>();
		String sql = "SELECT * FROM members";
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				members.add(extractMemberFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	// 將 ResultSet 轉換成 MemberBean
	private MemberBean extractMemberFromResultSet(ResultSet rs) throws SQLException {
		MemberBean member = new MemberBean();
		member.setMemberID(rs.getInt("memberID"));
		member.setEmail(rs.getString("email"));
		member.setPassword(rs.getString("password"));
		member.setName(rs.getString("name"));
		member.setNickname(rs.getString("nickname"));
		member.setBirthDateFromSqlDate(rs.getDate("birthDate"));
		member.setPhone(rs.getString("phone"));
		member.setRegisterDateFromSqlDate(rs.getDate("registerDate"));
		member.setStatus(rs.getString("status"));
		member.setProfilePic(rs.getString("profilePic"));
		return member;
	}

	// 修改狀態
	public void updateMemberStatus(int memberId, String newStatus) {
		String sql = "UPDATE members SET status = ? WHERE memberID = ?";
		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newStatus);
			pstmt.setInt(2, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 取得過去6個月內的會員註冊數
	public Map<String, Integer> getRegistrationTrend() {
        Map<String, Integer> trend = new HashMap<>();
        String sql = "SELECT DATEPART(MONTH, registerDate) as month, COUNT(*) as count " +
                     "FROM members " +
                     "WHERE registerDate >= DATEADD(MONTH, -6, GETDATE()) " +
                     "GROUP BY DATEPART(MONTH, registerDate) " +
                     "ORDER BY DATEPART(MONTH, registerDate)";//查詢選出過去六個月內，會員在每個月份的註冊人數
        
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                trend.put(rs.getString("month"), rs.getInt("count"));//將每個月份的註冊人數存入Map
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trend;//最後返回這個 Map<String, Integer>，包含過去六個月的會員註冊數趨勢。
    }
	

}

