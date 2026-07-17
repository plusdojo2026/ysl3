package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    //ページidを取得
	    //ドロワーメニュー用
	    String pageId = request.getParameter("page_id");
	    String page = "/WEB-INF/jsp/login.jsp";

	    if (pageId == null || pageId.equals("toHome")) {
	        page = "/WEB-INF/jsp/home.jsp";

	    } else if (pageId.equals("toProjectList")) {
	        page = "/WEB-INF/jsp/project/project_list.jsp";

	    } else if (pageId.equals("toTaskList")) {
	        page = "/WEB-INF/jsp/task/task_list.jsp";

	    } else if (pageId.equals("toMonth")) {
	        page = "/WEB-INF/jsp/month.jsp";

	    } else if (pageId.equals("toMypage")) {
	        page = "/WEB-INF/jsp/mypage.jsp";

	    } else if(pageId.equals("toMemberList")) {
	        page = "/WEB-INF/jsp/member/member_list.jsp";
	        
	    } else if (pageId.equals("toLogout")) {
	        // 後でログアウト処理を入れる
	        page = "/WEB-INF/jsp/login.jsp";
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	    dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		
//		String page = null;
//		
//		//ページIDを取得
//		String pageId = request.getParameter("page_id");
//		//ボタンの詳細を取得
//		String buttonId = request.getParameter("button_id");
//		
//		//何が入っているか確認
//		System.out.println("ページ："+pageId+" ボタン："+buttonId);
//		
//		//ここから区分けして、何の処理をさせるかを指定
//		if(pageId.equals("none") && buttonId.equals("ログアウト")) {
//			//ログアウトボタンが押されたら
//			HttpSession session = request.getSession();
//			session.invalidate();
//			page = "/WEB-INF/jsp/login.jsp";		
//		}else if(pageId.equals("L001") && buttonId.equals("ログイン")) {
//			//最初のページからログインボタンが押されたら
//			UserAction action = new UserAction(request);
//			page = action.login();
//		}else if(pageId.equals("M001") && buttonId.equals("登録")) {
//			//メニューから登録ボタンが押されたら
//			UserAction action = new UserAction(request);
//			page = action.regist();
//		}else if(pageId.equals("M002") && buttonId.equals("削除")) {
//			//メニューから削除ボタンが押されたら
//			UserAction action = new UserAction(request);
//			page = action.delete();
//		}
//		if(buttonId.equals("ホームへ")) {
//			page = "/WEB-INF/jsp/home.jsp";	
//		}else if(buttonId.equals("案件一覧へ")) {
//			page = "/WEB-INF/jsp/project_list.jsp";	
//		}
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//		dispatcher.forward(request, response);
	}

}
