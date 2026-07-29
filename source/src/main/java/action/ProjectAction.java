package action;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.ProjectDTO;
import model.UserDTO;
import model.WorkDTO;
import service.ProjectService;
import service.UserService;
import service.WorkService;

public class ProjectAction {
	
	HttpServletRequest request ;
	//コンストラクタ
	public ProjectAction(HttpServletRequest request) {
		this.request=request;
}
	//projectSelectAllメソッド
	public String projectSelectAll() throws UnsupportedEncodingException{
		String page="/WEB-INF/jsp/project/project_list.jsp";
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		ProjectService service = new ProjectService();
	
	ArrayList<ProjectDTO> projectList = service.projectSelectAll();
	request.setAttribute("projectList" , projectList);
	return page;
	}
	//projectSearchメソッド
	public String projectSearch() throws UnsupportedEncodingException ,SQLException{
		String page="/WEB-INF/jsp/project/project_list.jsp";
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		
		String statusStr = request.getParameter("projectStatus");
		
		String priorityStr = request.getParameter("projectPriority");
		
		String projectName = request.getParameter("projectName");
		
		int projectStatus = 0;
		int projectPriority = 0;
		
		if (statusStr != null && !statusStr.isEmpty()) {
			projectStatus = Integer.parseInt(statusStr);
			}
		
		if(priorityStr != null && ! priorityStr.isEmpty()) {
			projectPriority = Integer.parseInt(priorityStr);
		}
		
		ProjectService service = new ProjectService();
		
		ArrayList<ProjectDTO> projectList = service.projectSearch(projectStatus,projectPriority,projectName);
		request.setAttribute("projectList" , projectList);
		return page;
	}
	//projectRegistメソッド
	public  String projectRegist() throws UnsupportedEncodingException ,SQLException{
		//返却する次の飛び先のURLをとりあえず定義
		String page = "/WEB-INF/jsp/project/project_regist.jsp";
		
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectCode = request.getParameter("project-code");
		String projectName = request.getParameter("project-name");
		String customer = request.getParameter("customer");
		int pmId = Integer.parseInt(request.getParameter("pm-id"));
		int projectStatus = Integer.parseInt(request.getParameter("project-status"));
		int projectPriority = Integer.parseInt(request.getParameter("project-priority"));
		String projectStartDate = request.getParameter("project-start-date");
		String projectEndDate = request.getParameter("project-end-date");
		float projectEstimatedWorks = Float.parseFloat(request.getParameter("project-estimated-works"));
		String projectExplain = request.getParameter("project-explain");
		String projectLimit = request.getParameter("project-limit");
		
		
		
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
				projectEstimatedWorks,
				projectExplain,
				projectLimit
				
				);
		
		request.setAttribute("ans", ans);
		
		
		return page;
	}

	//projectUpdateメソッド
	public String projectUpdate() throws UnsupportedEncodingException,SQLException{
		String page = "/WEB-INF/jsp/project/project_edit.jsp";

		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String projectCode = request.getParameter("project-code");
		String projectName = request.getParameter("project-name");
		String customer = request.getParameter("customer");
		int pmId = Integer.parseInt(request.getParameter("pmId"));
		int projectStatus = Integer.parseInt(request.getParameter("project-status"));
		int projectPriority = Integer.parseInt(request.getParameter("project-priority"));
		String projectStartDate = request.getParameter("project-start-date");
		String projectEndDate = request.getParameter("project-end-date");
		String projectExplain = request.getParameter("project-explain");
		
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
	
		String page = "/WEB-INF/jsp/project/project_edit.jsp";
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		int projectStatus = Integer.parseInt(request.getParameter("project-status"));
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
			String page = "/WEB-INF/jsp/project/project_list.jsp";
			
			//入力値の取得
			request.setCharacterEncoding("UTF-8");
			int projectId = Integer.parseInt(request.getParameter("project-id"));
			
			// セッションからログイン中のユーザー情報を取得
			HttpSession session = request.getSession();
			UserDTO loginUser = (UserDTO) session.getAttribute("user");
					
			// セッションが切れている場合の安全対策
			if (loginUser == null) {
				page = "/WEB-INF/jsp/login.jsp";
				return page;
			}
			
			int userId = loginUser.getId();
			
			// Serviceを実体化して処理を依頼	
			ProjectService service = new ProjectService();
			ProjectDTO project = service.projectDetail(projectId);
			
			//workの一覧をtask_idから取得するサービス」DAOを実行する
			WorkService workService = new WorkService();
			ArrayList<WorkDTO> projectWorkList = workService.ProjectWorkList(userId,projectId);
			
			request.setAttribute("projectWorkList" , projectWorkList);
			return page;
		}



//追加メソッドselectProjectUserName
	
//PM候補一覧取得
	public String selectProjectUserName()
	throws SQLException {
	// 案件登録画面へ遷移
	String page =
	"/WEB-INF/jsp/project/project_regist.jsp";
	// UserService生成
	UserService service =
	new UserService();
	// PM候補一覧を取得
	ArrayList<UserDTO> pmList =
	service.selectProjectUserName();
	// JSPで使用できるよう格納
	request.setAttribute(
	"pmList",
	pmList);
	// 案件登録画面へ遷移
	return page;
	}
	

//追加メソッド　projectToEdit
	public String projectToEdit()
	throws SQLException, UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/project/project_edit.jsp";
		request.setCharacterEncoding("UTF-8");
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		ProjectService service = new ProjectService();
		ProjectDTO project = service.projectToEdit(projectId);
		request.setAttribute("project", project);
		UserService userService =
				
		new UserService();		
		ArrayList<UserDTO> pmList =
		userService.selectProjectUserName();
		request.setAttribute("pmList", pmList);
	return page;
	}
	//PM候補一覧取得
		public String selectProjectUserNamePlus()
		throws SQLException {
		// 案件登録画面へ遷移
		String page =
		"/WEB-INF/jsp/project/project_edit.jsp";
		// UserService生成
		UserService service =
		new UserService();
		// PM候補一覧を取得
		ArrayList<UserDTO> pmList =
		service.selectProjectUserNamePlus();
		// JSPで使用できるよう格納
		request.setAttribute(
		"pmList",
		pmList);
		// 案件登録画面へ遷移
		return page;
		}

}
