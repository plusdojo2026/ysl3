package action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.TaskDTO;
import model.UserDTO;
import model.WorkDTO;
import service.TaskService;
import service.WorkService;



public class HomeAction {
	
	HttpServletRequest request;
	
	// コンストラクタ
	public HomeAction(HttpServletRequest request) {
		this.request = request;
	}
	
	// ホーム画面にタスク・ログを表示するメソッド
	public String homeSelectAll() throws SQLException {
		
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
		
		// Serviceを実体化して処理を依頼
		TaskService taskService = new TaskService();
		ArrayList<TaskDTO> homeTaskList = taskService.homeTaskList(userId);
		
		
		// 工数ログも同様
		WorkService workService = new WorkService();
		ArrayList<WorkDTO> homeWorkList = workService.homeWorkList(userId);
		
		// リストが空ならエラーメッセージをセット
		if (homeTaskList == null || homeWorkList == null) {

			request.setAttribute("errMsg", "※一覧が取得できませんでした");
			page = "/WEB-INF/jsp/home.jsp";
			
			return page;

			// ちゃんと入っていたらログインできた人の情報をリクエストに保存
		} else {
		    request.setAttribute("homeTaskList", homeTaskList);
		    request.setAttribute("homeWorkList", homeWorkList);
		    
		    // 戻り値
		    return page;
		}
	}
}
