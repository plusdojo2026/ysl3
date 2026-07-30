package action;

import java.io.UnsupportedEncodingException;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ProjectDTO;
import service.ProjectService;

public class MonthAction {

	HttpServletRequest request;

	//コンストラクタ
	public MonthAction(HttpServletRequest request) {
		this.request = request;
	}
	
	//	月次画面の初期表示
	public String monthSelect() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/month.jsp";
		
		//	値の取得			
		String month=request.getParameter("month");

		// URLから取得
		if (month == null || month.isEmpty()) {
			month = getCurrentMonth();
		}
	
		// 対象月表示用
		request.setAttribute("targetMonth",month);

		// 前月
		request.setAttribute("lastMonth", getLastMonth(month));
		// 来月
		request.setAttribute("nextMonth", getNextMonth(month));

		return page;
	}

	private String getLastMonth(String month) {

		YearMonth ym = YearMonth.parse(month);

		return ym.minusMonths(1).toString();
	}

	private String getNextMonth(String month) {

		YearMonth ym = YearMonth.parse(month);

		return ym.plusMonths(1).toString();
	}
	
	private String getCurrentMonth() {
		return YearMonth.now().toString();
	}

	//サマリーカード
	//稼働メンバー、総工数、予定、残り工数
	public String SummaryCard() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/month.jsp";

		ProjectService service = new ProjectService();
		
		String month = request.getParameter("month");

		List<ProjectDTO> summaryList = service.getSummaryCard(month);

		request.setAttribute("summaryList", summaryList);

		return page;
	}

	//案件別実績
	//案件名、実績工数、見積工数、予定工数、進捗率	
	public String ProjectSummary() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/month.jsp";

		ProjectService service = new ProjectService();
		
		String month = request.getParameter("month");

		List<ProjectDTO> projectSummaryList = service.getProjectSummary(month);

		request.setAttribute("projectSummaryList", projectSummaryList);

		return page;
	}

	//ユーザー別実績
	//ユーザー名、実績工数、見積工数、予定工数、進捗率	
	public String UserSummary() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/month.jsp";
		{

			ProjectService service = new ProjectService();
			
			String month = request.getParameter("month");

			List<ProjectDTO> userSummaryList = service.getUserSummary(month);

			request.setAttribute("userSummaryList", userSummaryList);

			return page;
		}
	}
}
