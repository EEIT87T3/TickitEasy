package event.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {

	/*
	 * method 名稱：createEvent
	 * 用途：新增活動的實際執行 Service
	 * @param：EventDTO（使用者輸入資料）
	 * @return：boolean（false 表示發生 SQLException）
	 * 備註：生成自 ChatGPT
	 * Caller：event.dao.CreateEventDAO
	*/
	public void setIntegerOrNull(PreparedStatement ps, int parameterIndex, Integer value) throws SQLException {
	    if (value == null) {
	        ps.setNull(parameterIndex, java.sql.Types.INTEGER);
	    } else {
	        ps.setInt(parameterIndex, value);
	    }
	}
}
