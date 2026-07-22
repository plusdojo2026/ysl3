package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import model.UserDTO;

public class UserService {

	// データベース接続を保持する変数
	private Connection conn = null;

	// データベースとの接続を行うメソッド
	private void access() {
		// のちに処理記述
	}

	// データベースとの接続を切断するメソッド
	private void close() {
		// のちに処理記述	
	}

	// ログインメソッド
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
	
	// パスワード変更メソッド
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
	
	// ユーザー一覧を取得するメソッド（管理者）
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
	
	
	//	ユーザーを検索するメソッド（管理者）
	public ArrayList<UserDTO> userSearch(String userName) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		// DB接続
		access();
		
		try {
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー検索処理を実施。DAOのメソッドを実行
			userList = dao.userSearch(userName);
			
		} finally {
			
			// DB接続解除
			close();
		}
		
		
		
		// 戻り値
		return userList;
	}
	
	
	// ユーザー登録メソッド（管理者）
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
	
	// ユーザー編集メソッド（管理者）
	public int userEdit(String userName, int role, String mail, int sol) {
		int ans = 0;
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー編集処理を実施。DAOのメソッドを実行
			ans = dao.userEdit(userName, role, mail, sol);
		} finally {
			
			// DB接続解除
			close();
		}
		
		// 戻り値
		return ans;
	}
	
	
	//	案件に紐づけられてるユーザー取得メソッド（案件登録の際の担当者選ぶ用）
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
	
	//	タスクに紐づけられているユーザー取得メソッド（タスク登録の際の担当者選ぶ用）
	public ArrayList<UserDTO> selectTaskUserName(int projectId) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		// DB接続
		access();
		
		try {
			
			// DAOを実体化
			UserDAO dao = new UserDAO(conn);
			
			// ユーザー編集処理を実施。DAOのメソッドを実行
			userList = dao.selectTaskUserName(projectId);
		} finally {
			
			// DB接続解除
			close();
		}
		
		return userList;
	}
	
	
}
