package event.object.dto.createevent;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("htmlIdNo")
    private int htmlIdNo;

    @JsonProperty("positionNo")
    private int positionNo;

    @JsonProperty("ticketTypesInfo")
    private List<TicketTypeInfoDTO> ticketTypesInfo;

	public int getHtmlIdNo() {
		return htmlIdNo;
	}
	public void setHtmlIdNo(int htmlIdNo) {
		this.htmlIdNo = htmlIdNo;
	}
	public int getPositionNo() {
		return positionNo;
	}
	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}
	public List<TicketTypeInfoDTO> getTicketTypesInfo() {
		return ticketTypesInfo;
	}
	public void setTicketTypesInfo(List<TicketTypeInfoDTO> ticketTypesInfo) {
		this.ticketTypesInfo = ticketTypesInfo;
	}

	@Override
	public String toString() {
		return "SessionInfo [htmlIdNo=" + htmlIdNo + ", positionNo=" + positionNo + ", ticketTypesInfo="
				+ ticketTypesInfo + "]";
	}

	/*
	 * method 名稱：getIdString
	 * 用途：取得此 SessionInfo 對應的 Session 資料的 HTML 元素 id
	 * @param：（無）
	 * @return：String
	*/
	public String getIdString() {
		return "session" + htmlIdNo;
	}

}
