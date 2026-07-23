package servlet;

import java.io.IOException;

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
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 画面遷移用：ページIDとプレビューの取得
		String pageId = request.getParameter("page-id");
		String preview = request.getParameter("preview");
		UserDTO loginUser = getLoginUser(request);
		String page;

		// ページIDなし
		if (pageId == null) {
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

			// 未ログインでログイン画面以外へ直接アクセス
		} else if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";

			// ===== 完成後も残す：ドロワーメニューの遷移 =====

			// ホーム
		} else if (pageId.equals("HO01")) {
			HomeAction action = new HomeAction(request);
			page = action.homeSelectAll();

			// 案件一覧
		} else if (pageId.equals("PR01")) {
			ProjectAction action = new ProjectAction(request);
			page = action.projectSelectAll();

			// タスク一覧
		} else if (pageId.equals("TA01")) {
			TaskAction action = new TaskAction(request);
			page = action.taskSelectAll();

			// 月次集計（管理者／一般）
		} else if (pageId.equals("MO01") || pageId.equals("MO02")) {
			MonthAction action = new MonthAction(request);
			page = action.monthSelect();

			// マイページ
		} else if (pageId.equals("MY01")) {
			UserAction action = new UserAction(request);
			page = action.mypageSelect();

			// メンバー一覧（管理者のみ）
		} else if (pageId.equals("AD01")) {
			if (loginUser.getRole() != 1) {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			} else {
				MemberAction action = new MemberAction(request);
				page = action.memberSelectAll();
			}

			// ===== 今だけ：ドロワーから全画面を見るための確認用 =====
			// header.jsp の now-menu と一緒に、完成時はこのelse if全体を削除する。
		} else if (preview != null
				&& preview.equals("true")) {

			if (pageId.equals("PR02")) {
				page = "/WEB-INF/jsp/project/project_regist.jsp";

			} else if (pageId.equals("PR03")) {
				page = "/WEB-INF/jsp/project/project_edit.jsp";

			} else if (pageId.equals("PR04")) {
				page = "/WEB-INF/jsp/project/project_detail.jsp";

			} else if (pageId.equals("TA02")) {
				page = "/WEB-INF/jsp/task/task_regist.jsp";

			} else if (pageId.equals("TA03")) {
				page = "/WEB-INF/jsp/task/task_edit.jsp";

			} else if (pageId.equals("TA04")) {
				page = "/WEB-INF/jsp/task/task_detail.jsp";

			} else if (pageId.equals("WO01")) {
				page = "/WEB-INF/jsp/work_regist.jsp";

			} else if (pageId.equals("AD02") && loginUser.getRole() == 1) {
				page = "/WEB-INF/jsp/member/member_regist.jsp";

			} else if (pageId.equals("AD03") && loginUser.getRole() == 1) {
				page = "/WEB-INF/jsp/member/member_edit.jsp";

			} else {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			}

			// ===== 確認用ここまで =====

			// ===== 完成後も残す：各画面のボタン・リンクからの遷移 =====

			// 案件登録画面
		} else if (pageId.equals("PR02")) {
			ProjectAction action = new ProjectAction(request);
			page = action.projectToRegist();

			// 案件編集画面
		} else if (pageId.equals("PR03")) {
			ProjectAction action = new ProjectAction(request);
			page = action.projectToEdit();

			// 案件詳細画面
		} else if (pageId.equals("PR04")) {
			ProjectAction action = new ProjectAction(request);
			page = action.projectDetail();

			// タスク登録画面
		} else if (pageId.equals("TA02")) {
			TaskAction action = new TaskAction(request);
			page = action.taskToRegist();

			// タスク編集画面
		} else if (pageId.equals("TA03")) {
			TaskAction action = new TaskAction(request);
			page = action.taskToEdit();

			// タスク詳細画面
		} else if (pageId.equals("TA04")) {
			TaskAction action = new TaskAction(request);
			page = action.taskDetail();

			// 工数登録画面
		} else if (pageId.equals("WO01")) {
			WorkAction action = new WorkAction(request);
			page = action.workToRegist();

			// メンバー登録画面（管理者のみ）
		} else if (pageId.equals("AD02")) {
			if (loginUser.getRole() != 1) {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			} else {
				page = "/WEB-INF/jsp/member/member_regist.jsp";
			}

			// メンバー編集画面（管理者のみ）
		} else if (pageId.equals("AD03")) {
			if (loginUser.getRole() != 1) {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			} else {
				MemberAction action = new MemberAction(request);
				page = action.memberToEdit();
			}

			// 不正な画面ID
		} else {
			HomeAction action = new HomeAction(request);
			page = action.homeSelectAll();
		}

		forward(request, response, page);
	}


	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// ページID, ボタンIDの取得
		String pageId = request.getParameter("page-id");
		String btnId = request.getParameter("btn-id");
		UserDTO loginUser = getLoginUser(request);
		String page;

		// page-idまたはbtn-idがない不正なPOST
		if (pageId == null || btnId == null) {
			if (loginUser == null) {
				page = "/WEB-INF/jsp/login.jsp";
			} else {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			}

			// ログイン画面で、ログインボタンを押したとき
		} else if (pageId.equals("LO01") && btnId.equals("login")) {
			UserAction action = new UserAction(request);
			page = action.login();

			// ログアウト
		} else if (btnId.equals("logout")) {
			UserAction action = new UserAction(request);
			page = action.logout();

			// 未ログインでログイン以外のPOST
		} else if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";

			// マイページ画面でパスワード変更ボタンを押したとき
		} else if (pageId.equals("MY01") && btnId.equals("password-change")) {
			UserAction action = new UserAction(request);
			page = action.passwordChange();

			// 案件検索
		} else if (pageId.equals("PR01") && btnId.equals("project-search")) {
			ProjectAction action = new ProjectAction(request);
			page = action.projectSearch();

			// 案件登録
		} else if (pageId.equals("PR02") && btnId.equals("project-regist")) {
			ProjectAction action = new ProjectAction(request);
			page = action.projectRegist();

			// 案件更新
		} else if (pageId.equals("PR03") && btnId.equals("project-update")) {
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

			// タスク登録
		} else if (pageId.equals("TA02") && btnId.equals("task-regist")) {
			TaskAction action = new TaskAction(request);
			page = action.taskregist();

			// タスク更新
		} else if (pageId.equals("TA03") && btnId.equals("task-update")) {
			TaskAction action = new TaskAction(request);
			page = action.taskUpdate();

			// タスク削除
			// ホームまたは案件詳細のタスク削除ボタンから呼ばれる
		} else if ((pageId.equals("HO01") || pageId.equals("PR04")) && btnId.equals("task-delete")) {
			TaskAction action = new TaskAction(request);
			page = action.taskDelete();

			// タスクステータス変更
		} else if (pageId.equals("TA04") && btnId.equals("task-status-change")) {
			TaskAction action = new TaskAction(request);
			page = action.taskStatusChange();

			// 工数登録
		} else if (pageId.equals("WO01") && btnId.equals("work-regist")) {
			WorkAction action = new WorkAction(request);
			page = action.regist();

			// 工数ログ削除
		} else if (pageId.equals("TA04") && btnId.equals("work-delete")) {
			WorkAction action = new WorkAction(request);
			page = action.delete();

			// 月の切り替え・再集計
		} else if ((pageId.equals("MO01") || pageId.equals("MO02")) && btnId.equals("month-change")) {
			MonthAction action = new MonthAction(request);
			page = action.monthTally();

			// CSV出力
		} else if ((pageId.equals("MO01") || pageId.equals("MO02")) && btnId.equals("csv-output")) {
			MonthAction action = new MonthAction(request);
			page = action.monthCsv();


			// メンバー登録（管理者のみ）
		} else if (pageId.equals("AD02") && btnId.equals("member-regist")) {
			if (loginUser.getRole() != 1) {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			} else {
				MemberAction action = new MemberAction(request);
				page = action.memberRegist();
			}

			// メンバー更新（管理者のみ）
		} else if (pageId.equals("AD03") && btnId.equals("member-update")) {
			if (loginUser.getRole() != 1) {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			} else {
				MemberAction action = new MemberAction(request);
				page = action.memberEdit();
			}

			// 不正な組み合わせ
		} else {
			HomeAction action = new HomeAction(request);
			page = action.homeSelectAll();
		}

		forward(request, response, page);
	}

	private UserDTO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return null;
		}

		return (UserDTO) session.getAttribute("user");
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		dispatcher.forward(request, response);
	}
}
