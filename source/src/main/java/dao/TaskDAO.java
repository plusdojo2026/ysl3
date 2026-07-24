package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TaskDTO;

public class TaskDAO {
	Connection conn = null;

	//	コンストラクタ
	public TaskDAO(Connection conn) {
		this.conn = conn;
	}

	//	タスク一覧を表示するメソッド
	public ArrayList<TaskDTO> taskSelectAll(int userId) throws SQLException {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	(処理)s
		// SELECT文を準備する
		String sql = "SELECT * FROM tasks WHERE user_id = ? ORDER BY number";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, userId);
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		while (rs.next()) {
			TaskDTO dto = new TaskDTO();
			dto.setTaskId(rs.getInt("task_id"));
			dto.setUserId(rs.getInt("user_id"));
			dto.setName(rs.getString("task_name"));
			dto.setStatus(rs.getInt("task_status"));
			dto.setProjectId(rs.getInt("project_id"));
			dto.setPriority(rs.getInt("task_priority"));
			dto.setLimitDate(rs.getString("task_limit"));
			dto.setEstimatedWork(rs.getFloat("task_estimated_work"));
			dto.setProgress(rs.getInt("progress"));
			
			taskList.add(dto);
		}

		return taskList;

	}

	//	タスクを検索するメソッド
	public ArrayList<TaskDTO> taskSearch(int taskId, int userId, String name, int status, int projectId)
			throws SQLException {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ

		// SELECT文を準備する
		String sql = "SELECT task_id,user_id,task_name,task_status,project_id,task_priority,"
				+ "task_limit,task_estimated_work,progress FROM tasks WHERE name LIKE ?";

		
		//project_id
		if ((Integer) projectId != null) {

			sql += " AND project_id = ?";
		}
		//status
		if ((Integer) status != null) {
			
			sql += " AND task_status = ?";
		}
		
		//user_id,
		if ((Integer) userId != null) {
			
			sql += " AND task_status = ?";
		}
						
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// SQL文を完成させる	
		//name
		if (name != null) {
			pStmt.setString(1, "%" + name + "%");
		} else {
			pStmt.setString(1, "%");
		}

		//project_id
		int index = 1;
		
		if ((Integer) projectId != null) {

			pStmt.setInt(index++, projectId);
		}

		//status
		if ((Integer) status != null) {

			pStmt.setInt(index++, status);
		}
		
		
		//user_id,
		if ((Integer) userId != null) {

			pStmt.setInt(index++, userId);
		}
		
		
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		while (rs.next()) {
			TaskDTO dto = new TaskDTO();
			dto.setTaskId(rs.getInt("task_id"));
			dto.setUserId(rs.getInt("user_id"));
			dto.setName(rs.getString("task_name"));
			dto.setStatus(rs.getInt("task_status"));
			dto.setProjectId(rs.getInt("project_id"));
			dto.setPriority(rs.getInt("task_priority"));
			dto.setLimitDate(rs.getString("task_limit"));
			dto.setEstimatedWork(rs.getFloat("task_estimated_work"));
			dto.setProgress(rs.getInt("progress"));
			taskList.add(dto);
		}

		return taskList;

	}

	//	タスク登録メソッド
	public int taskRegist(int taskId, int userId, String name, int status, int priority,
			String limitDate, String explainText, float estimatedWorks, int projectId, String startDate, int progress)
			throws SQLException {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		// SELECT文を準備する
		String sql = "INSERT INTO tasks VALUES(?,?,?,?,?,?,?,?,?,?,?)WHERE user_id = ?";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, taskId);
		pStmt.setInt(2, userId);
		pStmt.setString(3, name);
		pStmt.setInt(4, status);
		pStmt.setInt(5, priority);
		pStmt.setString(6, limitDate);
		pStmt.setString(7, explainText);
		pStmt.setFloat(8, estimatedWorks);
		pStmt.setInt(9, projectId);
		pStmt.setString(10, startDate);
		pStmt.setInt(11, progress);

		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		while (rs.next()) {
			TaskDTO dto = new TaskDTO();
			dto.setTaskId(rs.getInt("task_id"));
			dto.setUserId(rs.getInt("user_id"));
			dto.setName(rs.getString("task_name"));
			dto.setStatus(rs.getInt("task_status"));
			dto.setPriority(rs.getInt("task_priority"));
			dto.setExplainText(rs.getString("task_explanation"));
			dto.setLimitDate(rs.getString("task_limit"));
			dto.setEstimatedWork(rs.getFloat("task_estimated_work"));
			dto.setProjectId(rs.getInt("project_id"));
			dto.setStartDate(rs.getString("task_start_date"));
			dto.setProgress(rs.getInt("progress"));
		}
		return ans;
	}

	//	タスク編集メソッド
	public int taskUpdate(int taskId, int userId, String name, int status, int priority,
			String explainText, String limitDate, float estimatedWorks, int projectId, String startDate, int progress)
			throws SQLException {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		// SELECT文を準備する
		String sql = "UPDATE task SET taskId=?,userId=?,name=?, status=?, priority=?,"
				+ "limitDate=?, explanationText=? ,estimatedWorks=?, projectId=?,"
				+ "startDate=?,progress=? WHERE user_id = ?";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, taskId);
		pStmt.setInt(2, userId);
		pStmt.setString(3, name);
		pStmt.setInt(4, status);
		pStmt.setInt(5, priority);
		pStmt.setString(6, limitDate);
		pStmt.setString(7, explainText);
		pStmt.setFloat(8, estimatedWorks);
		pStmt.setInt(9, projectId);
		pStmt.setString(10, startDate);
		pStmt.setInt(11, progress);

		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		while (rs.next()) {
			TaskDTO dto = new TaskDTO();
			dto.setTaskId(rs.getInt("task_id"));
			dto.setUserId(rs.getInt("user_id"));
			dto.setName(rs.getString("task_name"));
			dto.setStatus(rs.getInt("task_status"));
			dto.setPriority(rs.getInt("task_priority"));
			dto.setExplainText(rs.getString("task_explanation"));
			dto.setLimitDate(rs.getString("task_limit"));
			dto.setEstimatedWork(rs.getFloat("task_estimated_work"));
			dto.setProjectId(rs.getInt("project_id"));
			dto.setStartDate(rs.getString("task_start_date"));
			dto.setProgress(rs.getInt("progress"));
		}
		return ans;
	}

	//	タスク削除メソッド
	public int taskDelete(int taskId, int userId) throws SQLException {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		// SQL文を準備する
		String sql = "DELETE FROM tasks WHERE task_id = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setInt(1, taskId);

		return ans;
	}

	//	タスクステータス更新メソッド
	public int statusChange(int taskId, int userId) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

	//	タスク詳細を表示するメソッド
	public TaskDTO taskDetail(int taskId, int userId) throws SQLException {
		TaskDTO dto = null;
		//	処理はのちに記述。今は返すだけ
		String sql = "SELECT FROM tasks WHERE task_id = ? ";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// SQL文を完成させる
		pStmt.setInt(1, taskId);
		
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		while (rs.next()) {
		dto.setTaskId(rs.getInt("task_id"));
		dto.setUserId(rs.getInt("user_id"));
		dto.setName(rs.getString("task_name"));
		dto.setStatus(rs.getInt("task_status"));
		dto.setPriority(rs.getInt("task_priority"));
		dto.setExplainText(rs.getString("task_explanation"));
		dto.setLimitDate(rs.getString("task_limit"));
		dto.setEstimatedWork(rs.getFloat("task_estimated_work"));
		dto.setProjectId(rs.getInt("project_id"));
		dto.setStartDate(rs.getString("task_start_date"));
		dto.setProgress(rs.getInt("progress"));
				}

		return dto;
	}

	//  ホーム画面のタスク一覧を表示するメソッド
	public ArrayList<TaskDTO> homeTaskList(int taskId, int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;

	}

	//  案件一覧画面のタスク項目を表示するメソッド
	public ArrayList<TaskDTO> projectList(int taskId, int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;

	}

	//  案件詳細画面のタスク項目を削除するメソッド
	public ArrayList<TaskDTO> projectDetailDelete(int taskId, int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;

	}

	//   工数を登録するメソッド
	public int workRegist(int taskId, int userId) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		
		
		return ans;
	}

}