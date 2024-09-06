package event.controller;

import java.io.IOException;

import event.dao.TicketTypesDAO;
import event.object.dto.updateevent.OneTicketTypeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/event/ReadOneTicketType")
public class ReadOneTicketType extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReadOneTicketType() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Integer ticketTypeID = Integer.valueOf(request.getParameter("ticketTypeID"));
    	TicketTypesDAO ticketTypesDAO = new TicketTypesDAO();

    	// 取得該票種資料
		OneTicketTypeDTO ticketType = ticketTypesDAO.selectOneTicketTypeById(ticketTypeID);
		request.setAttribute("ticketType", ticketType);

		// 取得該票種是否為該場次的唯一票種
		Boolean isOnly = ticketTypesDAO.selectIfOnlyTicketTypeByDTO(ticketType);
		request.setAttribute("isOnly", isOnly);

		request.getRequestDispatcher("/event/TicketType.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
