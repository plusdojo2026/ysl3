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
import service.UserService;
import service.WorkService;

public class TaskAction {
	HttpServletRequest request;

	//コンストラクタ
	public TaskAction(HttpServletRequest request) {
		this.request = request;
	}

	// タスク一覧を取得するメソッド
	public String taskSelectAll() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task/task_list.jsp";

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

		// 管理者はIDを0で実行
		if (loginUser.getRole() == 1) {
			userId = 0;
		}

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		ArrayList<TaskDTO> taskList = service.taskSelectAll(userId);
		//ユーザーがタスクとして追加したprojectの一覧
		ArrayList<ProjectDTO> projectList = service.projectSelectAll(userId);

		// タスク一覧画面にて表示する
		request.setAttribute("taskList", taskList);
		//projectの一覧を保存する
		session.setAttribute("projectList", projectList);
		return page;
	}

	// タスク検索をするメソッド
	public String taskSearch() throws UnsupportedEncodingException, SQLException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task/task_list.jsp";

		request.setCharacterEncoding("UTF-8");

		int projectId = Integer.parseInt(request.getParameter("project-id"));
		int status = Integer.parseInt(request.getParameter("task-status"));
		String name = request.getParameter("task-name");

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

		// 管理者はIDを0で実行
		if (loginUser.getRole() == 1) {
			userId = 0;
		}

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		ArrayList<TaskDTO> taskList = service.taskSearch(userId, name, status, projectId);

		// タスク一覧画面にて表示する
		request.setAttribute("taskList", taskList);
		return page;
	}

	// タスク登録メソッド
	public String taskRegist() throws UnsupportedEncodingException, SQLException {

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int userId = Integer.parseInt(request.getParameter("user-id"));
		String name = request.getParameter("task-name");
		int status = Integer.parseInt(request.getParameter("task-status"));
		int priority = Integer.parseInt(request.getParameter("task-priority"));
		String limitDate = request.getParameter("task-limit");
		String explanationText = request.getParameter("task-explanation");
		float estimatedWorks = Float.parseFloat(request.getParameter("task-estimated-works"));
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		String startDate = request.getParameter("task-start-date");
		int progress = Integer.parseInt(request.getParameter("progress"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			return "/WEB-INF/jsp/login.jsp";

		}

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();

		int ans = service.taskRegist(userId, name, status, priority,
				limitDate, explanationText, estimatedWorks, projectId, startDate, progress);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "タスクを登録しました");

			ProjectAction projectAction = new ProjectAction(request);
			return projectAction.projectDetail();
		} else {
			request.setAttribute("msg", "タスクを登録できませんでした");

			return taskToRegist();
		}

	}

	//タスク編集メソッド
	public String taskUpdate() throws UnsupportedEncodingException, SQLException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task/task_list.jsp";

		//値の取得
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		int userId = Integer.parseInt(request.getParameter("user-id"));
		String name = request.getParameter("task-name");
		int status = Integer.parseInt(request.getParameter("task-status"));
		int priority = Integer.parseInt(request.getParameter("task-priority"));
		String limitDate = request.getParameter("task-limit");
		String explanationText = request.getParameter("task-explanation");
		float estimatedWorks = Float.parseFloat(request.getParameter("task-estimated-works"));
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		String startDate = request.getParameter("task-start-date");
		int progress = Integer.parseInt(request.getParameter("progress"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		int ans = service.taskUpdate(taskId, userId, name, status, priority,
				explanationText, limitDate, estimatedWorks, projectId, startDate, progress);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "タスクを更新しました");
			ProjectAction projectAction = new ProjectAction(request);
			return projectAction.projectDetail();
		} else {
			request.setAttribute("errMsg", "タスクを更新できませんでした");
			return taskToEdit();
		}

	}

	//タスク削除
	public String taskDelete() throws UnsupportedEncodingException, SQLException {

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

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		int ans = service.taskDelete(taskId);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "削除完了！");
		} else {
			request.setAttribute("msg", "削除失敗！");
		}

		HomeAction homeAction = new HomeAction(request);
		return homeAction.homeSelectAll();

	}

	// タスクステータス変更メソッド
	public String taskStatusChange() throws UnsupportedEncodingException, SQLException {
		request.setCharacterEncoding("UTF-8");

		// 画面から値を取得
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		int taskStatus = Integer.parseInt(request.getParameter("task-status"));

		// セッションを取得
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

		// 管理者は全タスクを変更可能
		if (loginUser.getRole() == 1) {
			userId = 0;
		}

		// ステータス変更
		TaskService service = new TaskService();
		int ans = service.statusChange(taskId, taskStatus, userId);

		if (ans == 1) {
			request.setAttribute("msg", "ステータスを変更しました");
		} else {
			request.setAttribute("errMsg", "ステータスを変更できませんでした");
		}
		// 更新後のタスク詳細を再取得
		return taskDetail();
	}

	//タスク詳細を表示するメソッド
	public String taskDetail() throws UnsupportedEncodingException, SQLException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task/task_detail.jsp";

		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}
		int userId = loginUser.getId();

		if (loginUser.getRole() == 1) {
			userId = 0;
		}

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		TaskDTO taskDetail = service.taskDetail(taskId);

		//workの一覧をtask_idから取得するサービス」DAOを実行する
		WorkService workService = new WorkService();
		ArrayList<WorkDTO> taskWorkList = workService.taskWorkList(userId, taskId);

		if (taskWorkList == null) {
			System.out.println("NO");
		}
		// タスク詳細画面にて表示する
		request.setAttribute("taskDetail", taskDetail);

		//workのリストをスコープ（taskWorkList）にいれる
		request.setAttribute("taskWorkList", taskWorkList);
		return page;
	}

	//案件一覧画面のタスク項目を表示するメソッド
	public String projectTaskList() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/project/project_list.jsp";

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
	public String taskDetailDelete() throws UnsupportedEncodingException, SQLException {

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
		int ans = service.taskDelete(taskId);

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
		String page = "/WEB-INF/jsp/task/task_list.jsp";

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

		int ans = service.workRegist(taskId, userId);

		//ちゃんと登録できたか確認
		if (ans == 1) {
			request.setAttribute("msg", "登録完了！");
		} else {
			request.setAttribute("msg", "登録失敗！");
		}
		return page;

	}

	// タスク編集初期表示用メソッド
	public String taskToEdit() throws SQLException {
		TaskDTO dto = new TaskDTO();

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task/task_edit.jsp";

		//値の取得
		int taskId = Integer.parseInt(request.getParameter("task-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// Serviceを実体化して処理を依頼
		TaskService service = new TaskService();
		dto = service.taskToEdit(taskId);

		// DTOがnullなら
		if (dto == null) {

			request.setAttribute("errMsg", "タスク情報の取得に失敗しました。");
			page = "/WEB-INF/jsp/task/task_list.jsp";
			return page;

			// ちゃんと入っていたら
		} else {

			// 取得できたタスクの情報をrequestに保存
			request.setAttribute("editTask", dto);

			UserService userService = new UserService();
			ArrayList<UserDTO> memberList = userService.selectTaskUserName();
			request.setAttribute("memberList", memberList);

		}
		// 戻り値
		return page;
	}

	// タスク新規登録時ユーザー表示用メソッド
	public String taskToRegist() throws SQLException {
		ArrayList<UserDTO> dto = new ArrayList<UserDTO>();

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/task/task_regist.jsp";

		// プロジェクトIDの取得
		int projectId = Integer.parseInt(request.getParameter("project-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// Serviceを実体化して処理を依頼
		UserService service = new UserService();
		dto = service.selectTaskUserName();

		ProjectService projectService = new ProjectService();
		ProjectDTO project = projectService.projectDetail(projectId);

		// DTOがnullなら
		if (dto == null || project == null) {

			request.setAttribute("errMsg", "必要な情報の取得に失敗しました");
			page = "/WEB-INF/jsp/task/task_list.jsp";
			return page;

			// ちゃんと入っていたら
		} else {

			// 取得できたユーザーの情報をrequestに保存
			request.setAttribute("editTask", dto);
			request.setAttribute("project", project);
		}
		// 戻り値
		return page;
	}

}