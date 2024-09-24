package event.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import event.object.dto.updateevent.OneTicketTypeDTO;
import event.object.po.TicketTypesPO;
import event.service.TicketTypeService;
import event.util.TimestampUtil;

@Controller
public class TicketTypeController {

	@Autowired
	private TicketTypeService ticketTypeService;
	
	@Autowired
	private TimestampUtil timestampUtil;
	
	
	@GetMapping("/event/TicketType")
	public String readAllTicketTypes(Model model) {
		List<TicketTypesPO> ticketTypeslist = ticketTypeService.readAllTicketTypes();
		model.addAttribute("ticketTypes", ticketTypeslist);
		return "event/ReadAllTicketTypes";
	}
	
	@GetMapping("/event/TicketType/{ticketTypeID}")
	public String readOneTicketType(@PathVariable("ticketTypeID") Integer ticketTypeID, Model model) {
		
    	// 取得該票種資料
		TicketTypesPO ticketType = ticketTypeService.readOneTicketType(ticketTypeID);
		model.addAttribute("ticketType", ticketType);
		
		// 取得該票種是否為該場次的唯一票種
//		Boolean isOnly = ticketTypeService.searchIfOnlyTicketTypeByPO(ticketType);
		Boolean isOnly = true;
		model.addAttribute("isOnly", isOnly);
		
		return "event/TicketType";
	}
	
	@PostMapping("/event/DeleteTicketTypeMvc")
	public String deleteTicketType(Model model, @RequestParam("ticketTypeID") Integer ticketTypeID) {
		Boolean result = ticketTypeService.delete(ticketTypeID);
		model.addAttribute("result", result);
		return "event/DeleteTicketTypeResult";
	}
	
	@PostMapping("/event/UpdateTicketTypeMvc")
	public String updateTicketType(Model model, @RequestParam Map<String, String> allParams) {
		
		// 1. getParameter，包裝為 OneTicketTypeDTO
		OneTicketTypeDTO oneTicketTypeDTO = new OneTicketTypeDTO();
		oneTicketTypeDTO.setTicketTypeID(Integer.parseInt(allParams.get("ticketTypeID")));
		oneTicketTypeDTO.setTypeName(allParams.get("typeName"));
		oneTicketTypeDTO.setTypeDesc(allParams.get("typeDesc"));
		oneTicketTypeDTO.setPrice(Integer.parseInt(allParams.get("price")));
		String quantityAvailable = allParams.get("quantityAvailable");
		if (quantityAvailable.isBlank()) {
			oneTicketTypeDTO.setQuantityAvailable(null);
		} else {
			oneTicketTypeDTO.setQuantityAvailable(Integer.parseInt(quantityAvailable));
		}
		String quantityPurchased = allParams.get("quantityPurchased");
		if (quantityPurchased.isBlank()) {
			oneTicketTypeDTO.setQuantityPurchased(0);
		} else {
			oneTicketTypeDTO.setQuantityPurchased(Integer.parseInt(quantityPurchased));
		}
		oneTicketTypeDTO.setStartSaleTime(timestampUtil.inputStringToTimestamp(allParams.get("startSaleTime")));
		oneTicketTypeDTO.setEndSaleTime(timestampUtil.inputStringToTimestamp(allParams.get("endSaleTime")));


		// 2. validate 使用者輸入的資訊
		String validate = ticketTypeService.validate(oneTicketTypeDTO);
		if (!(validate == "")) {
			model.addAttribute("result", validate);
			return "event/UpdateTicketTypeResult";
		}


		// 3. 呼叫 service，傳入 OneTicketTypeDTO
		Boolean updateTicketTypeResult = ticketTypeService.updateTicketType(oneTicketTypeDTO);


		// 4. setAttribute(成功／失敗)，回傳給 View 顯示結果
		if (updateTicketTypeResult) {
			model.addAttribute("result", "修改成功！");
			return "event/UpdateTicketTypeResult";
		} else {
			model.addAttribute("result", "發生 SQLException");
			return "event/UpdateTicketTypeResult";
		}
	}
}
