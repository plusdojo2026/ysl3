package action;

import java.io.UnsupportedEncodingException;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ProjectDTO;
import service.ProjectService;
import service.WorkService;

public class MonthAction {
	
	HttpServletRequest request ;
	//コンストラクタ
	public MonthAction(HttpServletRequest request) {
		this.request=request;
	}
	
		public String month() throws UnsupportedEncodingException {
			String page="/WEB-INF/jsp/month.jsp";
			

		// URLから取得
		    if (month == null || month.isEmpty()) {
		        month = getCurrentMonth();
		    }

	    // 対象月表示用
	    request.setAttribute("targetMonth", month);
	    
		// 前月・次月作成
		    request.setAttribute("lastMonth", getLastMonth(month));
		
		    request.setAttribute("nextMonth",getNextMonth(month));
		
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
		
		//サマリーカード
		//稼働メンバー、総工数、予定、残り工数
		public String SummaryCard() throws UnsupportedEncodingException{
			String page="/WEB-INF/jsp/month.jsp";
			
			ProjectService service = new ProjectService();
		
		    List<ProjectDTO> summaryList = ProjectService.getSummaryCard(month);
		
		    request.setAttribute("summaryList", summaryList);
		
		    return page;
		}
		
		//案件別実績
		public String ProjectSummary() throws UnsupportedEncodingException{
			String page="/WEB-INF/jsp/month.jsp";
			
			ProjectService service = new ProjectService();
		
		    List<ProjectDTO> projectSummaryList = ProjectService.getProjectSummary(month);
		
		    request.setAttribute("projectSummaryList",projectSummaryList );
		
		    return page;
		}
		 
		//ユーザー別実績
		public String UserSummary() throws UnsupportedEncodingException{
			String page="/WEB-INF/jsp/month.jsp";{
			
			WorkService service = new WorkService();
		
		    List<ProjectDTO> userSummaryList = WorkService.getUserSummary(month);
		
		    request.setAttribute("userSummaryList",userSummaryList);
		 
		    return page;
		}
			}
				}
		
		
		
		
	
		
		
		
	
	
	
	
	
	
	
	
	
	


