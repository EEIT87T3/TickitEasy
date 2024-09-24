package event.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

@Component
public class TimestampUtil {

	/*
	 * method 名稱：inputStringToTimestamp
	 * 用途：將「2024-08-24T22:00」或「2024-08-24T22:00:00」格式的 datetime 字串轉換為 Timestamp
	 * @param：String
	 * @return：Timestamp
	*/
	public Timestamp inputStringToTimestamp(String inputString) throws DateTimeParseException {
		LocalDateTime localDateTime = null;
		switch (inputString.length()) {
			case 16:
				inputString += ":00";
			case 19:
				localDateTime = LocalDateTime.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
				break;
			default:
				break;
		}
		return Timestamp.valueOf(localDateTime);
	}

	/*
	 * method 名稱：timestampToString
	 * 用途：將 Timestamp 轉換為「2024-08-24T22:00」格式的 datetime 字串
	 * @param：Timestamp
	 * @return：String
	*/
	public String timestampToString(Timestamp timestamp) {
		String rawString = timestamp.toLocalDateTime().toString();
		if (rawString.length() == 19) {
			return rawString.substring(0, 16);
		} else {
			return rawString;
		}
	}

	/*
	 * method 名稱：timestampToIncludeSecondsString
	 * 用途：將 Timestamp 轉換為「2024-08-24T22:00:00」格式的 datetime 字串
	 * @param：Timestamp
	 * @return：String
	*/
	public String timestampToIncludeSecondsString(Timestamp timestamp) {
		String rawString = timestamp.toLocalDateTime().toString();
		if (rawString.length() == 16) {
			return rawString + ":00";
		} else {
			return rawString;
		}
	}

	// 測試用
//	public static void main(String[] args) {
//		String inputString = "2024-08-24T09:47:00";
//		TimestampUtil timestampUtil = new TimestampUtil();
//		try {
//			Timestamp timestamp = timestampUtil.inputStringToTimestamp(inputString);
//			System.out.println("Timestamp 為 " + timestamp);
//
//			String timestampToString = timestampUtil.timestampToString(timestamp);
//			System.out.println("輸出字串為" + timestampToString);
//		} catch (DateTimeParseException e) {
//			System.out.println("發生 DateTimeParseException");
//		}
//	}
}
