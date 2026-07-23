package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.TaskDTO;
import model.UserDTO;
import service.TaskService;

public class TaskAction {
	HttpServletRequest request;

	//コンストラクタ
	public TaskAction(HttpServletRequest request) {
		this.request = request;
	}

	// タスク一覧を取得するメソッド
	public String taskSelectAll() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task_list.jsp";

		request.setCharacterEncoding("UTF-8");

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		ArrayList<TaskDTO> taskList = service.taskSelectAll(userId);

		// タスク一覧画面にて表示する
		request.setAttribute("task", taskList);
		return page;
	}

	// タスク検索をするメソッド
	public String taskSearch() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task_list.jsp";

		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		int taskStatus = Integer.parseInt(request.getParameter("task-status"));
		String taskName = request.getParameter("task-name");

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		ArrayList<TaskDTO> taskList = service.taskSearch(userId, taskId, projectId, taskStatus, taskName);

		// タスク一覧画面にて表示する
		request.setAttribute("task", taskList);
		return page;
	}

	// タスク登録メソッド
	public String taskregist() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task_list.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		String taskName = request.getParameter("task-name");
		int taskStatus = Integer.parseInt(request.getParameter("task-status"));
		int taskPriority = Integer.parseInt(request.getParameter("task-priority"));
		int progress = Integer.parseInt(request.getParameter("progress"));
		float taskEstimatedWorks = Float.parseFloat(request.getParameter("task-estimated-works"));
		String taskStartDate = request.getParameter("task-start-date");
		String taskLimit = request.getParameter("task-limit");
		String taskExplanation = request.getParameter("task-explanation");

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();

		int ans = service.taskRegist(userId, taskId, projectId, taskName, taskStatus, taskPriority,
				progress, taskEstimatedWorks, taskStartDate, taskLimit, taskExplanation);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "登録完了！");
		} else {
			request.setAttribute("msg", "登録失敗！");
		}
		return page;

	}

	//タスク編集メソッド
	public String taskUpdate() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task_list.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		String taskName = request.getParameter("task-name");
		int taskStatus = Integer.parseInt(request.getParameter("task-status"));
		int taskPriority = Integer.parseInt(request.getParameter("task-priority"));
		int progress = Integer.parseInt(request.getParameter("progress"));
		float taskEstimatedWorks = Float.parseFloat(request.getParameter("task-estimated-works"));
		String taskStartDate = request.getParameter("task-start-date");
		String taskLimit = request.getParameter("task-limit");
		String taskExplanation = request.getParameter("task-explanation");

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		int ans = service.taskUpdate(userId, taskId, projectId, taskName, taskStatus, taskPriority,
				progress, taskEstimatedWorks, taskStartDate, taskLimit, taskExplanation);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "編集完了！");
		} else {
			request.setAttribute("msg", "編集失敗！");
		}
		return page;

	}

	//タスク削除
	public String taskDelete() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/home.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		int ans = service.taskDelete(userId, taskId);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "削除完了！");
		} else {
			request.setAttribute("msg", "削除失敗！");
		}
		return page;
	}

	// タスクステータス変更メソッド
	public String taskStatusChange() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task_detail.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");

		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		int ans = service.taskStatusChange(userId, taskId);

		//ボタン変更
		if (ans == 1) {
			request.setAttribute("msg", "ステータス変更完了！");
		} else {
			request.setAttribute("msg", "ステータス変更失敗！");
		}
		return page;
	}

	//タスク詳細を表示するメソッド
	public String taskDetail() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task_detail.jsp";

		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		TaskDTO taskDetail = service.taskDetail(userId, taskId);

		// タスク詳細画面にて表示する
		request.setAttribute("TaskDetail", taskDetail);
		return page;
	}

	//ホーム画面のタスク一覧を表示するメソッド
	public String homeTaskList() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/home.jsp";

		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		ArrayList<TaskDTO> taskList = service.homeTaskList(taskId, userId);

		// タスク一覧画面にて表示する
		request.setAttribute("task", taskList);
		return page;
	}

	//案件一覧画面のタスク項目を表示するメソッド
	public String projectTaskList() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/project_list.jsp";

		Integer.parseInt(request.getParameter("user-id"));
		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		ArrayList<TaskDTO> taskList = service.taskSelectAll(userId);

		// タスク一覧画面にて表示する
		request.setAttribute("task", taskList);
		return page;
	}

	// 案件詳細画面のタスク項目を削除するメソッド
	public String taskDetailDelete() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/home.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		int ans = service.taskDelete(userId, taskId);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "削除完了！");
		} else {
			request.setAttribute("msg", "削除失敗！");
		}
		return page;
	}

	//  工数を登録するメソッド
	public String workRegist() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task_list.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報からユーザーIDを取り出す
		int userId = loginUser.getId();

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();

		int ans = service.workRegist(userId, taskId);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "登録完了！");
		} else {
			request.setAttribute("msg", "登録失敗！");
		}
		return page;

	}
}