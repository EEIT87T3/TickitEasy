package event.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import event.dao.CreateEventDAO;
import event.object.dto.createevent.EventDTO;
import event.object.dto.createevent.SessionDTO;
import event.object.dto.createevent.TicketTypeDTO;
import event.object.po.EventsPO;
import event.object.po.SessionsPO;
import event.object.po.TicketTypesPO;

public class CreateEventService {
	
	/*
	 * method 名稱：validate
	 * 用途：驗證使用者輸入資料
	 * @param：EventDTO（使用者輸入資料）
	 * @return：String（「""」表示通過驗證，或是錯誤訊息）
	*/
	public String validate(EventDTO eventDTO) {
		if (eventDTO.getEventName() == null || eventDTO.getEventName().isBlank()) {
			return "活動名稱不可為空白。";
		}
		if (eventDTO.getEventType() == null || eventDTO.getEventType().isBlank()) {
			return "活動類型不可為空白。";
		}
		for (SessionDTO sessionDTO : eventDTO.getSessionList()) {
			if (sessionDTO.getSessionName() == null || sessionDTO.getSessionName().isBlank()) {
				return "場次名稱不可為空白。";
			}
			if (sessionDTO.getAddress() == null || sessionDTO.getAddress().isBlank()) {
				return "場地地址不可為空白。";
			}
			if (sessionDTO.getQuantityTotalAvailable() == null) {
				return "開放購買總數量不可為空白。";
			}
			if (sessionDTO.getQuantityTotalAvailable() <= 0) {
				return "開放購買總數量不可小於或等於零。";
			}
			if (sessionDTO.getSessionStartTime() == null) {
				return "場次開始時間不可為空白。";
			}
			if (sessionDTO.getStartEntryTime() == null) {
				return "場次開始入場時間不可為空白。";
			}
			if (sessionDTO.getEndEntryTime() == null) {
				return "場次結束入場時間不可為空白。";
			}
			for (TicketTypeDTO ticketTypeDTO : sessionDTO.getTicketTypeList()) {
				if (ticketTypeDTO.getTypeName() == null || ticketTypeDTO.getTypeName().isBlank()) {
					return "票種名稱不可為空白。";
				}
				if (ticketTypeDTO.getPrice() == null) {
					return "票種價格不可為空白。";
				}
				if (ticketTypeDTO.getPrice() < 0) {
					return "票種價格不可小於零。";
				}
				if (ticketTypeDTO.getQuantityAvailable() != null && ticketTypeDTO.getQuantityAvailable() <= 0) {
					return "票種數量限制不可小於或等於零。";
				}
				if (ticketTypeDTO.getStartSaleTime() == null) {
					return "開始售票時間不可為空白。";
				}
				if (ticketTypeDTO.getEndSaleTime() == null) {
					return "結束售票時間不可為空白。";
				}
			}
		}
		return "";
	}
	
	/*
	 * method 名稱：createEvent
	 * 用途：新增活動的實際執行 Service
	 * @param：EventDTO（使用者輸入資料）
	 * @return：boolean（false 表示發生 SQLException）
	*/
	public boolean createEvent(EventDTO eventDTO) {
		// 4.1 將三種 DTO 包裝為 PO
		// 4.2 呼叫 DAO，傳入 EventsPO, Map<Short, SessionsPO>, List<TicketTypesPO>
		// 4.3 回傳 boolean 或是自定義 Exception
		
		
		// 4.1 將三種 DTO 包裝為 PO
		
		EventsPO eventsPO = new EventsPO();
		Map<Short, SessionsPO> sessionsPOMap = new HashMap<Short, SessionsPO>();;
		List<TicketTypesPO> ticketTypesPOList = new ArrayList<TicketTypesPO>();
		
		eventsPO.setEventName(eventDTO.getEventName());
//		eventsPO.setEventPic(eventDTO.getEventPic());
		eventsPO.setEventType(eventDTO.getEventType());
		eventsPO.setEventDesc(eventDTO.getEventDesc());
		eventsPO.setTotalReviews(0);
		eventsPO.setTotalScore(0);
		
		for (SessionDTO sessionDTO : eventDTO.getSessionList()) {
			SessionsPO sessionsPO = new SessionsPO();
			sessionsPO.setSessionNo(sessionDTO.getSessionNo());
			sessionsPO.setSessionName(sessionDTO.getSessionName());
			sessionsPO.setSessionDesc(sessionDTO.getSessionDesc());
			sessionsPO.setPlace(sessionDTO.getPlace());
			sessionsPO.setAddress(sessionDTO.getAddress());
			Timestamp sessionStartTime = sessionDTO.getSessionStartTime();
			sessionsPO.setSessionStartTime(sessionStartTime);
			sessionsPO.setStartEntryTime(sessionDTO.getStartEntryTime());
			sessionsPO.setEndEntryTime(sessionDTO.getEndEntryTime());
			sessionsPO.setQuantityTotalAvailable(sessionDTO.getQuantityTotalAvailable());
			sessionsPO.setQuantityTotalPurchased(0);
			
			if (eventsPO.getEarliestSessionTime() == null && eventsPO.getLatestSessionTime() == null) {
				eventsPO.setEarliestSessionTime(sessionStartTime);
				eventsPO.setLatestSessionTime(sessionStartTime);
			} else {
				if (sessionStartTime.before(eventsPO.getEarliestSessionTime())) {
					eventsPO.setEarliestSessionTime(sessionStartTime);
				}
				if (sessionStartTime.after(eventsPO.getLatestSessionTime())) {
					eventsPO.setLatestSessionTime(sessionStartTime);
				}
			}
			
			for (TicketTypeDTO ticketTypeDTO : sessionDTO.getTicketTypeList()) {
				TicketTypesPO ticketTypesPO = new TicketTypesPO();
				ticketTypesPO.setSessionID(0 - (sessionDTO.getSessionNo()));  // 負數以表示此為暫時儲存 position
				ticketTypesPO.setTicketTypeNo(ticketTypeDTO.getTicketTypeNo());
				ticketTypesPO.setTypeName(ticketTypeDTO.getTypeName());
				ticketTypesPO.setTypeDesc(ticketTypeDTO.getTypeDesc());
				ticketTypesPO.setPrice(ticketTypeDTO.getPrice());
				Integer quantityAvailable = ticketTypeDTO.getQuantityAvailable();
				if (quantityAvailable != null) {
					ticketTypesPO.setQuantityAvailable(quantityAvailable);
				}
				ticketTypesPO.setQuantityPurchased(0);
				ticketTypesPO.setStartSaleTime(ticketTypeDTO.getStartSaleTime());
				ticketTypesPO.setEndSaleTime(ticketTypeDTO.getEndSaleTime());

				ticketTypesPOList.add(ticketTypesPO);
			}
			
			sessionsPOMap.put(sessionsPO.getSessionNo(), sessionsPO);
		}
		
		
		// 4.2 呼叫 DAO，傳入 EventsPO, List<SessionsPO>, List<TicketTypesPO>
		CreateEventDAO createEventDAO = new CreateEventDAO();
		try {
			createEventDAO.createEvent(eventsPO, sessionsPOMap, ticketTypesPOList);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
