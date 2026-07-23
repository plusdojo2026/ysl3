package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserDTO;

public class UserDAO {

	public Connection conn = null;

	//	コンストラクタ
	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	//	ログインメソッド
	public UserDTO login(String loginId, String password) {

		// DTO実体化
		UserDTO dto = null;

		// SQL文準備
		String sql = "SELECT user_id, login_id, user_name, mail, role, sol FROM users WHERE login_id = ? AND password = ? AND sol = 1";
		try (

				// いつものやつ
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);

			try (
					ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					dto = new UserDTO();
					dto.setId(rs.getInt("user_id"));
					dto.setLoginId(rs.getString("login_id"));
					dto.setUserName(rs.getString("user_name"));
					dto.setMail(rs.getString("mail"));
					dto.setRole(rs.getInt("role"));
					dto.setSol(rs.getInt("sol"));
				}
			}
		} catch (SQLException e) {

			throw new RuntimeException("ログイン処理中にDBエラーが発生しました", e);
		}
		// 戻り値
		return dto;
	}

	//	パスワード変更メソッド
	public int passwordChange(int userId, String password, String newPassword) {
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

	//	案件に紐づけられてるユーザー取得メソッド（案件登録の際の担当者選ぶ用）
	public ArrayList<UserDTO> selectProjectUserName() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		//	処理はのちに記述。今は返すだけ
		return userList;
	}

	//	タスクに紐づけられているユーザー取得メソッド（タスク登録の際の担当者選ぶ用）
	public ArrayList<UserDTO> selectTaskUserName(int projectId) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		//	処理はのちに記述。今は返すだけ
		return userList;
	}
	
	// マイページにユーザーの情報表示用メソッド
	public UserDTO mypageSelect(int userId) {
		UserDTO dto = null;
		
		return dto;
	}
}
