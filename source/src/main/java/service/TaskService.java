package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.TaskDAO;
import model.TaskDTO;

public class TaskService {

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

	// タスク一覧を取得するメソッド
	public ArrayList<TaskDTO> taskSelectAll(int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.taskSelectAll(userId);

		} catch (Exception e) {
			// TODO: handle exception
		} 
		finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	// タスク検索をするメソッド
	public ArrayList<TaskDTO> taskSearch(int taskId, int projectId, int taskStatus, int userId, String taskName) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク検索を実施。DAOのメソッドを実行
			taskList = dao.taskSearch(taskId, projectId, taskStatus, taskName, userId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	// タスク登録メソッド
	public int taskRegist(int taskId, int userId, int projectId, String taskName, int taskStatus, int taskPriority,
			int progress, float taskEstimatedWorks, String taskStartDate, String taskLimit, String taskExplanation) {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.taskRegist(taskId, userId, projectId, taskName, taskStatus, taskPriority,
					progress, taskEstimatedWorks, taskStartDate, taskLimit, taskExplanation);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	// タスク編集メソッド
	public int taskUpdate(int taskId, int userId, int projectId, String taskName, int taskStatus, int taskPriority,
			int progress, float taskEstimatedWorks, String taskStartDate, String taskLimit, String taskExplanation) {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.taskUpdate(taskId, userId, projectId, taskName, taskStatus, taskPriority,
					progress, taskEstimatedWorks, taskStartDate, taskLimit, taskExplanation);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	// ユーザー削除メソッド
	public int taskDelete(int userId, int taskId) {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.taskDelete(userId, taskId);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	// タスクステータス変更メソッド
	public int taskStatusChange(int userId, int taskId) {
		int ans = 0;

		// DB接続
		access();

		try {

			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// ユーザー登録処理を実施。DAOのメソッドを実行
			ans = dao.taskStatusChange(userId, taskId);
		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return ans;
	}

	//タスク詳細を表示するメソッド
	public TaskDTO taskDetail(int userId, int taskId) {
		TaskDTO dto = null;

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(this.conn);

			// ログイン処理を実施。DAOのメソッドを実行
			dto = dao.taskDetail(userId, taskId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return dto;
	}

	//  ホーム画面のタスク一覧を表示するメソッド
	public ArrayList<TaskDTO> homeTaskList(int taskId, int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.homeTaskList(taskId, userId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	//  案件一覧画面のタスク項目を表示するメソッド
	public ArrayList<TaskDTO> projectTaskList(int taskId, int userId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.projectList(taskId, userId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	//  案件詳細画面のタスク項目を削除するメソッド
	public ArrayList<TaskDTO> projectDetailDelete(int userId, int taskId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();

		// DB接続
		access();

		try {
			// DAOを実体化
			TaskDAO dao = new TaskDAO(conn);

			// タスク一覧取得処理を実施。DAOのメソッドを実行
			taskList = dao.projectDetailDelete(userId, taskId);

		} finally {

			// DB接続解除
			close();
		}

		// 戻り値
		return taskList;
	}

	//   工数を登録するメソッド
	public int workRegist(int userId, int taskId) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
}
