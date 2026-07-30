package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserDAO;
import model.UserDTO;

public class UserService {

	// データベース接続を保持する変数
	private Connection conn = null;

	// データベース接続用 ※「romance_magic」は、データベース名
//	private static final String url ="jdbc:mysql://localhost:3306/ysl3?useSSL=false&serverTimezone=Asia/Tokyo&characterEncoding=UTF-8";
//	private static final String dbUser = "ysl3";
//	private static final String dbPassword = "Kz3Dvi22zPhkEzDg";

	// ローカル接続用 ※「romance_magic」は、データベース名
	private static final String url = "jdbc:mysql://localhost:3306/romance_magic?useSSL=false&serverTimezone=Asia/Tokyo&characterEncoding=UTF-8";
	private static final String dbUser = "root";
	private static final String dbPassword = "password";
	
	
	// データベースとの接続を行うメソッド
	private void access() {
		   try {
		       // MySQLドライバーを読み込む
		       Class.forName(
		               "com.mysql.cj.jdbc.Driver"
		       );
		       // DBへ接続
		       conn = DriverManager.getConnection(url, dbUser, dbPassword);
		   } catch (ClassNotFoundException e) {
		       throw new RuntimeException("MySQLドライバーが見つかりません", e);
		   } catch (SQLException e) {
		       throw new RuntimeException("データベースへの接続に失敗しました", e);
		   }
		}

	// データベースとの接続を切断するメソッド
	private void close() {
		   if (conn == null) {
		       return;
		   }
		   try {
		       conn.close();
		   } catch (SQLException e) {
		       throw new RuntimeException("データベースの切断に失敗しました", e);
		   } finally {
		       conn = null;
		   }
		}

	// ログインメソッド----------------------------------------------------------
	public UserDTO login(String loginId, String password) {
		UserDTO dto = null;

		// DB接続
		access();

		try {
			// DAOを実体化
			UserDAO dao = new UserDAO(this.conn);
			
			// ログイン処理を実施。DAOのメソッドを実行
			dto = dao.login(loginId, password);
			
		} finally {
			
			// DB接続解除
			close();
		}
		
		// 戻り値
		return dto;
	}
	
	// パスワード変更メソッド----------------------------------------------------------
	public int passwordChange(int userId, String password, String newPassword) {
		int ans = 0;

		// DB接続
		access();
		
		try {
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// パスワード変更処理を実施。DAOのメソッドを実行
			ans = dao.passwordChange(userId, password, newPassword);
			
		} finally {
			
			// DB接続解除
			close();
		}
		
		// 戻り値
		return ans;
	}
	
	// ユーザー一覧を取得するメソッド（管理者）----------------------------------------------------------
	public ArrayList<UserDTO> userSelectAll() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		// DB接続
		access();
		
		try {
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー一覧取得処理を実施。DAOのメソッドを実行
			userList = dao.userSelectAll();
			
		} finally {
			
			// DB接続解除
			close();
		}
		
		// 戻り値
		return userList;
	}
	
	
//	//	ユーザーを検索するメソッド（管理者）----------------------------------------------------------JSでやるかも
//	public ArrayList<UserDTO> userSearch(String keyword) {
//		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
//		
//		// DB接続
//		access();
//		
//		try {
//			// DAOを実体化
//			UserDAO dao = new UserDAO(conn);
//			
//			// ユーザー検索処理を実施。DAOのメソッドを実行
//			userList = dao.userSearch(keyword);
//			
//		} finally {
//			
//			// DB接続解除
//			close();
//		}
//		
//		
//		
//		// 戻り値
//		return userList;
//	}
	
	
	// ユーザー登録メソッド（管理者）----------------------------------------------------------
	public int userRegist(String loginId, String userName, String mail, String password, int role) {
		int ans = 0;
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.userRegist(loginId, userName, mail, password, role);
		} finally {
			
			// DB接続解除
			close();
		}
		
		
		// 戻り値
		return ans;
	}
	
	// ユーザー編集メソッド（管理者）----------------------------------------------------------
	public int userEdit(int userId, String userName, int role, String mail, int sol) {
		int ans = 0;
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー編集処理を実施。DAOのメソッドを実行
			ans = dao.userEdit(userId, userName, role, mail, sol);
		} finally {
			
			// DB接続解除
			close();
		}
		
		// 戻り値
		return ans;
	}
	
	
	//	案件に紐づけられてるユーザー取得メソッド（案件登録の際のPM選ぶ用）----------------------------------------------------------
	public ArrayList<UserDTO> selectProjectUserName() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー取得処理を実施。DAOのメソッドを実行
			userList = dao.selectProjectUserName();
		} finally {
			
			// DB接続解除
			close();
		}
		return userList;
	}
	
//	案件に紐づけられてるユーザー取得メソッド（案件編集の際のPM選ぶ用）----------------------------------------------------------
	public ArrayList<UserDTO> selectProjectUserNamePlus() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー取得処理を実施。DAOのメソッドを実行
			userList = dao.selectProjectUserNamePlus();
		} finally {
			
			// DB接続解除
			close();
		}
		return userList;
	}
	
	//	タスクに紐づけられているユーザー取得メソッド（タスク登録の際の担当者選ぶ用）----------------------------------------------------------
	public ArrayList<UserDTO> selectTaskUserName() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー編集処理を実施。DAOのメソッドを実行
			userList = dao.selectTaskUserName();
		} finally {
			
			// DB接続解除
			close();
		}
		
		return userList;
	}
	
	// マイページでユーザーの情報を表示するメソッド----------------------------------------------------------
	public UserDTO mypageSelect(int userId) {
		UserDTO dto = null;
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// マイページ表示処理を実施。DAOのメソッドを実行
			dto = dao.mypageSelect(userId);
		} finally {
			
			// DB接続解除
			close();
		}
		
		// 戻り値
		return dto;
	}
	
	// ユーザー編集画面でユーザーの情報を表示するメソッド
	public UserDTO memberToEdit(int userId) {
		UserDTO dto = null;
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザーの情報表示処理を実施。DAOのメソッドを実行
			dto = dao.memberToEdit(userId);
		} finally {
			
			// DB接続解除
			close();
		}
		
		// 戻り値
		return dto;
	}
	
}
