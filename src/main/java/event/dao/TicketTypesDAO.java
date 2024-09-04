package event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import event.object.dto.updateevent.OneTicketTypeDTO;
import event.object.po.TicketTypesPO;
import event.object.vo.ReadAllTicketTypesVO;
import event.util.SQLUtil;
import util.ConnectionUtil;

public class TicketTypesDAO {

	/*
	 * method 名稱：readAll
	 * 用途：查詢所有票種的實際執行 DAO
	 * @param：（無）
	 * @return：List<ReadAllTicketTypesVO>
	 * Caller：webapp/event/ReadAllTicketTypes.jsp
	*/
	public List<ReadAllTicketTypesVO> readAll() {
		List<ReadAllTicketTypesVO> ticketTypeslist = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sqlSelectAll = """
				SELECT t.ticketTypeID, e.eventName, s.sessionName, t.typeName, t.price, t.quantityAvailable, t.startSaleTime, t.endSaleTime
				FROM ticketTypes AS t
					JOIN sessions AS s ON t.sessionID = s.sessionID
					JOIN events AS e ON s.eventID = e.eventID
				""";
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlSelectAll);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ReadAllTicketTypesVO readAllTicketTypesVO = new ReadAllTicketTypesVO();
				readAllTicketTypesVO.setTicketTypeID(resultSet.getInt("ticketTypeID"));
				readAllTicketTypesVO.setEventName(resultSet.getString("eventName"));
				readAllTicketTypesVO.setSessionName(resultSet.getString("sessionName"));
				readAllTicketTypesVO.setTypeName(resultSet.getString("typeName"));
				readAllTicketTypesVO.setPrice(resultSet.getInt("price"));
				// quantityAvailable 可能為 NULL
				Integer quantityAvailable = resultSet.getInt("quantityAvailable");
				readAllTicketTypesVO.setQuantityAvailable(resultSet.wasNull() ? null : quantityAvailable);
				readAllTicketTypesVO.setStartSaleTime(resultSet.getTimestamp("startSaleTime"));
				readAllTicketTypesVO.setEndSaleTime(resultSet.getTimestamp("endSaleTime"));

				ticketTypeslist.add(readAllTicketTypesVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
		}

		return ticketTypeslist;
	}

	/*
	 * method 名稱：selectOneTicketTypeById
	 * 用途：以 ID 查詢單一票種
	 * @param：Integer（ticketTypeID）
	 * @return：OneTicketTypeDTO
	 * Caller：java.event.controller.ReadOneTicketType doGet()
	*/
	public OneTicketTypeDTO selectOneTicketTypeById(int ticketTypeId) {
		OneTicketTypeDTO oneTicketTypeDTO = new OneTicketTypeDTO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sqlSelectOneById = """
				SELECT t.ticketTypeID, e.eventName, s.sessionName, t.typeName, t.typeDesc, t.price, t.quantityAvailable, t.quantityPurchased, t.startSaleTime, t.endSaleTime
				FROM ticketTypes AS t
					JOIN sessions AS s ON t.sessionID = s.sessionID
					JOIN events AS e ON s.eventID = e.eventID
				WHERE t.ticketTypeID = ?
				""";
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlSelectOneById);
			preparedStatement.setInt(1, ticketTypeId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				oneTicketTypeDTO.setTicketTypeID(resultSet.getInt("ticketTypeID"));
				oneTicketTypeDTO.setEventName(resultSet.getString("eventName"));
				oneTicketTypeDTO.setSessionName(resultSet.getString("sessionName"));
				oneTicketTypeDTO.setTypeName(resultSet.getString("typeName"));
				oneTicketTypeDTO.setTypeDesc(resultSet.getString("typeDesc"));
				oneTicketTypeDTO.setPrice(resultSet.getInt("price"));
				Integer quantityAvailable = resultSet.getInt("quantityAvailable");
				oneTicketTypeDTO.setQuantityAvailable(resultSet.wasNull() ? null : quantityAvailable);
				Integer quantityPurchased = resultSet.getInt("quantityPurchased");
				oneTicketTypeDTO.setQuantityPurchased(resultSet.wasNull() ? null : quantityPurchased);
				oneTicketTypeDTO.setStartSaleTime(resultSet.getTimestamp("startSaleTime"));
				oneTicketTypeDTO.setEndSaleTime(resultSet.getTimestamp("endSaleTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
		}
		return oneTicketTypeDTO;
	}

	/*
	 * method 名稱：selectIfOnlyTicketTypeByDTO
	 * 用途：以 ID 查詢該票種是否為該場次的唯一票種
	 * @param：OneTicketTypeDTO（要查詢的票種）
	 * @return：Boolean
	 * Caller：java.event.controller.ReadOneTicketType doGet()
	*/
	public Boolean selectIfOnlyTicketTypeByDTO(OneTicketTypeDTO oneTicketTypeDTO) {
		Boolean result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sqlSelectOneById = """
				SELECT COUNT(t.ticketTypeID) AS count
				FROM ticketTypes AS t
					JOIN sessions AS s ON t.sessionID = s.sessionID
					JOIN events AS e ON s.eventID = e.eventID
				WHERE e.eventName = ? AND s.sessionName = ?
				""";
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlSelectOneById);
			preparedStatement.setString(1, oneTicketTypeDTO.getEventName());
			preparedStatement.setString(2, oneTicketTypeDTO.getSessionName());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getInt("count") > 1) {
					result = false;
				} else {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection, preparedStatement, resultSet);
		}
		return result;
	}

	/*
	 * method 名稱：deleteTicketTypeById
	 * 用途：以 ID 刪除單一票種的資料庫互動
	 * @param：Integer（ticketTypeID）
	 * @return：Boolean
	 * Caller：webapp/event/DeleteTicketType doPost()
	*/
	public Boolean deleteTicketTypeById(Integer ticketTypeID) {
		Boolean result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sqlSelectOneById = """
				DELETE FROM ticketTypes
				WHERE ticketTypeID = ?
				""";
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlSelectOneById);
			preparedStatement.setInt(1, ticketTypeID);
			int executeUpdate = preparedStatement.executeUpdate();

			if (executeUpdate == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(connection, preparedStatement);
		}
		return result;
	}

	/*
	 * method 名稱：updateTicketTypeById
	 * 用途：以 ID 修改票種得資料庫互動
	 * @param：TicketTypesPO
	 * @return：Boolean（執行結果）
	 * Caller：java.event.service.UpdateTicketTypeService updateTicketType()
	 * 備註：throws SQLException
	*/
	public Boolean updateTicketTypeById(TicketTypesPO ticketTypesPO) throws SQLException {
		Boolean result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		SQLUtil sqlUtil = new SQLUtil();

		try {
			connection =  ConnectionUtil.getConnection();
			String sqlUpdateTicketType = """
					UPDATE ticketTypes
					SET typeName = ?, typeDesc = ?, price = ?, quantityAvailable = ?, quantityPurchased = ?, startSaleTime = ?, endSaleTime = ?
					WHERE ticketTypeID = ?
					""";
			preparedStatement = connection.prepareStatement(sqlUpdateTicketType);
			preparedStatement.setInt(8, ticketTypesPO.getTicketTypeID());
			preparedStatement.setString(1, ticketTypesPO.getTypeName());
			preparedStatement.setString(2, ticketTypesPO.getTypeDesc());
			preparedStatement.setInt(3, ticketTypesPO.getPrice());
			sqlUtil.setIntegerOrNull(preparedStatement, 4, ticketTypesPO.getQuantityAvailable());
			preparedStatement.setInt(5, ticketTypesPO.getQuantityPurchased());
			preparedStatement.setTimestamp(6, ticketTypesPO.getStartSaleTime());
			preparedStatement.setTimestamp(7, ticketTypesPO.getEndSaleTime());
			int executeUpdate = preparedStatement.executeUpdate();

			if (executeUpdate == 1) {
				result = true;
			} else {
				result = false;
			}
		} finally {
			ConnectionUtil.closeResource(connection, preparedStatement);
		}
		return result;
	}
}
