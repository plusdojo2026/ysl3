package action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.ProjectDTO;
import model.TaskDTO;
import model.UserDTO;
import model.WorkDTO;
import service.ProjectService;
import service.TaskService;
import service.WorkService;

public class WorkAction {
	HttpServletRequest request;

	// コンストラクタ
	public WorkAction(HttpServletRequest request) {
		this.request = request;
	}

	//工数一覧表示
	public String workSelectAll() throws UnsupportedEncodingException {
		// 戻り値のページを定義
		String page = null;

		// セッションを取得
		HttpSession session = request.getSession(false);

		if (session == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// セッションからユーザーID取得
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		WorkService service = new WorkService();
		ArrayList<WorkDTO> allWorkList = service.workSelectAll(userId);

		// リストが空ならエラーメッセージをセット
		if (allWorkList == null) {

			request.setAttribute("errMsg", "※一覧が取得できませんでした");
			page = "/WEB-INF/jsp/work_regist.jsp";

			return page;

			//入っていたら工数一覧をリクエストに保存
		} else {
			session.setAttribute("allworkrList", allWorkList);

			// 戻り値
			return page;

		}

	}

	//工数の登録メソッド
	public String regist() throws UnsupportedEncodingException, SQLException {

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		String workDate = request.getParameter("work-date");
		String explainText = request.getParameter("explain-text");
		float work = Float.parseFloat(request.getParameter("work"));

		// セッションを取得
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			return "/WEB-INF/jsp/login.jsp"; 
		}

		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		System.out.println(taskId);

		// セッションが切れている場合
		if (loginUser == null) {
			return "/WEB-INF/jsp/login.jsp";

		}

		// セッションからユーザーID取得
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		WorkService service = new WorkService();
		int ans = service.workRegist(userId, taskId, work, explainText, workDate);

		// 変更結果の判定
		if (ans == 1) {
			request.setAttribute("msg", "工数を登録しました");

			// 登録したタスクの詳細画面へ戻る
			TaskAction taskAction = new TaskAction(request);
			return taskAction.taskDetail();
		} else {
			request.setAttribute("errMsg", "工数を登録できませんでした");

			// 案件名・タスク名を再取得して登録画面へ戻る
			return workToRegist();
		}

	}

	// 工数を削除するメソッド
	public String delete() throws UnsupportedEncodingException, SQLException {

		// 文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// セッションからログインユーザーを取得
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "/WEB-INF/jsp/login.jsp";
		}
		UserDTO loginUser = (UserDTO) session.getAttribute("user");
		
		// セッションが切れている場合
		if (loginUser == null) {
			return "/WEB-INF/jsp/login.jsp";
		}

		int userId = loginUser.getId();

		if (loginUser.getRole() == 1) {
			userId = 0;
		}


		// 削除する工数IDを取得
		int workId = Integer.parseInt(request.getParameter("work-id"));

		// Serviceへ削除処理を依頼
		WorkService service = new WorkService();
		int ans = service.workDelete(workId, userId);
		if (ans == 1) {
			request.setAttribute("msg", "工数を削除しました");
		} else {
			request.setAttribute("errMsg", "工数を削除できませんでした");
		}

		// タスク情報と工数ログを再取得
		TaskAction taskAction = new TaskAction(request);
		return taskAction.taskDetail();
	}

	//工数を計算する
	public String tally() throws UnsupportedEncodingException {
		String page = "/WEB-INF/jsp/work_regist.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int workId = Integer.parseInt(request.getParameter("work-id"));
		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// Serviceを実体化して処理を依頼
		WorkService service = new WorkService();
		int ans = service.workTally(workId, taskId);

		// 戻り値
		return page;
	}

	//工数登録画面で案件名とタスク名を表示する
	public String workToRegist() throws UnsupportedEncodingException, SQLException {

		// 戻り値のページを定義
		String page = "/WEB-INF/jsp/work_regist.jsp";

		// セッションを取得
		HttpSession session = request.getSession(false);
		
		// セッションが切れている場合
		if (session == null) {
			return "/WEB-INF/jsp/login.jsp";
		}

		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// セッションからユーザーID取得
		int userId = loginUser.getId();

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// Serviceを実体化
		ProjectService projectService = new ProjectService();
		TaskService taskService = new TaskService();

		TaskDTO taskAns = taskService.taskDetail(taskId);
		int projectId = taskAns.getProjectId();

		ProjectDTO projectAns = projectService.projectDetail(projectId);

		// リストが空ならエラーメッセージをセット
		if (projectId == 0) {

			request.setAttribute("errMsg", "値が取得できませんでした");
			page = "/WEB-INF/jsp/home.jsp";

			return page;

			// ちゃんと入っていたら案件・タスクの情報をリクエストに保存
		} else {
			request.setAttribute("TaskShow", taskAns);
			request.setAttribute("ProjectShow", projectAns);

			// 戻り値
			return page;
		}
	}

	// 案件詳細に工数ログを表示するメソッド
	public String ProjectWorkList() throws SQLException {

		// 戻り値のページを定義
		String page = "/WEB-INF/jsp/home.jsp";

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession(false);

		if (session == null) {
			return "/WEB-INF/jsp/login.jsp";
		}
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// 工数ログ実体化
		WorkService workService = new WorkService();
		//ArrayList<WorkDTO> projectWorkList = workService.homeWorkList(userId);

		request.setAttribute("errMsg", "※一覧が取得できませんでした");

		// ちゃんと入っていたらログインできた人の情報をリクエストに保存

		//request.setAttribute("projectWorkList", projectWorkList);

		// 戻り値
		return page;

	}

	// タスク詳細に工数ログを表示するメソッド
	public String TaskWorkList() throws SQLException {

		// 戻り値のページを定義
		String page = "/WEB-INF/jsp/home.jsp";

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession(false);

		if (session == null) {
			return "/WEB-INF/jsp/login.jsp";
		}
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// 工数ログも同様
		WorkService workService = new WorkService();
		ArrayList<WorkDTO> taskWorkList = workService.taskWorkList(userId, taskId);

		request.setAttribute("errMsg", "※一覧が取得できませんでした");

		// ちゃんと入っていたらログインできた人の情報をリクエストに保存

		request.setAttribute("taskWorkList", taskWorkList);

		// 戻り値
		return page;

	}

	//	//ホームに工数ログを表示
	//	public String homework() throws UnsupportedEncodingException {
	//	String page="/WEB-INF/jsp/home.jsp";
	//	
	//		
	//		// セッションを取得
	//		HttpSession session = request.getSession();	
	//		
	//		// Serviceを実体化して処理を依頼
	//		WorkService service = new WorkService();
	//		ArrayList<WorkDTO> allWorkList = service.workSelectAll(0);
	//				
	//		// リストが空ならエラーメッセージをセット
	//		if (allWorkList == null) {
	//					
	//		request.setAttribute("errMsg", "※一覧が取得できませんでした");
	//			page = "/WEB-INF/jsp/member/work_regist.jsp";
	//					
	//			return page;
	//					
	//		// 入っていたら工数一覧をリクエストに保存
	//		} else {
	//			session.setAttribute("allWorkrList", allWorkList);
	//					
	//		// 戻り値
	//			return page;
	//		
	//	}
	//}

}
