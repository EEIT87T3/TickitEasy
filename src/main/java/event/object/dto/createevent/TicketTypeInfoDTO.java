package event.object.dto.createevent;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketTypeInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("htmlIdNo")
    private int htmlIdNo;

    @JsonProperty("sessionNo")
    private int sessionNo;

    @JsonProperty("positionNo")
    private int positionNo;

	public int getHtmlIdNo() {
		return htmlIdNo;
	}
	public void setHtmlIdNo(int htmlIdNo) {
		this.htmlIdNo = htmlIdNo;
	}
	public int getSessionNo() {
		return sessionNo;
	}
	public void setSessionNo(int sessionNo) {
		this.sessionNo = sessionNo;
	}
	public int getPositionNo() {
		return positionNo;
	}
	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}

	@Override
	public String toString() {
		return "TicketTypeInfo [htmlIdNo=" + htmlIdNo + ", sessionNo=" + sessionNo + ", positionNo=" + positionNo + "]";
	}

	/*
	 * method 名稱：getIdString
	 * 用途：取得此 TicketTypeInfo 對應的 TicketType 資料的 HTML 元素 id
	 * @param：（無）
	 * @return：String
	*/
	public String getIdString() {
		return "ticketType" + sessionNo + "_" + htmlIdNo;
	}
}
