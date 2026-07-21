package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserDTO;

public class UserDAO {
	Connection conn = null;
	
	//	コンストラクタ
	public UserDAO(Connection conn) {
		this.conn = conn;
	}
	
	//	ログインメソッド
	public UserDTO login(String loginId, String password) {
		UserDTO  dto = null;
		//	処理はのちに記述。今は返すだけ
		return dto;
	}
	
	
	//	パスワード変更メソッド
	public int passwordChange(String userId, String password, String newPassword) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
	
	
	//	ユーザー一覧を表示するメソッド（管理者）
	public ArrayList<UserDTO> userSelectAll() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		//	処理はのちに記述。今は返すだけ
		return userList;
		
	}
	
	//	ユーザーを検索するメソッド（管理者）
	public ArrayList<UserDTO> userSearch(String userName) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		//	処理はのちに記述。今は返すだけ
		return userList;
	}
	
	//	ユーザー登録メソッド（管理者）
	public int userRegist(String loginId, String userName, String mail, String password, int role) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

	//	ユーザー編集メソッド（管理者）
	public int userEdit(String userName, int role, String mail, int sol) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

	//	案件に紐づけられてるユーザー取得メソッド
	public ArrayList<UserDTO> selectProjectUserName() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		//	処理はのちに記述。今は返すだけ
		return userList;
	}
	
	//	タスクに紐づけられているユーザー取得メソッド
	public ArrayList<UserDTO> selectTaskUserName(int projectId) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		//	処理はのちに記述。今は返すだけ
		return userList;
	}
}
