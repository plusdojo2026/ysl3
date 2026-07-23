package action;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.UserDTO;
import model.WorkDTO;
import service.WorkService;

public class WorkAction {
	HttpServletRequest request;

	// コンストラクタ
	public WorkAction(HttpServletRequest request) {
		this.request = request;
}
	
	//工数一覧表示
	public String workSelectAll() throws UnsupportedEncodingException{
		// 戻り値のページを定義
		String page = null;
		
		// セッションを取得
		HttpSession session = request.getSession(false);	
		
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
			page = "/WEB-INF/jsp/member/work_regist.jsp";
					
			return page;
					
		//入っていたら工数一覧をリクエストに保存
		} else {
			session.setAttribute("allworkrList", allWorkList);
					
		// 戻り値
			return page;
		
	}
	
	}
	
	//工数の登録メソッド
	public String regist() throws UnsupportedEncodingException {
		
	
		// 戻り値のページを定義
		String page = null;
		
		//値の取得
		request.setCharacterEncoding("UTF-8");		
		int projectId = Integer.parseInt(request.getParameter("project-id"));
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		String workDate = request.getParameter("work-date");
		String explainText = request.getParameter("explain-text");
		String work = request.getParameter("work");
		
		// セッションを取得
		HttpSession session = request.getSession(false);	
		
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
		int ans = service.workRegist(userId, projectId, taskId, workDate, explainText, work);

		// 変更結果の判定
		if (ans == 1) {
			request.setAttribute("msg", "工数登録しました！");
		} else {
			request.setAttribute("errMsg", "※工数登録できませんでした");
		}

		// 戻り値
		return page;
	
	}
	
	//工数を削除する
	public String delete() throws UnsupportedEncodingException {
		String page="/WEB-INF/jsp/task_detail.jsp";
		
		//値の取得
		request.setCharacterEncoding("UTF-8");	
		int workId = Integer.parseInt(request.getParameter("work-id"));
		int taskId = Integer.parseInt(request.getParameter("task-id"));
		
	
		// Serviceを実体化して処理を依頼
		WorkService service = new WorkService();
		int ans = service.workDelete(workId, taskId);	
		
		// 変更結果の判定
		if (ans == 1) {
			request.setAttribute("msg", "工数削除しました！");
		} else {
			request.setAttribute("errMsg", "※工数削除できませんでした");
		}
		
		
		// 戻り値
		return page;
	}	
	
	//工数を計算する、わかりません
	public String tally() throws UnsupportedEncodingException {
		String page="/WEB-INF/jsp/work_regist.jsp";
		
		//値の取得
		request.setCharacterEncoding("UTF-8");	
		int workId = Integer.parseInt(request.getParameter("work-id"));
		
		// Serviceを実体化して処理を依頼
		WorkService service = new WorkService();
		int ans = service.workTally(workId);
		
		

		
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
