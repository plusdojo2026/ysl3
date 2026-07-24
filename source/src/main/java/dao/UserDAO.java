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

	//	ログインメソッド----------------------------------------------------------
	public UserDTO login(String loginId, String password) {

		// DTO実体化
		UserDTO dto = null;

		// SQL文準備
		String sql = "SELECT user_id, login_id, user_name, mail, role, sol FROM users WHERE login_id = ? AND password = ? AND sol = 1";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);

			try (ResultSet rs = pStmt.executeQuery()) {
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

	//	パスワード変更メソッド----------------------------------------------------------
	public int passwordChange(int userId, String password, String newPassword) {
		int ans = 0;

		// SQL文準備
		String sql = "UPDATE users SET password = ? WHERE user_id = ? AND password = ?";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			// 変数を挿入
			pStmt.setString(1, newPassword);
			pStmt.setInt(2, userId);
			pStmt.setString(3, password);

			// SQLを実行して、更新されたかどうかを受け取る
			ans = pStmt.executeUpdate();

		} catch (SQLException e) {
			
			throw new RuntimeException("パスワード変更処理中にDBエラーが発生しました", e);
		}

		// 戻り値
		return ans;
	}

	//	ユーザー一覧を表示するメソッド（管理者）----------------------------------------------------------
	public ArrayList<UserDTO> userSelectAll() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();

		// DTO実体化
		UserDTO dto = null;

		// SQL文を準備する
		String sql = "SELECT * FROM users";

		try (
				PreparedStatement pStmt = conn.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery()) {

			// 結果を格納
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getInt("user_id"));
				dto.setLoginId(rs.getString("login_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setMail(rs.getString("mail"));
				dto.setRole(rs.getInt("role"));
				dto.setSol(rs.getInt("sol"));

				// リストに追加
				userList.add(dto);
			}

		} catch (SQLException e) {

			throw new RuntimeException("ユーザー一覧取得中にDBエラーが発生しました", e);
		}
		//serviceに返却する
		return userList;
	}

//	//	ユーザーを検索するメソッド（管理者）----------------------------------------------------------
//	public ArrayList<UserDTO> userSearch(String keyword) {
//		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
//		//	処理はのちに記述。今は返すだけ
//		return userList;
//	}

	//	ユーザー登録メソッド（管理者）----------------------------------------------------------
	public int userRegist(String loginId, String userName, String mail, String password, int role) {
		int ans = 0;
		
		// SQL文準備
		String sql = "INSERT INTO users (login_id, user_name, password, mail, role, sol) VALUES(?, ?, ?, ?, ?, 1)";
		
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			// 変数を挿入
			pStmt.setString(1, loginId);
			pStmt.setString(2, userName);
			pStmt.setString(3, password);
			pStmt.setString(4, mail);
			pStmt.setInt(5,  role);

			// SQLを実行して、更新されたかどうかを受け取る
			ans = pStmt.executeUpdate();

		} catch (SQLException e) {
			
			throw new RuntimeException("ユーザー登録処理中にDBエラーが発生しました", e);
		}
		
		// 戻り値
		return ans;
	}

	//	ユーザー編集メソッド（管理者）----------------------------------------------------------
	public int userEdit(int userId, String userName, int role, String mail, int sol) {
		int ans = 0;

		// SQL文準備
		String sql = "UPDATE users SET user_name = ?, role = ?, mail = ?, sol = ? WHERE user_id = ?";
		
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			// 変数を挿入
			pStmt.setString(1, userName);
			pStmt.setInt(2, role);
			pStmt.setString(3, mail);
			pStmt.setInt(4, sol);
			pStmt.setInt(5, userId);

			// SQLを実行して、更新されたかどうかを受け取る
			ans = pStmt.executeUpdate();

		} catch (SQLException e) {
			
			throw new RuntimeException("ユーザー編集処理中にDBエラーが発生しました", e);
		}
		
		// 戻り値
		return ans;

	}

	//	案件に紐づけられてるユーザー取得メソッド（案件登録の際の選ぶ用）----------------------------------------------------------
	public ArrayList<UserDTO> selectProjectUserName() {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		
		// DTO実体化
		UserDTO dto = null;

		// SQL文を準備する：有効なメンバー全員から選ぶ
		String sql = "SELECT * FROM users WHERE sol = 1";

		try (
				PreparedStatement pStmt = conn.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery()) {

			// 結果を格納
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getInt("user_id"));
				dto.setLoginId(rs.getString("login_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setMail(rs.getString("mail"));
				dto.setRole(rs.getInt("role"));
				dto.setSol(rs.getInt("sol"));

				// リストに追加
				userList.add(dto);
			}

		} catch (SQLException e) {

			throw new RuntimeException("ユーザー一覧取得中にDBエラーが発生しました", e);
		}
		//serviceに返却する
		return userList;
	}

	//	タスクに紐づけられているユーザー取得メソッド（タスク登録の際の担当者選ぶ用）----------------------------------------------------------
	public ArrayList<UserDTO> selectTaskUserName(int projectId) {
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();

		// DTO実体化
		UserDTO dto = null;

		// SQL文を準備する
		String sql = "SELECT * FROM users WHERE sol = 1 AND user_id IN (SELECT user_id FROM tasks WHERE project_id = ?)";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			
			// 変数セット
			pStmt.setInt(1,  projectId);
			
			try (ResultSet rs = pStmt.executeQuery()) {
				while (rs.next()) {
					dto = new UserDTO();
					dto.setId(rs.getInt("user_id"));
					dto.setLoginId(rs.getString("login_id"));
					dto.setUserName(rs.getString("user_name"));
					dto.setMail(rs.getString("mail"));
					dto.setRole(rs.getInt("role"));
					dto.setSol(rs.getInt("sol"));

					// リストに追加
					userList.add(dto);
				}
			}

		} catch (SQLException e) {

			throw new RuntimeException("ユーザー一覧取得中にDBエラーが発生しました", e);
		}
		//serviceに返却する
		return userList;
	}

	// マイページにユーザーの情報表示用メソッド----------------------------------------------------------
	public UserDTO mypageSelect(int userId) {
		UserDTO dto = null;
		
		// SQL文準備
		String sql = "SELECT user_id, login_id, user_name, mail, role, sol FROM users WHERE user_id = ?";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, userId);

			try (ResultSet rs = pStmt.executeQuery()) {
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

			throw new RuntimeException("SQL処理中にDBエラーが発生しました", e);
		}
		// 戻り値
		return dto;
	}
	
	// ユーザー編集画面にユーザーの情報を表示するメソッド----------------------------------------------------------
	public UserDTO memberToEdit(int userId) {
		UserDTO dto = null;
		
		// SQL文準備
		String sql = "SELECT user_id, login_id, user_name, mail, role, sol FROM users WHERE user_id = ?";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, userId);

			try (ResultSet rs = pStmt.executeQuery()) {
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

			throw new RuntimeException("SQL処理中にDBエラーが発生しました", e);
		}
		// 戻り値
		return dto;
	}
	
	
	
	
}
