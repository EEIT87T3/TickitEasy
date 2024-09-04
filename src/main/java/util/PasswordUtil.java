package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	// 密碼加密
	public static String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());//BCrypt.gensalt() 生成一個隨機的鹽值
		//BCrypt.hashpw() 方法將明文密碼和鹽值結合，生成加密後的密碼哈希值
	}
	
	//密碼驗證
	public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
		return BCrypt.checkpw(plainTextPassword, hashedPassword);//接收參數：明文密碼和已加密的密碼哈希值
	}//使用 BCrypt.checkpw() 方法來比較明文密碼和加密後的密碼哈希值
}
	