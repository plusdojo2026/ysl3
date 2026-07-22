package action;


import java.io.UnsupportedEncodingException;
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
		String ProjectId = request.getParameter("id");
	
	ProjectService service = new ProjectService();
	ArrayList<ProjectDTO> projectList = service.projectSelectAll();
	request.setAttribute("projectList" , projectList);
	return page;
	}
	//projectSearchメソッド
	public ProjectDTO projectSearch() throws UnsupportedEncodingException{
		ProjectDTO dto = null;
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectStatus = request.getParameter("id");
		String projectPriority = request.getParameter("priority");
		
		return dto;
	}
	//projectRegistメソッド
	public  int projectRegist() throws UnsupportedEncodingException{
		//返却する次の飛び先のURLをとりあえず定義
		int ans = 0;
		//下で使うのでDTOの箱だけ準備
		ProjectDTO dto = null;
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectCode = request.getParameter("code");
		String projectName = request.getParameter("name");
		String pmId = request.getParameter("pmId");
		String projectStatus = request.getParameter("status");
		String projectPriority = request.getParameter("priority");
		String projectStartDate = request.getParameter("startdate");
		String projectEndDate = request.getParameter("enddate");
		String projectExplain =  request.getParameter("explain");
		
		return ans;
	}

	//projectUpdateメソッド
	public int projectUpdate() throws UnsupportedEncodingException{
		int ans = 0 ;
		ProjectDTO dto = null;
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectCode = request.getParameter("code");
		String projectName = request.getParameter("name");
		String pmId = request.getParameter("pmId");
		String projectStatus = request.getParameter("status");
		String projectPriority = request.getParameter("priority");
		String projectStartDate = request.getParameter("startdate");
		String projectEndDate = request.getParameter("enddate");
		String projectExplain =  request.getParameter("explain");
		
		return ans;
		}
	//projectStatusChangeメソッド
	public int projectStatusChange() throws UnsupportedEncodingException{
		int ans = 0 ;
		ProjectDTO dto = null;
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectStatus = request.getParameter("status");
		return ans;
	}
	//projectdetailメソッド
	public  ProjectDTO projectdetail() {
		ProjectDTO dto = null;
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String ProjectId = request.getParameter("id");
		return dto;
	}}

	