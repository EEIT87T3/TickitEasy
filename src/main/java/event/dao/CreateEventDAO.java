package event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import event.object.po.EventsPO;
import event.object.po.SessionsPO;
import event.object.po.TicketTypesPO;
import event.util.SQLUtil;
import util.ConnectionUtil;

public class CreateEventDAO {

	/*
	 * method 名稱：createEvent
	 * 用途：新增活動的實際執行 DAO
	 * @param：EventsPO, List<SessionsPO>, List<TicketTypesPO>
	 * @return：（無）
	 * Caller：event.service.CreateEventService createEvent()
	*/
	public void createEvent(EventsPO eventsPO, Map<Short, SessionsPO> sessionsPOMap, List<TicketTypesPO> ticketTypesPOList) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SQLUtil sqlUtil = new SQLUtil();
		
		try {
			connection = ConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			
			// 1. events
			// 1-1. INSERT events
			String sqlInsertEvents = """
					INSERT INTO events(eventName, eventPic, eventType, eventDesc, earliestSessionTime, latestSessionTime, totalReviews, totalScore)
						VALUES(?, ?, ?, ?, ?, ?, ?, ?);
					""";
			preparedStatement = connection.prepareStatement(sqlInsertEvents);
			preparedStatement.setString(1, eventsPO.getEventName());
			preparedStatement.setString(2, eventsPO.getEventPic());
			preparedStatement.setString(3, eventsPO.getEventType());
			preparedStatement.setString(4, eventsPO.getEventDesc());
			preparedStatement.setTimestamp(5, eventsPO.getEarliestSessionTime());
			preparedStatement.setTimestamp(6, eventsPO.getLatestSessionTime());
			preparedStatement.setInt(7, eventsPO.getTotalReviews());
			preparedStatement.setInt(8, eventsPO.getTotalScore());
			preparedStatement.executeUpdate();
			
			// 1-2. SELECT events eventID
			String sqlSelectEventsId = """
					SELECT eventID
					FROM events
					WHERE eventName = ?
					""";
			preparedStatement = connection.prepareStatement(sqlSelectEventsId);
			preparedStatement.setString(1, eventsPO.getEventName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				eventsPO.setEventID(resultSet.getInt("eventID"));
			}
			
			
			// 2. sessions
			String sqlInsertSessions = """
					INSERT INTO sessions(eventID, sessionNo, sessionName, sessionDesc, place, address, sessionStartTime, startEntryTime, endEntryTime, quantityTotalAvailable, quantityTotalPurchased)
						VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
					""";
			String sqlSelectSessionsId = """
					SELECT sessionID
					FROM sessions
					WHERE eventID = ? AND sessionNo = ?
					""";
			Collection<SessionsPO> sessionsPOMapValues = sessionsPOMap.values();
			for (SessionsPO sessionsPO : sessionsPOMapValues) {
				// 2-1. INSERT sessions
				preparedStatement = connection.prepareStatement(sqlInsertSessions);
				sessionsPO.setEventID(eventsPO.getEventID());
				preparedStatement.setInt(1, sessionsPO.getEventID());
				preparedStatement.setShort(2, sessionsPO.getSessionNo());
				preparedStatement.setString(3, sessionsPO.getSessionName());
				preparedStatement.setString(4, sessionsPO.getSessionDesc());
				preparedStatement.setString(5, sessionsPO.getPlace());
				preparedStatement.setString(6, sessionsPO.getAddress());
				preparedStatement.setTimestamp(7, sessionsPO.getSessionStartTime());
				preparedStatement.setTimestamp(8, sessionsPO.getStartEntryTime());
				preparedStatement.setTimestamp(9, sessionsPO.getEndEntryTime());
				preparedStatement.setInt(10, sessionsPO.getQuantityTotalAvailable());
				preparedStatement.setInt(11, sessionsPO.getQuantityTotalPurchased());
				preparedStatement.executeUpdate();
				
				// 2-2. SELECT sessions sessionID
				preparedStatement = connection.prepareStatement(sqlSelectSessionsId);
				preparedStatement.setInt(1, sessionsPO.getEventID());
				preparedStatement.setShort(2, sessionsPO.getSessionNo());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					sessionsPO.setSessionID(resultSet.getInt("sessionID"));
				}
			}
			
			
			// 3. ticketTypes
			// 3.1. INSERT ticketTypes
			String sqlInsertTicketTypes = """
					INSERT INTO ticketTypes(sessionID, ticketTypeNo, typeName, typeDesc, price, quantityAvailable, quantityPurchased, startSaleTime, endSaleTime)
						VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
					""";
			preparedStatement = connection.prepareStatement(sqlInsertTicketTypes);
			for (TicketTypesPO ticketTypesPO : ticketTypesPOList) {
				preparedStatement.setInt(1, sessionsPOMap.get((short) Math.abs(ticketTypesPO.getSessionID())).getSessionID());
				preparedStatement.setShort(2, ticketTypesPO.getTicketTypeNo());
				preparedStatement.setString(3, ticketTypesPO.getTypeName());
				preparedStatement.setString(4, ticketTypesPO.getTypeDesc());
				preparedStatement.setInt(5, ticketTypesPO.getPrice());
				sqlUtil.setIntegerOrNull(preparedStatement, 6, ticketTypesPO.getQuantityAvailable());
				sqlUtil.setIntegerOrNull(preparedStatement, 7, ticketTypesPO.getQuantityPurchased());
				preparedStatement.setTimestamp(8, ticketTypesPO.getStartSaleTime());
				preparedStatement.setTimestamp(9, ticketTypesPO.getEndSaleTime());
				preparedStatement.executeUpdate();
			}
			
			connection.commit();
		} catch (SQLException e) {
	        connection.rollback();
		    throw e;
		} finally {
			connection.setAutoCommit(true);
			ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
		}
	}
}
