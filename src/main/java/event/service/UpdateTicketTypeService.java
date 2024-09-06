package event.service;

import java.sql.SQLException;

import event.dao.TicketTypesDAO;
import event.object.dto.updateevent.OneTicketTypeDTO;
import event.object.po.TicketTypesPO;

public class UpdateTicketTypeService {

	/*
	 * method 名稱：validate
	 * 用途：驗證使用者輸入資料
	 * @param：OneTicketTypeDTO（使用者輸入資料）
	 * @return：String（「""」表示通過驗證，或是錯誤訊息）
	*/
	public String validate(OneTicketTypeDTO oneTicketTypeDTO) {
		if (oneTicketTypeDTO.getTicketTypeID() == null || oneTicketTypeDTO.getTicketTypeID() <= 0) {
			return "票種編號有誤。";
		}
		if (oneTicketTypeDTO.getTypeName() == null || oneTicketTypeDTO.getTypeName().isBlank()) {
			return "票種名稱不可為空白。";
		}
		if (oneTicketTypeDTO.getPrice() == null ) {
			return "票種價格不可為空白。";
		}
		if (oneTicketTypeDTO.getPrice() < 0) {
			return "票種價格不可小於零";
		}
		if (oneTicketTypeDTO.getQuantityAvailable() != null && oneTicketTypeDTO.getQuantityAvailable() <= 0) {
			return "票種數量限制不可小於或等於零。";
		}
		if (oneTicketTypeDTO.getQuantityPurchased() < 0) {
			return "已購買數量不可小於零";
		}
		if (oneTicketTypeDTO.getStartSaleTime() == null) {
			return "開始售票時間不可為空白。";
		}
		if (oneTicketTypeDTO.getEndSaleTime() == null) {
			return "結束售票時間不可為空白。";
		}
		return "";
	}

	/*
	 * method 名稱：updateTicketType
	 * 用途：修改票種的實際執行 Service
	 * @param：OneTicketTypeDTO（使用者輸入資料）
	 * @return：boolean（false 表示發生 SQLException）
	*/
	public Boolean updateTicketType(OneTicketTypeDTO oneTicketTypeDTO) {
		// 3.1 將 DTO 包裝為 PO
		TicketTypesPO ticketTypesPO = new TicketTypesPO();
		ticketTypesPO.setTicketTypeID(oneTicketTypeDTO.getTicketTypeID());
		ticketTypesPO.setTypeName(oneTicketTypeDTO.getTypeName());
		ticketTypesPO.setTypeDesc(oneTicketTypeDTO.getTypeDesc());
		ticketTypesPO.setPrice(oneTicketTypeDTO.getPrice());
		ticketTypesPO.setQuantityAvailable(oneTicketTypeDTO.getQuantityAvailable());
		ticketTypesPO.setQuantityPurchased(oneTicketTypeDTO.getQuantityPurchased());
		ticketTypesPO.setStartSaleTime(oneTicketTypeDTO.getStartSaleTime());
		ticketTypesPO.setEndSaleTime(oneTicketTypeDTO.getEndSaleTime());

		// 3.2 呼叫 DAO，傳入 TicketTypesPO
		TicketTypesDAO ticketTypesDAO = new TicketTypesDAO();
		try {
			Boolean result = ticketTypesDAO.updateTicketTypeById(ticketTypesPO);
			if (!result) {
				System.out.println("updateTicketTypeById() 回報 false");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		// 3.3 回傳結果
		return true;
	}

}
