package event.controller;

import java.io.IOException;
import java.util.List;

import event.object.dto.createevent.EventDTO;
import event.object.dto.createevent.SessionDTO;
import event.object.dto.createevent.SessionInfoDTO;
import event.object.dto.createevent.TicketTypeDTO;
import event.object.dto.createevent.TicketTypeInfoDTO;
import event.service.CreateEventService;
import event.util.TimestampUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JsonUtil;


@WebServlet ("/event/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TimestampUtil timestampUtil;
	private CreateEventService createEventService;

    public CreateEvent() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	timestampUtil = new TimestampUtil();
    	createEventService = new CreateEventService();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		// 1. 從 request getParameter("structure")，判斷出結構
		// 2. request getParameter，包裝為三種 DTO
		// 3. validate 使用者輸入的資訊
		// 4. 呼叫 service，傳入 EventDTO
			// 4.1 將三種 DTO 包裝為 PO
			// 4.2 呼叫 DAO，傳入 EventsPO, List<SessionsPO>, List<TicketTypesPO>
			// 4.3 回傳 boolean 或是自定義 Exception
		// 5. setAttribute(成功／失敗)，forward 給下一個網頁顯示結果



		// 1. 從 request getParameter("structure")，判斷出結構

		String structureString = request.getParameter("structure");
		List<SessionInfoDTO> sessionInfoList = JsonUtil.jsonStringToListBean(structureString, SessionInfoDTO.class);


		// 2. request getParameter，包裝為三種 DTO

		EventDTO eventDTO = new EventDTO();
		eventDTO.setEventName(request.getParameter("eventName"));
//		eventDTO.setEventPic(request.getParameter("eventPic"));
		eventDTO.setEventType(request.getParameter("eventType"));
		eventDTO.setEventDesc(request.getParameter("eventDesc"));

		for (SessionInfoDTO sessionInfo : sessionInfoList) {
			SessionDTO sessionDTO = new SessionDTO();
			sessionDTO.setSessionNo((short) sessionInfo.getPositionNo());
			sessionDTO.setSessionName(request.getParameter(sessionInfo.getIdString() + "Name"));
			sessionDTO.setSessionDesc(request.getParameter(sessionInfo.getIdString() + "Desc"));
			sessionDTO.setPlace(request.getParameter(sessionInfo.getIdString() + "Place"));
			sessionDTO.setAddress(request.getParameter(sessionInfo.getIdString() + "Address"));
			String sessionTotalQuantity = request.getParameter(sessionInfo.getIdString() + "totalQuantity");
			if (sessionTotalQuantity == null) {
				sessionDTO.setQuantityTotalAvailable(null);
			} else {
				sessionDTO.setQuantityTotalAvailable(Integer.parseInt(sessionTotalQuantity));
			}
			sessionDTO.setSessionStartTime(timestampUtil.inputStringToTimestamp(request.getParameter(sessionInfo.getIdString() + "StartDatetime")));
			sessionDTO.setStartEntryTime(timestampUtil.inputStringToTimestamp(request.getParameter(sessionInfo.getIdString() + "StartEntryDatetime")));
			sessionDTO.setEndEntryTime(timestampUtil.inputStringToTimestamp(request.getParameter(sessionInfo.getIdString() + "EndEntryDatetime")));

			for (TicketTypeInfoDTO ticketTypeInfo : sessionInfo.getTicketTypesInfo()) {
				TicketTypeDTO ticketTypeDTO = new TicketTypeDTO();
				ticketTypeDTO.setTicketTypeNo((short) ticketTypeInfo.getPositionNo());
				ticketTypeDTO.setTypeName(request.getParameter(ticketTypeInfo.getIdString() + "Name"));
				ticketTypeDTO.setTypeDesc(request.getParameter(ticketTypeInfo.getIdString() + "Desc"));
				String ticketTypePrice = request.getParameter(ticketTypeInfo.getIdString() + "Price");
				if (ticketTypePrice == null) {
					ticketTypeDTO.setPrice(null);
				} else {
					ticketTypeDTO.setPrice(Integer.parseInt(ticketTypePrice));
				}
				String ticketTypeQuantity = request.getParameter(ticketTypeInfo.getIdString() + "Quantity");
				if (ticketTypeQuantity == null) {
					ticketTypeDTO.setQuantityAvailable(null);
				} else {
					ticketTypeDTO.setQuantityAvailable(Integer.parseInt(ticketTypeQuantity));
				}
				ticketTypeDTO.setStartSaleTime(timestampUtil.inputStringToTimestamp(request.getParameter(ticketTypeInfo.getIdString() + "StartSale")));
				ticketTypeDTO.setEndSaleTime(timestampUtil.inputStringToTimestamp(request.getParameter(ticketTypeInfo.getIdString() + "EndSale")));

				sessionDTO.getTicketTypeList().add(ticketTypeDTO);
			}

			eventDTO.getSessionList().add(sessionDTO);
		}


		// 3. validate 使用者輸入的資訊
		String validate = createEventService.validate(eventDTO);
		if (!(validate == "")) {
			request.setAttribute("result", validate);
			request.getRequestDispatcher("/event/CreateEventResult.jsp").forward(request, response);
		}


		// 4. 呼叫 service，傳入 EventDTO
		boolean createEventResult = createEventService.createEvent(eventDTO);


		// 5. forward 給結果顯示網頁
		if (createEventResult) {
			request.setAttribute("result", "新增成功！");
			request.getRequestDispatcher("/event/CreateEventResult.jsp").forward(request, response);
		} else {
			request.setAttribute("result", "發生 SQLException");
			request.getRequestDispatcher("/event/CreateEventResult.jsp").forward(request, response);
		}
	}
}
