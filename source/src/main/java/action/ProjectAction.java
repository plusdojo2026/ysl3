package action;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.ProjectDTO;
import service.ProjectService;

public class ProjectAction {
	
	HttpServletRequest request ;
	//コンストラクタ
	public ProjectAction(HttpServletRequest request) {
		this.request=request;
}
	//projectSelectAllメソッド
	public String projectSelectAll() throws UnsupportedEncodingException{
		String page="/WEB-INF/jsp/project_list.jsp";
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		ProjectService service = new ProjectService();
	
	ArrayList<ProjectDTO> projectList = service.projectSelectAll();
	request.setAttribute("projectList" , projectList);
	return page;
	}
	//projectSearchメソッド
	public String projectSearch() throws UnsupportedEncodingException ,SQLException{
		String page="/WEB-INF/jsp/project_list.jsp";
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		int projectStatus = Integer.parseInt(request.getParameter("projectStatus"));
		int projectPriority = Integer.parseInt(request.getParameter("projectPriority"));
		String projectName = request.getParameter("projectName");
		
		ProjectService service = new ProjectService();
		
		ArrayList<ProjectDTO> projectList = service.projectSearch(projectStatus,projectPriority,projectName);
		request.setAttribute("projectList" , projectList);
		return page;
	}
	//projectRegistメソッド
	public  String projectRegist() throws UnsupportedEncodingException ,SQLException{
		//返却する次の飛び先のURLをとりあえず定義
		String page = "/WEB-INF/jsp/project_regist.jsp";
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectCode = request.getParameter("projectCode");
		String projectName = request.getParameter("projectName");
		String customer = request.getParameter("customer");
		int pmId = Integer.parseInt(request.getParameter("pmId"));
		int projectStatus = Integer.parseInt(request.getParameter("projectStatus"));
		int projectPriority = Integer.parseInt(request.getParameter("projectPriority"));
		String projectStartDate = request.getParameter("projectStartDate");
		String projectEndDate = request.getParameter("projectEndDate");
		String projectExplain = request.getParameter("projectExplain");
		
		ProjectService service = new ProjectService();
		
	
		int ans = service.projectRegist(
				projectCode,
				projectName,
				customer,
				pmId,
				projectStatus,
				projectPriority,
				projectStartDate,
				projectEndDate,
				projectExplain);
		
		request.setAttribute("ans", ans);
		
		
		return page;
	}

	//projectUpdateメソッド
	public String projectUpdate() throws UnsupportedEncodingException,SQLException{
		String page = "/WEB-INF/jsp/project_edit.jsp";

		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectCode = request.getParameter("projectCode");
		String projectName = request.getParameter("projectName");
		String customer = request.getParameter("customer");
		int pmId = Integer.parseInt(request.getParameter("pmId"));
		int projectStatus = Integer.parseInt(request.getParameter("projectStatus"));
		int projectPriority = Integer.parseInt(request.getParameter("projectPriority"));
		String projectStartDate = request.getParameter("projectStartDate");
		String projectEndDate = request.getParameter("projectEndDate");
		String projectExplain = request.getParameter("projectExplain");
		
		ProjectService service = new ProjectService();
		
		int ans = service.projectUpdate(
				projectCode,
				projectName,
				customer,
				pmId,
				projectStatus,
				projectPriority,
				projectStartDate,
				projectEndDate,
				projectExplain);
		
		request.setAttribute("ans", ans);
		
		return page;
		}
	//projectStatusChangeメソッド
	public String projectStatusChange() throws UnsupportedEncodingException,SQLException{
	
		String page = "/WEB-INF/jsp/project_edit.jsp";
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		int projectStatus = Integer.parseInt(request.getParameter("projectStatus"));
		ProjectService service = new ProjectService();
		
		int ans = service.projectStatusChange (
				projectId,
				projectStatus
				);
		
		request.setAttribute("ans", ans);
		return page;
	}
	//projectDetailメソッド
	public  String projectDetail() throws UnsupportedEncodingException,SQLException {
		String page = "/WEB-INF/jsp/project_list.jsp";
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		
		ProjectService service = new ProjectService();
		ProjectDTO project = service.projectDetail(projectId);
		request.setAttribute("project" , project);
		return page;
	}}



//追加メソッドselectProjectUserName
	

	