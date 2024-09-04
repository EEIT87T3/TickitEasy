package event.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import event.object.dto.updateevent.OneTicketTypeDTO;
import event.service.UpdateTicketTypeService;
import event.util.TimestampUtil;

@WebServlet("/event/UpdateTicketType")
@MultipartConfig
public class UpdateTicketType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TimestampUtil timestampUtil;
	private UpdateTicketTypeService updateTicketTypeService;
	
    public UpdateTicketType() {
        super(); 
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	timestampUtil = new TimestampUtil();
    	updateTicketTypeService = new UpdateTicketTypeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. request getParameter，包裝為 OneTicketTypeDTO
		// 2. validate 使用者輸入的資訊
		// 3. 呼叫 service，傳入 OneTicketTypeDTO
			// 3.1 將 DTO 包裝為 PO
			// 3.2 呼叫 DAO，傳入 TicketTypesPO
			// 3.3 回傳結果
		// 4. setAttribute(成功／失敗)，forward 給下一個網頁顯示結果
		
		
		// 1. request getParameter，包裝為 OneTicketTypeDTO
		OneTicketTypeDTO oneTicketTypeDTO = new OneTicketTypeDTO();
		oneTicketTypeDTO.setTicketTypeID(Integer.parseInt(request.getParameter("ticketTypeID")));
		oneTicketTypeDTO.setTypeName(request.getParameter("typeName"));
		oneTicketTypeDTO.setTypeDesc(request.getParameter("typeDesc"));
		oneTicketTypeDTO.setPrice(Integer.parseInt(request.getParameter("price")));
		String quantityAvailable = request.getParameter("quantityAvailable");
		if (quantityAvailable.isBlank()) {
			oneTicketTypeDTO.setQuantityAvailable(null);
		} else {
			oneTicketTypeDTO.setQuantityAvailable(Integer.parseInt(quantityAvailable));
		}
		String quantityPurchased = request.getParameter("quantityPurchased");
		if (quantityPurchased.isBlank()) {
			oneTicketTypeDTO.setQuantityPurchased(0);
		} else {
			oneTicketTypeDTO.setQuantityPurchased(Integer.parseInt(quantityPurchased));
		}
		oneTicketTypeDTO.setStartSaleTime(timestampUtil.inputStringToTimestamp(request.getParameter("startSaleTime")));
		oneTicketTypeDTO.setEndSaleTime(timestampUtil.inputStringToTimestamp(request.getParameter("endSaleTime")));
	
		
		// 2. validate 使用者輸入的資訊
		String validate = updateTicketTypeService.validate(oneTicketTypeDTO);
		if (!(validate == "")) {
			request.setAttribute("result", validate);
			request.getRequestDispatcher("/event/UpdateTicketTypeResult.jsp").forward(request, response);
		}
		
		
		// 3. 呼叫 service，傳入 OneTicketTypeDTO
		Boolean updateTicketTypeResult = updateTicketTypeService.updateTicketType(oneTicketTypeDTO);
		
		
		// 4. setAttribute(成功／失敗)，forward 給下一個網頁顯示結果
		if (updateTicketTypeResult) {
			request.setAttribute("result", "修改成功！");
			request.getRequestDispatcher("/event/UpdateTicketTypeResult.jsp").forward(request, response);
		} else {
			request.setAttribute("result", "發生 SQLException");
			request.getRequestDispatcher("/event/UpdateTicketTypeResult.jsp").forward(request, response);
		}
	}

}
