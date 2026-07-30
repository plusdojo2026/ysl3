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
		String sql = "SELECT * FROM works WHERE user_id = ? limit 10";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);

			try (
					ResultSet rs = pStmt.executeQuery()) {
				// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setId(rs.getInt("work_id"));
					dto.setTaskId(rs.getInt("task_id"));
					dto.setWork(rs.getFloat("work"));
					dto.setExplainText(rs.getString("work_explanation"));
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
	public int workRegist(int userId,int taskId,float work,String explainText,String workDate) {
		int ans = 0;
		//SQLを準備
		String sql = "INSERT INTO works VALUES (0,?, ?, ?, ?, ?)";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			
			pStmt.setInt(1, userId);
			pStmt.setInt(2, taskId);
			pStmt.setFloat(3, work);
			pStmt.setString(4, explainText);
			pStmt.setString(5, workDate);

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
	public int workTally(int workId, int taskId) {
		int ans = 0;

		String sql = "SELECT ";
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, workId);
			pStmt.setInt(2, taskId);

			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next()) {
					ans = rs.getInt(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}

	//ホームに工数ログを表示するメソッド
	public ArrayList<WorkDTO> homeWorkList(int userId) throws SQLException {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();

		// 定義
		String sql = null;
		PreparedStatement pStmt = null;

		if (userId == 0) {

			sql = "SELECT * FROM works LIMIT 10";
			pStmt = conn.prepareStatement(sql);
		} else {
			// SELECT文を準備する
			sql = "SELECT * FROM works WHERE user_id = ? limit 10";

			// まとめる
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
		}

		ResultSet rs = pStmt.executeQuery();

		// 移し替え
		while (rs.next()) {
			WorkDTO dto = new WorkDTO();
			dto.setId(rs.getInt("work_id"));
			dto.setTaskId(rs.getInt("task_id"));
			dto.setWork(rs.getFloat("work"));
			dto.setExplainText(rs.getString("work_explanation"));
			dto.setWorkDate(rs.getString("work_date"));
			workList.add(dto);
		}

		return workList;
	}

	//タスク詳細に工数ログを表示するメソッド
	public ArrayList<WorkDTO> TaskWorkList(int userId, int taskId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
		
		// SELECT文を準備する
		String sql = "SELECT work_explanation,w.work_id,w.task_id, t.task_name,work_date,work,w.user_id FROM works as w join tasks  AS t on w.task_id = t.task_id WHERE w.user_id = ? AND w.task_id = ? LIMIT 10;";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);
			pStmt.setInt(2, taskId);

			try (ResultSet rs = pStmt.executeQuery()) {
				// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setExplainText(rs.getString("work_explanation"));
					dto.setId(rs.getInt("work_id"));
					dto.setTaskId(rs.getInt("task_id"));
					dto.setTaskName(rs.getString("task_name"));
//					dto.setUserName(rs.getString("user_name"));
					dto.setWorkDate(rs.getString("work_date"));
					dto.setWork(rs.getFloat("work"));
					workList.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workList;
	}

	//案件詳細に工数ログを表示するメソッド
	public ArrayList<WorkDTO> ProjectWorkList(int userId, int projectId) {
		ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
		// SELECT文を準備する
		String sql = "SELECT work_explanation,project_id,w.work_id,w.task_id, t.task_name,work_date,work,w.user_id, u.user_name FROM works as w join tasks  AS t on w.task_id = t.task_id LEFT JOIN users AS u ON w.user_id = u.user_id WHERE w.user_id = ? AND t.project_id = ?";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);
			pStmt.setInt(2, projectId);

			try (ResultSet rs = pStmt.executeQuery()) {
				// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setExplainText(rs.getString("work_explanation"));
					dto.setId(rs.getInt("work_id"));
					dto.setTaskId(rs.getInt("task_id"));
					dto.setTaskName(rs.getString("task_name"));
					dto.setUserName(rs.getString("user_name"));
					dto.setWorkDate(rs.getString("work_date"));
					dto.setWork(rs.getFloat("work"));
					workList.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workList;
	}

	//工数登録画面で案件名とタスク名を表示する	
	public WorkDTO workToRegist(int taskId) {
		WorkDTO ans = null;

		String sql = "SELECT * FROM works WHERE task_id = ?";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, taskId);

			try (ResultSet rs = pStmt.executeQuery()) {
				// 移し替え
				while (rs.next()) {
					WorkDTO dto = new WorkDTO();
					dto.setId(rs.getInt("work_id"));
					dto.setTaskId(rs.getInt("task_id"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ans;
	}

	//サマリーカード
	//稼働メンバー
	public int getMemberCount(String month) {

		int MemberCount = 0;

		//SQL文
		String sql = "SELECT COUNT(DISTINCT user_id) AS memberCount "
				+ "FROM works "
				+ "WHERE DATE_FORMAT(work_date,'%Y-%m') = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql);) {

			// 対象年月をセット
			ps.setString(1, month);

			//SQL文を実行
			ResultSet rs = ps.executeQuery();

			// 取得結果が存在する場合
			if (rs.next()) {

				// 稼働メンバーを取得
				MemberCount = rs.getInt("MemberCount");
			}

		} catch (SQLException e) {

			// SQL実行時のエラー表示
			e.printStackTrace();
		}

		// 稼働メンバーを返す
		return MemberCount;
	}

}