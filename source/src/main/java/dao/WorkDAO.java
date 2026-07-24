package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.WorkDTO;

public class WorkDAO {
	Connection conn = null;

	//コンストラクタ
	public WorkDAO(Connection conn) {
		this.conn = conn;
	}

	//工数一覧を表示するメソッド
	public ArrayList<WorkDTO> workSelectAll(int userId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();

		// SELECT文を準備する
		String sql = "SELECT * FROM work WHERE user_id = ?";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);

			try (
					ResultSet rs = pStmt.executeQuery()) {
		// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setId(rs.getInt("id"));
					dto.setTaskId(rs.getInt("task_id"));
					dto.setWork(rs.getFloat("work"));
					dto.setExplainText(rs.getString("explain_text"));
					dto.setWorkDate(rs.getString("work_date"));
					workList.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workList;
	}

	//工数登録するメソッド
	public int workRegist(int userId, int projectId, int taskId, String workDate, String explainText, String work) {
		int ans = 0;
		//SQLを準備
		String sql = "INSERT INTO works VALUES (?,?,?,?,?,?)";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);
			pStmt.setInt(2, projectId);
			pStmt.setInt(3, taskId);
			pStmt.setString(4, workDate);
			pStmt.setString(5, explainText);
			pStmt.setString(6, work);

			ans = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}

	//工数を削除するメソッド
	public int workDelete(int workId) {
		int ans = 0;

		String sql = "DELETE FROM works WHERE work_id = ?";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			// ?に値をセット   
			pStmt.setInt(1, workId);

			// DELETE文を実行し、削除された行数を取得する
			ans = pStmt.executeUpdate();

		} catch (SQLException e) {

		}

		return ans;

	}

	//工数を計算するメソッド
	public int workTally(int workId, int tasklId) {
		int ans = 0;
		//処理はのちに記述。今は返すだけ
		
		return ans;
	}

	//ホームに工数ログを表示するメソッド
	public ArrayList<WorkDTO> homeWorkList(int userId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
		// SELECT文を準備する
		String sql = "SELECT * FROM work WHERE user_id = ?";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);

			try (ResultSet rs = pStmt.executeQuery()) {
				// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setId(rs.getInt("id"));
					dto.setTaskId(rs.getInt("task_id"));
					dto.setWork(rs.getFloat("work"));
					dto.setExplainText(rs.getString("explain_text"));
					dto.setWorkDate(rs.getString("work_date"));
					workList.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workList;
	}
}