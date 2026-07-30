package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProjectDTO;
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
		//	(処理)
		// SELECT文を準備する
		String sql = "SELECT tasks.*, user_name, project_name FROM tasks "
				+ "LEFT JOIN users ON tasks.user_id = users.user_id "
				+ "LEFT JOIN projects ON tasks.project_id = projects.project_id ";

		if (userId != 0) {

			sql += "WHERE tasks.user_id = ? ";
		}
		sql += "ORDER BY tasks.update_at DESC";

		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		if (userId != 0) {
			pStmt.setInt(1, userId);
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
			dto.setEstimatedWorks(rs.getFloat("task_estimated_works"));
			dto.setProgress(rs.getInt("progress"));

			dto.setProjectName(rs.getString("project_name"));
			dto.setUserName(rs.getString("user_name"));

			taskList.add(dto);
		}

		return taskList;

	}

	//	ユーザーがタスクとして追加したprojectの一覧
	public ArrayList<ProjectDTO> projectSelectAll(int userId) throws SQLException {
		ArrayList<ProjectDTO> projectList = new ArrayList<ProjectDTO>();
		//	(処理)
		// SELECT文を準備する

		String sql = "SELECT DISTINCT projects.project_id, projects.project_name "
				+ "FROM projects "
				+ "LEFT JOIN tasks ON projects.project_id = tasks.project_id ";

		if (userId != 0) {
			sql += "WHERE projects.pm_id = ? OR tasks.user_id = ? ";
		}
		sql += "ORDER BY projects.project_code";

		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		PreparedStatement pStmt = conn.prepareStatement(sql);

		if (userId != 0) {
			pStmt.setInt(1, userId);
			pStmt.setInt(2, userId);
		}
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		while (rs.next()) {
			ProjectDTO dto = new ProjectDTO();
			dto.setId(rs.getInt("project_id"));
			dto.setName(rs.getString("project_name"));
			projectList.add(dto);
		}

		return projectList;

	}

	// タスク検索メソッド
	public ArrayList<TaskDTO> taskSearch(int userId, String name, int status, int projectId) throws SQLException {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		String sql = "SELECT tasks.*, projects.project_name, users.user_name "
				+ "FROM tasks "
				+ "LEFT JOIN projects "
				+ "ON tasks.project_id = projects.project_id "
				+ "LEFT JOIN users "
				+ "ON tasks.user_id = users.user_id "
				+ "WHERE tasks.task_name LIKE ? ";
		// 一般ユーザーは自分の担当タスクだけ
		if (userId != 0) {
			sql += "AND tasks.user_id = ? ";
		}
		// 案件が選択されている場合
		if (projectId != -1) {
			sql += "AND tasks.project_id = ? ";
		}
		// ステータスが選択されている場合
		if (status != -1) {
			sql += "AND tasks.task_status = ? ";
		}
		sql += "ORDER BY tasks.update_at DESC";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		int index = 1;
		// タスク名
		if (name == null) {
			pStmt.setString(index, "%");
		} else {
			pStmt.setString(index, "%" + name + "%");
		}
		index++;
		// 一般ユーザーのユーザーID
		if (userId != 0) {
			pStmt.setInt(index, userId);
			index++;
		}
		// 案件ID
		if (projectId != -1) {
			pStmt.setInt(index, projectId);
			index++;
		}
		// ステータス
		if (status != -1) {
			pStmt.setInt(index, status);
		}
		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			TaskDTO dto = new TaskDTO();
			dto.setTaskId(rs.getInt("task_id"));
			dto.setUserId(rs.getInt("user_id"));
			dto.setName(rs.getString("task_name"));
			dto.setStatus(rs.getInt("task_status"));
			dto.setPriority(rs.getInt("task_priority"));
			dto.setLimitDate(rs.getString("task_limit"));
			dto.setEstimatedWorks(rs.getFloat("task_estimated_works"));
			dto.setProjectId(rs.getInt("project_id"));
			dto.setProgress(rs.getInt("progress"));
			dto.setProjectName(rs.getString("project_name"));
			dto.setUserName(rs.getString("user_name"));
			taskList.add(dto);
		}
		rs.close();
		pStmt.close();
		return taskList;
	}

	//	タスク登録メソッド
	public int taskRegist(int userId, String name, int status, int priority,
			String limitDate, String explainText, float estimatedWorks, int projectId, String startDate, int progress)
			throws SQLException {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		// SELECT文を準備する
		String sql = "INSERT INTO tasks "
				+ "(user_id, task_name, task_status, task_priority, task_limit, "
				+ "task_explanation, task_estimated_works, project_id, task_start_date, progress) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);

		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, userId);
		pStmt.setString(2, name);
		pStmt.setInt(3, status);
		pStmt.setInt(4, priority);
		pStmt.setString(5, limitDate);
		pStmt.setString(6, explainText);
		pStmt.setFloat(7, estimatedWorks);
		pStmt.setInt(8, projectId);
		pStmt.setString(9, startDate);
		pStmt.setInt(10, progress);

		// SELECT文を実行し、結果表を取得する
		ans = pStmt.executeUpdate();

		return ans;
	}

	// タスク編集メソッド
	public int taskUpdate(int taskId, int userId, String name, int status, int priority,
			String explainText, String limitDate, float estimatedWorks, int projectId, String startDate, int progress)
			throws SQLException {
		int ans = 0;

		String sql = "UPDATE tasks SET user_id=?, task_name=?, task_status=?, task_priority=?,"
				+ " task_limit=?, task_explanation=?, task_estimated_works=?, project_id=?,"
				+ " task_start_date=?, progress=? WHERE task_id = ?";

		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, userId);
		pStmt.setString(2, name);
		pStmt.setInt(3, status);
		pStmt.setInt(4, priority);
		pStmt.setString(5, limitDate);
		pStmt.setString(6, explainText);
		pStmt.setFloat(7, estimatedWorks);
		pStmt.setInt(8, projectId);
		pStmt.setString(9, startDate);
		pStmt.setInt(10, progress);
		pStmt.setInt(11, taskId);

		ans = pStmt.executeUpdate();

		return ans;
	}

	//	タスク削除メソッド
	public int taskDelete(int taskId) throws SQLException {
		int ans = 0;

		// SQL文を準備する
		String sql = "DELETE FROM tasks WHERE task_id = ?";

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, taskId);

			// SQLを実行して、削除されたかどうかを受け取る
			ans = pStmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("タスク削除処理中にDBエラーが発生しました", e);
		}

		return ans;
	}

	// タスクステータス変更メソッド
	public int statusChange(int taskId, int taskStatus, int userId) {

		int ans = 0;
		String sql = "UPDATE tasks SET task_status = ?";

		// 完了へ変更する場合は進捗率を100％にする
		if (taskStatus == 2) {
			sql += ", progress = 100";

			// 未着手へ変更する場合は進捗率を0％にする
		} else if (taskStatus == 0) {
			sql += ", progress = 0";
		}
		sql += " WHERE task_id = ?";

		// 一般ユーザーは自分の担当タスクだけ変更可能
		if (userId != 0) {
			sql += " AND user_id = ?";
		}

		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			int index = 1;

			// 変更後のステータス
			pStmt.setInt(index, taskStatus);
			index++;

			// 変更するタスクID
			pStmt.setInt(index, taskId);
			index++;

			// 一般ユーザーの場合だけユーザーIDを設定
			if (userId != 0) {
				pStmt.setInt(index, userId);
			}
			ans = pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("タスクステータス変更中にDBエラーが発生しました", e);
		}
		return ans;
	}

	//	タスク詳細を表示するメソッド
	public TaskDTO taskDetail(int taskId) throws SQLException {
		TaskDTO dto = null;
		//	処理はのちに記述。今は返すだけ
		String sql = "SELECT tasks.task_id, project_name,task_name,task_status,task_priority,task_estimated_works,"
				+ "progress,task_start_date,task_limit,task_explanation,user_name, tasks.project_id FROM tasks "
				+ "LEFT JOIN users ON tasks.user_id = users.user_id "
				+ "LEFT JOIN projects ON tasks.project_id = projects.project_id "
				+ "WHERE task_id = ? ";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setInt(1, taskId);

		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		if (rs.next()) {
			dto = new TaskDTO();
			dto.setTaskId(rs.getInt("task_id"));
			dto.setName(rs.getString("task_name"));
			dto.setStatus(rs.getInt("task_status"));
			dto.setPriority(rs.getInt("task_priority"));
			dto.setExplainText(rs.getString("task_explanation"));
			dto.setLimitDate(rs.getString("task_limit"));
			dto.setEstimatedWorks(rs.getFloat("task_estimated_works"));
			dto.setStartDate(rs.getString("task_start_date"));
			dto.setProgress(rs.getInt("progress"));
			dto.setProjectId(rs.getInt("project_id"));
			dto.setProjectName(rs.getString("project_name"));
			dto.setUserName(rs.getString("user_name"));
		}

		rs.close();
		pStmt.close();

		return dto;
	}

	// 新規登録初期表示用メソッド
	public TaskDTO taskToEdit(int taskId) throws SQLException {
		TaskDTO dto = null;

		// SQL文準備
		String sql = "SELECT tasks.*, users.user_name, projects.project_name FROM tasks "
				+ "LEFT JOIN users ON tasks.user_id = users.user_id "
				+ "LEFT JOIN projects ON tasks.project_id = projects.project_id "
				+ "WHERE tasks.task_id = ? ";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setInt(1, taskId);

		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//移し替え
		while (rs.next()) {
			dto = new TaskDTO();
			dto.setTaskId(rs.getInt("task_id"));
			dto.setUserId(rs.getInt("user_id"));
			dto.setName(rs.getString("task_name"));
			dto.setStatus(rs.getInt("task_status"));
			dto.setPriority(rs.getInt("task_priority"));
			dto.setExplainText(rs.getString("task_explanation"));
			dto.setLimitDate(rs.getString("task_limit"));
			dto.setEstimatedWorks(rs.getFloat("task_estimated_works"));
			dto.setProjectId(rs.getInt("project_id"));
			dto.setStartDate(rs.getString("task_start_date"));
			dto.setProgress(rs.getInt("progress"));

			dto.setProjectName(rs.getString("project_name"));
			dto.setUserName(rs.getString("user_name"));
		}

		// 戻り値
		return dto;
	}

	//  ホーム画面のタスク一覧を表示するメソッド
	public ArrayList<TaskDTO> homeTaskList(int userId) throws SQLException {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// SQL文準備
		String sql = null;
		PreparedStatement pStmt = null;

		// 管理者かどうかチェック
		if (userId == 0) {
			sql = "SELECT * FROM tasks";
			pStmt = conn.prepareStatement(sql);
		} else {
			// SELECT文を準備する
			sql = "SELECT * FROM tasks WHERE user_id = ? ORDER BY task_id";

			// まとめる
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
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
			dto.setEstimatedWorks(rs.getFloat("task_estimated_works"));
			dto.setProgress(rs.getInt("progress"));

			taskList.add(dto);
		}

		return taskList;

	}

	//   工数を登録するメソッド
	public int workRegist(int taskId, int userId) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ

		return ans;
	}

	//  案件一覧画面のタスク項目を表示するメソッド
	public ArrayList<TaskDTO> projectList(int projectId, int userId) throws SQLException {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	(処理)
		// SELECT文を準備する
		String sql = "SELECT tasks.*, users.user_name, projects.project_name "
				+ "FROM tasks "
				+ "LEFT JOIN users ON tasks.user_id = users.user_id "
				+ "LEFT JOIN projects ON tasks.project_id = projects.project_id "
				+ "WHERE projects.project_id = ? ";
		if (userId != 0) {
			sql += "AND tasks.user_id = ? ";
		}
		sql += "ORDER BY tasks.update_at DESC";

		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, projectId);

		if (userId != 0) {
			pStmt.setInt(2, projectId);

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
			dto.setEstimatedWorks(rs.getFloat("task_estimated_works"));
			dto.setProgress(rs.getInt("progress"));

			dto.setProjectName(rs.getString("project_name"));
			dto.setUserName(rs.getString("user_name"));

			taskList.add(dto);
		}

		return taskList;

	}

	//  案件詳細画面のタスク項目を削除するメソッド
	public ArrayList<TaskDTO> projectDetailDelete(int taskId, int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;

	}

	//サマリーカード用
	//予定工数
	public float getPlannedWork(String month) {
		float PlannedWork = 0;

		//SQL文
		String sql = "SELECT\r\n"
				+ "SUM(task_estimated_works) AS planned_work\r\n"
				+ "FROM tasks\r\n"
				+ "WHERE DATE_FORMAT(task_start_date,'%Y-%m')=?";

		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			// 対象年月をセット
			ps.setString(1, month);

			//SQL文を実行
			ResultSet rs = ps.executeQuery();

			// 取得結果が存在する場合
			if (rs.next()) {

				// 予定工数を取得
				PlannedWork = rs.getFloat("planned_work");
			}

		} catch (SQLException e) {

			// SQL実行時のエラー表示
			e.printStackTrace();
		}

		// 予定工数を返す
		return PlannedWork;
	}

}