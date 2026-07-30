package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.HomeAction;
import action.MemberAction;
import action.MonthAction;
import action.ProjectAction;
import action.TaskAction;
import action.UserAction;
import action.WorkAction;
import model.UserDTO;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String pageId = request.getParameter("page-id");
		String page;

		// セッションからログインユーザーを取得
		HttpSession session = request.getSession(false);
		UserDTO loginUser = null;

		if (session != null) {
			loginUser = (UserDTO) session.getAttribute("user");
		}

		try {

			// page-idがない場合
			if (pageId == null || pageId.isBlank()) {

				if (loginUser == null) {
					
					page = "/WEB-INF/jsp/login.jsp";

				} else {

					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();
				}

				// ログイン画面
			} else if (pageId.equals("LO01")) {
				
				page = "/WEB-INF/jsp/login.jsp";

				// ログアウト
			} else if (pageId.equals("logout")) {

				UserAction action = new UserAction(request);
				page = action.logout();

				// 未ログイン
			} else if (loginUser == null) {

				request.setAttribute("errMsg","セッションが切れました。再度ログインしてください");
				page = "/WEB-INF/jsp/login.jsp";

				// ホーム
			} else if (pageId.equals("HO01")) {

				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();

				// 案件一覧
			} else if (pageId.equals("PR01")) {

				ProjectAction action = new ProjectAction(request);
				page = action.projectSelectAll();

				// 案件登録画面
			} else if (pageId.equals("PR02")) {

				ProjectAction action = new ProjectAction(request);
				page = action.selectProjectUserName();

				// 案件編集画面
			} else if (pageId.equals("PR03")) {

				if (!isActive(request,"project-id")) {

					request.setAttribute("errMsg", "案件IDを取得できませんでした");
					ProjectAction action = new ProjectAction(request);
					page = action.projectSelectAll();

				} else {

					ProjectAction action = new ProjectAction(request);
					page = action.projectToEdit();
				}

				// 案件詳細画面
			} else if (pageId.equals("PR04")) {

				if (!isActive(request, "project-id")) {

					request.setAttribute("errMsg", "案件の情報を取得できませんでした");
					ProjectAction action = new ProjectAction(request);
					page = action.projectSelectAll();

				} else {

					ProjectAction action = new ProjectAction(request);
					page = action.projectDetail();
				}

				// タスク一覧
			} else if (pageId.equals("TA01")) {

				TaskAction action = new TaskAction(request);
				page = action.taskSelectAll();

				// タスク登録画面
			} else if (pageId.equals("TA02")) {

				TaskAction action = new TaskAction(request);
				page = action.taskToRegist();

				// タスク編集画面
			} else if (pageId.equals("TA03")) {

				if (!isActive(request, "task-id")) {

					request.setAttribute("errMsg", "タスク情報を取得できませんでした");
					TaskAction action = new TaskAction(request);
					page = action.taskSelectAll();

				} else {

					TaskAction action = new TaskAction(request);
					page = action.taskToEdit();
				}

				// タスク詳細画面
			} else if (pageId.equals("TA04")) {

				if (!isActive(request, "task-id")) {

					request.setAttribute("errMsg", "タスクIDを取得できませんでした");
					TaskAction action = new TaskAction(request);
					page = action.taskSelectAll();

				} else {

					TaskAction action = new TaskAction(request);
					page = action.taskDetail();
				}

				// 月次集計
			} else if (pageId.equals("MO01") || pageId.equals("MO02")) {

				MonthAction action = new MonthAction(request);

				// 対象月・前月・翌月を取得
				page = action.monthSelect();

				// サマリーカードを取得
				action.SummaryCard();

				// 案件別実績を取得
				action.ProjectSummary();

				// ユーザー別実績を取得
				action.UserSummary();

				// 工数登録画面
			} else if (pageId.equals("WO01")) {

				if (!isActive(request, "task-id")) {

					request.setAttribute("errMsg", "タスクIDを取得できませんでした");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();

				} else {

					WorkAction action = new WorkAction(request);
					page = action.workToRegist();
				}

				// マイページ
			} else if (pageId.equals("MY01")) {

				UserAction action = new UserAction(request);
				page = action.mypageSelect();

				// メンバー一覧
			} else if (pageId.equals("AD01")) {

				if (loginUser.getRole() != 1) {
					
					request.setAttribute("errMsg", "管理者のみ利用できます");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();

				} else {

					MemberAction action = new MemberAction(request);
					page = action.memberSelectAll();
				}

				// メンバー登録画面
			} else if (pageId.equals("AD02")) {

				if (loginUser.getRole() != 1) {

					request.setAttribute("errMsg", "管理者のみ利用できます");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();

				} else {

					page = "/WEB-INF/jsp/member/member_regist.jsp";
				}

				// メンバー編集画面
			} else if (pageId.equals("AD03")) {

				if (loginUser.getRole() != 1) {

					request.setAttribute("errMsg", "管理者のみ利用できます");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();

				} else if (!isActive(request, "user-id")) {

					request.setAttribute("errMsg", "ユーザーIDを取得できませんでした");
					MemberAction action = new MemberAction(request);
					page = action.memberSelectAll();

				} else {

					MemberAction action = new MemberAction(request);
					page = action.memberToEdit();
				}

				// 不正なpage-id
			} else {

				request.setAttribute("errMsg", "指定された画面は存在しません");
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			}

		} catch (SQLException e) {

			throw new ServletException("エラーが発生しました", e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String pageId = request.getParameter("page-id");
		String btnId = request.getParameter("btn-id");
		String page;

		// セッションからログインユーザーを取得
		HttpSession session = request.getSession(false);

		UserDTO loginUser = null;

		if (session != null) {
			loginUser = (UserDTO) session.getAttribute("user");
		}

		try {

			// page-idまたはbtn-idがない場合
			if (pageId == null || btnId == null) {

				if (loginUser == null) {

					page = "/WEB-INF/jsp/login.jsp";

				} else {

					request.setAttribute("errMsg", "処理を判別できませんでした");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();
				}

				// ログイン
			} else if (pageId.equals("LO01") && btnId.equals("login")) {

				UserAction action = new UserAction(request);
				page = action.login();

				// ログアウト
			} else if (btnId.equals("logout")) {

				UserAction action = new UserAction(request);
				page = action.logout();

				// セッション切れ
			} else if (loginUser == null) {

				request.setAttribute("errMsg", "セッションが切れました。再度ログインしてください");
				page = "/WEB-INF/jsp/login.jsp";

				// パスワード変更
			} else if (pageId.equals("MY01") && btnId.equals("password-change")) {

				UserAction action = new UserAction(request);
				page = action.passwordChange();

				// 案件検索
			} else if (pageId.equals("PR01") && btnId.equals("project-search")) {

				ProjectAction action = new ProjectAction(request);
				page = action.projectSearch();

				// 案件登録画面
			} else if (pageId.equals("PR01") && btnId.equals("project-regist")) {

				ProjectAction action = new ProjectAction(request);
				page = action.selectProjectUserName();

				// 以前の案件登録画面ボタン
			} else if (pageId.equals("PR02") && btnId.equals("project-to-regist")) {

				ProjectAction action = new ProjectAction(request);
				page = action.selectProjectUserName();

				// 案件登録
			} else if (pageId.equals("PR02") && btnId.equals("登録")) {

				ProjectAction action = new ProjectAction(request);
				page = action.projectRegist();

				// 案件編集画面
			} else if (pageId.equals("PR03") && btnId.equals("project-to-edit")) {

				if (!isActive(request, "project-id")) {

					request.setAttribute("errMsg", "案件IDを取得できませんでした");
					ProjectAction action = new ProjectAction(request);
					page = action.projectSelectAll();

				} else {

					ProjectAction action = new ProjectAction(request);
					page = action.projectToEdit();
				}

				// 案件更新
			} else if (pageId.equals("PR03") && btnId.equals("更新")) {

				ProjectAction action = new ProjectAction(request);
				page = action.projectUpdate();

				// 案件ステータス変更
			} else if (pageId.equals("PR04") && btnId.equals("project-status-change")) {

				ProjectAction action = new ProjectAction(request);
				page = action.projectStatusChange();

				// タスク検索
			} else if (pageId.equals("TA01") && btnId.equals("task-search")) {

				TaskAction action = new TaskAction(request);
				page = action.taskSearch();

				// タスク登録画面
			} else if (pageId.equals("TA01") && btnId.equals("task-regist")) {
				
				TaskAction action = new TaskAction(request);
				page = action.taskToRegist();
				

				// タスク登録
			} else if (pageId.equals("TA02") && btnId.equals("task-regist")) {

				TaskAction action = new TaskAction(request);
				page = action.taskRegist();

				// タスク編集画面
			} else if (pageId.equals("TA01") && btnId.equals("task-edit")) {

				if (!isActive(request, "task-id")) {

					request.setAttribute("errMsg", "タスクIDを取得できませんでした");
					TaskAction action = new TaskAction(request);
					page = action.taskSelectAll();

				} else {

					TaskAction action = new TaskAction(request);
					page = action.taskToEdit();
				}

				// タスク更新
			} else if (pageId.equals("TA03") && btnId.equals("task-update")) {

				TaskAction action = new TaskAction(request);
				page = action.taskUpdate();

				// ホームからタスク削除
			} else if (pageId.equals("HO01") && btnId.equals("task-delete")) {

				TaskAction action = new TaskAction(request);
				page = action.taskDelete();

				// 案件詳細からタスク削除
			} else if (pageId.equals("PR04") && btnId.equals("task-delete")) {

				TaskAction action = new TaskAction(request);
				page = action.taskDelete();

				// タスク詳細からタスク削除
			} else if (pageId.equals("TA04") && btnId.equals("task-delete")) {

				TaskAction action = new TaskAction(request);
				page = action.taskDetailDelete();

				// タスクステータス変更
			} else if (pageId.equals("TA04") && btnId.equals("task-status-change")) {

				TaskAction action = new TaskAction(request);
				page = action.taskStatusChange();

				// ホームから工数登録画面
			} else if (pageId.equals("HO01") && btnId.equals("work-regist")) {

				if (!isActive(request, "task-id")) {

					request.setAttribute("errMsg", "タスクIDを取得できませんでした");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();

				} else {

					WorkAction action = new WorkAction(request);
					page = action.workToRegist();
				}

				// 工数登録
			} else if (pageId.equals("WO01")&& btnId.equals("work-regist")) {

				WorkAction action = new WorkAction(request);
				page = action.regist();

				// 工数削除
			} else if (pageId.equals("TA04") && btnId.equals("work-delete")) {

				WorkAction action = new WorkAction(request);
				page = action.delete();

				// 月次変更
			} else if ((pageId.equals("MO01") || pageId.equals("MO02")) && btnId.equals("month-change")) {

				MonthAction action = new MonthAction(request);

				// 対象月・前月・翌月を取得
				page = action.monthSelect();

				// サマリーカードを取得
				action.SummaryCard();

				// 案件別実績を取得
				action.ProjectSummary();

				// ユーザー別実績を取得
				action.UserSummary();

				// メンバー登録
			} else if (pageId.equals("AD02") && btnId.equals("member-regist")) {

				if (loginUser.getRole() != 1) {

					request.setAttribute("errMsg", "管理者権限がありません");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();

				} else {

					MemberAction action = new MemberAction(request);
					page = action.memberRegist();
				}

				// メンバー更新
			} else if (pageId.equals("AD03") && btnId.equals("member-update")) {

				if (loginUser.getRole() != 1) {

					request.setAttribute("errMsg", "管理者のみ利用できます");
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();

				} else {

					MemberAction action = new MemberAction(request);
					page = action.memberEdit();
				}

				// 不正な組み合わせ
			} else {

				request.setAttribute("errMsg", "指定された処理は存在しません");
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			}

		} catch (SQLException e) {

			throw new ServletException("更新処理中にエラーが発生しました", e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	// 変数判定用メソッド
	private boolean isActive(HttpServletRequest request, String parameterName) {

		String value = request.getParameter(parameterName);
		if (value == null || value.isBlank()) {
			return false;
		}

		try {

			return Integer.parseInt(value) > 0;

		} catch (NumberFormatException e) {

			return false;
		}
	}
}