package event.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import event.object.dto.createevent.EventDTO;
import event.object.dto.createevent.SessionDTO;
import event.object.dto.createevent.SessionInfoDTO;
import event.object.dto.createevent.TicketTypeDTO;
import event.object.dto.createevent.TicketTypeInfoDTO;
import event.service.CreateEventService;
import event.util.TimestampUtil;
import util.JsonUtil;

@Controller
public class EventController {
	
	@Autowired
	private CreateEventService createEventService;
	@Autowired
	private TimestampUtil timestampUtil;
	
	
	@GetMapping("/event/CreateEvent")
	public String createEventPage() {
		return "forward:/event/CreateEvent.html";
	}
	
	@PostMapping
	public String createEvent(Model model, @RequestParam Map<String, String> allParams) {
		// 1. 從 request getParameter("structure")，判斷出結構

		String structureString = allParams.get("structure");
		List<SessionInfoDTO> sessionInfoList = JsonUtil.jsonStringToListBean(structureString, SessionInfoDTO.class);


		// 2. request getParameter，包裝為三種 DTO

		EventDTO eventDTO = new EventDTO();
		eventDTO.setEventName(allParams.get("eventName"));
//		eventDTO.setEventPic(allParams.get("eventPic"));
		eventDTO.setEventType(allParams.get("eventType"));
		eventDTO.setEventDesc(allParams.get("eventDesc"));

		for (SessionInfoDTO sessionInfo : sessionInfoList) {
			SessionDTO sessionDTO = new SessionDTO();
			sessionDTO.setSessionNo((short) sessionInfo.getPositionNo());
			sessionDTO.setSessionName(allParams.get(sessionInfo.getIdString() + "Name"));
			sessionDTO.setSessionDesc(allParams.get(sessionInfo.getIdString() + "Desc"));
			sessionDTO.setPlace(allParams.get(sessionInfo.getIdString() + "Place"));
			sessionDTO.setAddress(allParams.get(sessionInfo.getIdString() + "Address"));
			String sessionTotalQuantity = allParams.get(sessionInfo.getIdString() + "totalQuantity");
			if (sessionTotalQuantity == null) {
				sessionDTO.setQuantityTotalAvailable(null);
			} else {
				sessionDTO.setQuantityTotalAvailable(Integer.parseInt(sessionTotalQuantity));
			}
			sessionDTO.setSessionStartTime(timestampUtil.inputStringToTimestamp(allParams.get(sessionInfo.getIdString() + "StartDatetime")));
			sessionDTO.setStartEntryTime(timestampUtil.inputStringToTimestamp(allParams.get(sessionInfo.getIdString() + "StartEntryDatetime")));
			sessionDTO.setEndEntryTime(timestampUtil.inputStringToTimestamp(allParams.get(sessionInfo.getIdString() + "EndEntryDatetime")));

			for (TicketTypeInfoDTO ticketTypeInfo : sessionInfo.getTicketTypesInfo()) {
				TicketTypeDTO ticketTypeDTO = new TicketTypeDTO();
				ticketTypeDTO.setTicketTypeNo((short) ticketTypeInfo.getPositionNo());
				ticketTypeDTO.setTypeName(allParams.get(ticketTypeInfo.getIdString() + "Name"));
				ticketTypeDTO.setTypeDesc(allParams.get(ticketTypeInfo.getIdString() + "Desc"));
				String ticketTypePrice = allParams.get(ticketTypeInfo.getIdString() + "Price");
				if (ticketTypePrice == null) {
					ticketTypeDTO.setPrice(null);
				} else {
					ticketTypeDTO.setPrice(Integer.parseInt(ticketTypePrice));
				}
				String ticketTypeQuantity = allParams.get(ticketTypeInfo.getIdString() + "Quantity");
				if (ticketTypeQuantity == null) {
					ticketTypeDTO.setQuantityAvailable(null);
				} else {
					ticketTypeDTO.setQuantityAvailable(Integer.parseInt(ticketTypeQuantity));
				}
				ticketTypeDTO.setStartSaleTime(timestampUtil.inputStringToTimestamp(allParams.get(ticketTypeInfo.getIdString() + "StartSale")));
				ticketTypeDTO.setEndSaleTime(timestampUtil.inputStringToTimestamp(allParams.get(ticketTypeInfo.getIdString() + "EndSale")));

				sessionDTO.getTicketTypeList().add(ticketTypeDTO);
			}

			eventDTO.getSessionList().add(sessionDTO);
		}


		// 3. validate 使用者輸入的資訊
		String validate = createEventService.validate(eventDTO);
		if (!(validate == "")) {
			model.addAttribute("result", validate);
			return "CreateEventResult";
		}


		// 4. 呼叫 service，傳入 EventDTO
		boolean createEventResult = createEventService.createEvent(eventDTO);


		// 5. forward 給結果顯示網頁
		if (createEventResult) {
			model.addAttribute("result", "新增成功！");
			return "CreateEventResult";
		} else {
			model.addAttribute("result", "發生 SQLException");
			return "CreateEventResult";
		}
	}

}
