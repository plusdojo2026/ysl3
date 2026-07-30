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
import action.ProjectAction;
import action.TaskAction;
import action.UserAction;
import action.WorkAction;
import model.UserDTO;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String pageId = request.getParameter("page-id");
		String preview = request.getParameter("preview");
		String page;

		HttpSession session = request.getSession(false);
		UserDTO loginUser = null;

		if (session != null) {
			loginUser = (UserDTO) session.getAttribute("user");
		}

		try {
			// ページIDがない場合
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

				// 未ログイン
			} else if (loginUser == null) {
				page = "/WEB-INF/jsp/login.jsp";

				// ドロワーメニューからの遷移

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

//				// 月次
//			} else if (pageId.equals("MO01") || pageId.equals("MO02")) {
//			 MonthAction action = new MonthAction(request);
//			 page = action.monthSelect();
//				// マイページ
			} else if (pageId.equals("MY01")) {
				UserAction action = new UserAction(request);
				page = action.mypageSelect();

				// メンバー一覧
			} else if (pageId.equals("AD01")) {
				if (loginUser.getRole() != 1) {
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();
				} else {
					MemberAction action = new MemberAction(request);
					page = action.memberSelectAll();
				}

				

				// 実際のボタン・リンクからの画面遷移

				// 案件詳細画面
			} else if (pageId.equals("PR04")) {
				ProjectAction action = new ProjectAction(request);
				page = action.projectDetail();

				// タスク登録画面の初期表示
			} else if (pageId.equals("TA02")) {
				TaskAction action = new TaskAction(request);
				page = action.taskToRegist();

				// タスク編集画面の初期表示
			} else if (pageId.equals("TA01")) {
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

				// メンバー登録画面
			} else if (pageId.equals("AD02")) {
				if (loginUser.getRole() != 1) {
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();
				} else {
					page = "/WEB-INF/jsp/member/member_regist.jsp";
				}

				// メンバー編集画面
			} else if (pageId.equals("AD03")) {
				if (loginUser.getRole() != 1) {
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();
				} else {
					MemberAction action = new MemberAction(request);
					page = action.memberToEdit();
				}

				// 不正なページID
			} else {
				HomeAction action = new HomeAction(request);
				page = action.homeSelectAll();
			}

		} catch (SQLException e) {
			throw new ServletException(
					"画面表示処理中にエラーが発生しました",
					e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String pageId = request.getParameter("page-id");
		String btnId = request.getParameter("btn-id");
		String page;

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

				// ログイン以外のPOSTでセッションが切れている場合
			} else if (loginUser == null) {
				page = "/WEB-INF/jsp/login.jsp";

				// パスワード変更
			} else if (pageId.equals("MY01") && btnId.equals("password-change")) {
				UserAction action = new UserAction(request);
				page = action.passwordChange();
				
			} else if (pageId.equals("PR03")&& btnId.equals("project-to-edit")) {
				ProjectAction action = new ProjectAction(request);
				page = action.projectToEdit();
			
			// 案件登録画面の初期表示
			} else if (pageId.equals("PR02") && btnId.equals("project-to-regist")) {
				ProjectAction action = new ProjectAction(request);
				page = action.selectProjectUserName();

				// 案件検索
			} else if (pageId.equals("PR01") && btnId.equals("検索")) {
				ProjectAction action = new ProjectAction(request);
				page = action.projectSearch();

				// 案件登録
			} else if (pageId.equals("PR02") && btnId.equals("登録")) {
				ProjectAction action = new ProjectAction(request);
				page = action.projectRegist();

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

				// タスク登録
			} else if (pageId.equals("TA02") && btnId.equals("task-regist")) {
				TaskAction action = new TaskAction(request);
				page = action.taskRegist();

				// タスク更新
			} else if (pageId.equals("TA03") && btnId.equals("task-update")) {
				TaskAction action = new TaskAction(request);
				page = action.taskUpdate();
				
			} else if (pageId.equals("TA01") && btnId.equals("task-edit")) {
				TaskAction action = new TaskAction(request);
				page = action.taskToEdit();

				// ホーム画面からタスク削除
			} else if (pageId.equals("HO01") && btnId.equals("task-delete")) {
				TaskAction action = new TaskAction(request);
				page = action.taskDelete();

				// 案件詳細画面からタスク削除
			} else if (pageId.equals("PR04") && btnId.equals("task-delete")) {
				TaskAction action = new TaskAction(request);
				page = action.taskDelete();

				// タスク詳細画面からタスク削除
			} else if (pageId.equals("TA04") && btnId.equals("task-delete")) {
				TaskAction action = new TaskAction(request);
				page = action.taskDetailDelete();

				// タスクステータス変更
			} else if (pageId.equals("TA04") && btnId.equals("task-status-change")) {
				TaskAction action = new TaskAction(request);
				page = action.taskStatusChange();

				// 工数登録
			} else if (pageId.equals("WO01") && btnId.equals("work-regist")) {
				WorkAction action = new WorkAction(request);
				page = action.regist();

				// 工数削除
			} else if (pageId.equals("TA04") && btnId.equals("work-delete")) {
				WorkAction action = new WorkAction(request);
				page = action.delete();
				
				// ホームから工数登録画面へ
			} else if (pageId.equals("HO01") && btnId.equals("work-regist")) {
				WorkAction action = new WorkAction(request);
				page = action.workToRegist();
		
			} else if (pageId.equals("TA01") && btnId.equals("task-update")) {
				TaskAction action = new TaskAction(request);
				page = action.taskToEdit();
				
				
//			} else if ((pageId.equals("MO01") || pageId.equals("MO02")) && btnId.equals("month-change")) {
//				 MonthAction action = new MonthAction(request);
//				 page = action.month();
				 
				// タスク編集画面の初期表示
			} else if (pageId.equals("TA01") && btnId.equals("task-regist")) {
				TaskAction action = new TaskAction(request);
				page = action.taskToRegist();
//	
//			} else if ((pageId.equals("MO01") || pageId.equals("MO02")) && btnId.equals("csv-output")) {
//				 MonthAction action = new MonthAction(request);
//				 page = action.monthCsv();


				// メンバー登録
			} else if (pageId.equals("AD02") && btnId.equals("member-regist")) {
				if (loginUser.getRole() != 1) {
					HomeAction action = new HomeAction(request);
					page = action.homeSelectAll();
				} else {
					MemberAction action = new MemberAction(request);
					page = action.memberRegist();
				}

				// メンバー更新
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

		} catch (SQLException e) {
			throw new ServletException(
					"更新処理中にエラーが発生しました",
					e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		dispatcher.forward(request, response);
	}
}