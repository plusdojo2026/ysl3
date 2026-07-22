package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.UserAction;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// ドロワーメニューなどから受け取る画面ID
		String pageId = request.getParameter("page-id");

		// 指定なしの場合はホーム
		String page = "/WEB-INF/jsp/home.jsp";

		if (pageId == null || pageId.equals("HO01")) {
			page = "/WEB-INF/jsp/home.jsp";

		} else if (pageId.equals("PR01")) {
			page = "/WEB-INF/jsp/project/project_list.jsp";

		} else if (pageId.equals("PR04")) {
			page = "/WEB-INF/jsp/project/project_detail.jsp";

		} else if (pageId.equals("PR2")) {
			page = "/WEB-INF/jsp/project/project_regist.jsp";

		} else if (pageId.equals("PR03")) {
			page = "/WEB-INF/jsp/project/project_edit.jsp";

			// タスク
		} else if (pageId.equals("TA01")) {
			page = "/WEB-INF/jsp/task/task_list.jsp";

		} else if (pageId.equals("TA04")) {
			page = "/WEB-INF/jsp/task/task_detail.jsp";

		} else if (pageId.equals("TA02")) {
			page = "/WEB-INF/jsp/task/task_regist.jsp";

		} else if (pageId.equals("TA03")) {
			page = "/WEB-INF/jsp/task/task_edit.jsp";

			// 工数
		} else if (pageId.equals("WO01")) {
			page = "/WEB-INF/jsp/work_regist.jsp";

			// 月次・マイページ
		} else if (pageId.equals("MO01")) {
			page = "/WEB-INF/jsp/month.jsp";

		} else if (pageId.equals("MY01")) {
			page = "/WEB-INF/jsp/mypage.jsp";

			// メンバー管理
		} else if (pageId.equals("AD01")) {
			page = "/WEB-INF/jsp/member/member_list.jsp";

		} else if (pageId.equals("AD02")) {
			page = "/WEB-INF/jsp/member/member_regist.jsp";

		} else if (pageId.equals("AD03")) {
			page = "/WEB-INF/jsp/member/member_edit.jsp";

			// ログイン
		} else if (pageId.equals("LO01")) {
			page = "/WEB-INF/jsp/login.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
				
				String page = null;
				
				//ページIDを取得
				String pageId = request.getParameter("page-id");
				
				//ボタンの詳細を取得
				String btnId = request.getParameter("button-id");
				
				//何が入っているか確認
				System.out.println("ページ："+pageId+" ボタン："+btnId);
				
				//ここから区分けして、何の処理をさせるかを指定
				if(pageId.equals("none") && btnId.equals("ログアウト")) {
					
					//ログアウトボタンが押されたら
					HttpSession session = request.getSession();
					session.invalidate();
					page = "/WEB-INF/jsp/login.jsp";		
				}else if(pageId.equals("L001") && btnId.equals("ログイン")) {
					
					//最初のページからログインボタンが押されたら
					UserAction action = new UserAction(request);
					page = action.login();
				}else if(pageId.equals("M001") && btnId.equals("登録")) {
					
					//メニューから登録ボタンが押されたら
					UserAction action = new UserAction(request);
					page = action.regist();
				}else if(pageId.equals("M002") && btnId.equals("削除")) {
					
					//メニューから削除ボタンが押されたら
					UserAction action = new UserAction(request);
					page = action.delete();
				}
				if(btnId.equals("ホームへ")) {
					page = "/WEB-INF/jsp/home.jsp";	
				}else if(btnId.equals("案件一覧へ")) {
					page = "/WEB-INF/jsp/project_list.jsp";	
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
	}

}
