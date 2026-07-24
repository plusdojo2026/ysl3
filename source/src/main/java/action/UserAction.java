package action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.UserDTO;
import service.UserService;

public class UserAction {

	HttpServletRequest request;

	// コンストラクタ
	public UserAction(HttpServletRequest request) {
		this.request = request;
	}

	// ログインメソッド----------------------------------------------------------
	public String login() throws UnsupportedEncodingException, SQLException {

		// 返却する次の飛び先のURLを一旦定義
		String page = null;

		// 下で使うのでDTO実体化
		UserDTO dto = null;

		// ログインページより入力値を取得
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("login-id");
		String password = request.getParameter("password");

		// パスワード形式チェック
		if (password == null || password.length() < 6 || password.length() > 20) {
			request.setAttribute("errMsg", "パスワードは6文字以上20文字以内で入力してください");
			page = "/WEB-INF/jsp/login.jsp";
			return page;
			
			// 英字と数字がどちらも含まれており、かつ半角英数字のみであること
		} else if (!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$")) {
			request.setAttribute("errMsg", "パスワードは半角英字と数字を組み合わせて入力してください");
			page = "/WEB-INF/jsp/login.jsp";
			return page;
		}

		// ちゃんと両方入力されていたらserviceを実体化
		UserService service = new UserService();
		dto = service.login(loginId, password);

		// DTOがnullなら
		if (dto == null) {

			request.setAttribute("errMsg", "IDまたはパスワードが違います");
			page = "/WEB-INF/jsp/login.jsp";
			return page;

			// ちゃんと入っていたら
		} else {
			// ログインできた人の情報をsessionに保存
			HttpSession session = request.getSession();
			session.setAttribute("user", dto);

			// ホームアクションに飛ばし、ホーム画面でタスクやログを表示する
			HomeAction action = new HomeAction(request);
			page = action.homeSelectAll();

			return page;
		}

	}

	// パスワード変更メソッド----------------------------------------------------------
	public String passwordChange() throws UnsupportedEncodingException {

		// 返却する次の飛び先のURLを定義
		String page = "/WEB-INF/jsp/mypage.jsp";

		// 画面からの入力値を取得
		request.setCharacterEncoding("UTF-8");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("new-password");
		String newPasswordConfirm = request.getParameter("new-password-confirm");
		
		// パスワードの入力値チェック
		if (newPassword != newPasswordConfirm) {
			request.setAttribute("errMsg", "パスワードが一致しません");
			page = "/WEB-INF/jsp/mypage.jsp";
			return page;
		}
		
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
		UserService service = new UserService();
		int ans = service.passwordChange(userId, password, newPassword);

		// 変更結果の判定
		if (ans == 1) {
			request.setAttribute("msg", "パスワードを変更しました！");
		} else {
			request.setAttribute("errMsg", "※現在のパスワードが違います");
		}

		// マイページ表示用メソッド
		return mypageSelect();
	}

	// ログアウトメソッド----------------------------------------------------------
	public String logout() {

		// セッションを取得
		HttpSession session = request.getSession(false);

		// セッションが存在していれば破棄する
		if (session != null) {
			session.invalidate();
		}

		// ログアウト完了メッセージをリクエストに設定
		request.setAttribute("errMsg", "ログアウトしました");

		// ログイン画面へ遷移
		String page = "/WEB-INF/jsp/login.jsp";

		// 戻り値
		return page;
	}
	
	// マイページ表示用メソッド----------------------------------------------------------
	public String mypageSelect() {
		String page = "/WEB-INF/jsp/mypage.jsp";
		
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
		
		// Serviceを実体化して処理を行う
		UserService service = new UserService();
		UserDTO myDate = service.mypageSelect(userId);
		
		// 変更結果の判定
		if (myDate == null) {
			request.setAttribute("errMsg", "ユーザー情報の取得に失敗しました");
		} else {
			request.setAttribute("user", myDate);
			request.setAttribute("msg", "成功");
		}

		// 戻り値
		return page;
		
	}

}
