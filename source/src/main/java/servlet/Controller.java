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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String page = "/WEB-INF/jsp/login.jsp";	
		//ページIDを取得
		String pageId = request.getParameter("page_id");
		//ボタンの詳細を取得
		String buttonId = request.getParameter("button_id");
		
		//何もわたってきて無ければログイン画面へ
		if(pageId==null && buttonId==null) {
			page = "/WEB-INF/jsp/home.jsp";	
		}//else if(xxxxx)と続いていく
		
		//ログイン画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
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
//			session.invalidate();m
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
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//		dispatcher.forward(request, response);
	}

}
