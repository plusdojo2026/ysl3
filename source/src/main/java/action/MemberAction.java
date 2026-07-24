package action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.UserDTO;
import service.UserService;

public class MemberAction {

	HttpServletRequest request;

	// コンストラクタ
	public MemberAction(HttpServletRequest request) {
		this.request = request;
	}

	// ユーザー一覧を表示するメソッド（管理者）----------------------------------------------------------
	public String memberSelectAll() throws UnsupportedEncodingException {

		// 戻り値のページを定義
		String page = null;

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
		ArrayList<UserDTO> allUserList = service.userSelectAll();

		// リストが空ならエラーメッセージをセット
		if (allUserList == null) {

			request.setAttribute("errMsg", "※一覧が取得できませんでした");
			page = "/WEB-INF/jsp/member/member_list.jsp";

			return page;

			// ちゃんと入っていたらメンバー一覧をリクエストに保存
		} else {
			session.setAttribute("allUserList", allUserList);

			// 戻り値
			return page;
		}
	}

	// ユーザー登録メソッド----------------------------------------------------------
	public String memberRegist() throws UnsupportedEncodingException {

		// 戻り値のページを定義
		String page = null;

		// 画面からの入力値を取得
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("login-id");
		String userName = request.getParameter("user-name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		int role = Integer.parseInt(request.getParameter("role"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報から権限を取り出す
		int userRole = loginUser.getRole();

		// 管理者かどうか判定
		if (userRole == 1) {

			// Serviceを実体化して処理を依頼
			UserService service = new UserService();
			int ans = service.userRegist(loginId, userName, mail, password, role);

			// 結果が取れたら
			if (ans == 1) {

				request.setAttribute("msg", "登録に成功しました");

				// 全件表示
				page = memberSelectAll();
			} else {

				request.setAttribute("errMsg", "登録に失敗しました");
				page = "/WEB-INF/jsp/member/member_list.jsp";
			}

		}

		return page;
	}

	// メンバー編集メソッド----------------------------------------------------------
	public String memberEdit() throws UnsupportedEncodingException {

		// 戻り値のページを定義
		String page = null;

		// 画面からの入力値を取得
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("user-name");
		String mail = request.getParameter("mail");
		int role = Integer.parseInt(request.getParameter("role"));
		int sol = Integer.parseInt(request.getParameter("sol"));
		int userId = Integer.parseInt(request.getParameter("user-id"));

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");

		// セッションが切れている場合の安全対策
		if (loginUser == null) {
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ログイン情報から権限を取り出す
		int userRole = loginUser.getRole();

		// 管理者かどうか判定
		if (userRole == 1) {

			// Serviceを実体化して処理を依頼
			UserService service = new UserService();
			int ans = service.userEdit(userId, userName, role, mail, sol);

			// 結果が取れたら
			if (ans == 1) {

				request.setAttribute("msg", "保存に成功しました");

				// 全件表示
				page = memberSelectAll();
			} else {

				request.setAttribute("errMsg", "保存に失敗しました");
				page = "/WEB-INF/jsp/member/member_list.jsp";
			}

		}

		// 戻り値
		return page;
	}

	// ユーザー編集ページの初期表示用メソッド
	public String memberToEdit() throws UnsupportedEncodingException {

		// 戻り値のページを定義
		String page = "/WEB-INF/jsp/member/member_edit.jsp";

		// UTF-8を指定
		request.setCharacterEncoding("UTF-8");

		// 画面からユーザーIDを取得
		String userIdText = request.getParameter("user-id");

		// 取得できたか判定。できていなかったらメンバー一覧へ戻す
		if (userIdText == null || userIdText.isEmpty()) {
			request.setAttribute("errMsg", "ユーザーIDの取得に失敗しました");
			return memberSelectAll();
		}

		// ユーザーIDを数値として保存する変数
		int userId;

		// ユーザーIDを数値に変換
		userId = Integer.parseInt(userIdText);

		// Service, DTOを実体化し、Serviceのメソッドを実行
		UserService service = new UserService();
		UserDTO member = service.memberToEdit(userId);

		if (member == null) {
			request.setAttribute("errMsg", "ユーザー情報の取得に失敗しました");
			return memberSelectAll();
		}

		// 取得できたユーザーの情報をリクエストに保存
		request.setAttribute("member", member);

		// 戻り値
		return page;
	}

	//	// ユーザー検索メソッド JSでやるかもなのでコメントアウト中
	//	public String userSearch() throws UnsupportedEncodingException {
	//		
	//		// 戻り値のページを定義
	//		String page = null;
	//		
	//		// 入力された語句を取得
	//		String keyword = request.getParameter("keyword");
	//		
	//		// セッションからログイン中のユーザー情報を取得
	//		HttpSession session = request.getSession();
	//		UserDTO loginUser = (UserDTO) session.getAttribute("user");
	//		
	//		// セッションが切れている場合の安全対策
	//		if (loginUser == null) {
	//			page = "/WEB-INF/jsp/login.jsp";
	//			return page;
	//		}
	//		
	//		// Serviceを実体化して処理を依頼
	//		UserService service = new UserService();
	//		ArrayList<UserDTO> searchUserList = service.userSearch(keyword);
	//		
	//		// リストが空ならエラーメッセージをセット
	//		if (searchUserList == null) {
	//
	//			request.setAttribute("errMsg", "※一覧が取得できませんでした");
	//			page = "/WEB-INF/jsp/home.jsp";
	//			
	//			return page;
	//
	//			// ちゃんと入っていたらログインできた人の情報をリクエストに保存
	//		} else {
	//		    request.setAttribute("homeTaskList", searchUserList);
	//		    
	//		    // 戻り値
	//		    return page;
	//		}
	//	}

}