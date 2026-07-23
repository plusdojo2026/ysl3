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
	public ArrayList<TaskDTO> taskSelectAll(int userId) throws SQLException{
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	(処理)s
		// SELECT文を準備する
				String sql ="SELECT * FROM task WHERE user_id = ?";
				//デバッグ（SQL文の確認用）
				System.out.println(sql);
				
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, userId);
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while(rs.next()) {
		TaskDTO dto = new TaskDTO(userId);			
		dto.setTaskId(rs.getInt("task_id"));
		dto.setProjectId(rs.getInt("project_id"));
		dto.setName(rs.getString("task_name"));
		dto.setStatus(rs.getInt("status"));
		dto.setLimitDate(rs.getString("limit_date"));
		dto.setEstimatedWork(rs.getFloat("estimated_work"));
		dto.setTotalWork(rs.getInt("total_work"));
		dto.setUserId(rs.getInt("user_id"));
		dto.setProgress(rs.getInt("progress"));
		taskList.add(dto);
				}
		
		return taskList;
		
	}
	
	//	タスクを検索するメソッド
	public ArrayList<TaskDTO> taskSearch(int projectId, int taskStatus,int userId,String taskName, int taskId) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
	}
	
	//	タスク登録メソッド
	public int taskRegist( int taskId,int userId,int projectId,String taskName, int taskStatus, int taskPriority,
			int progress,float taskEstimatedWorks, String taskStartDate, String taskLimit,String taskExplanation) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

	//	タスク編集メソッド
	public int taskUpdate( int taskId, int userId,int projectId,String taskName, int taskStatus, int taskPriority,
			int progress,float taskEstimatedWorks, String taskStartDate, String taskLimit,String taskExplanation) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
			
    //	タスク削除メソッド
	public int taskDelete(int userId,int taskId) {
	     int ans = 0;
		//	処理はのちに記述。今は返すだけ
		 return ans;
     }

	 //	タスクステータス更新メソッド
	public int taskStatusChange(int userId,int taskId){
	     int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
			
     //	タスク詳細を表示するメソッド
	public TaskDTO taskDetail(int userId,int taskId) {
		TaskDTO  dto = null;
		//	処理はのちに記述。今は返すだけ
		return dto;
	}
	
	
     //  ホーム画面のタスク一覧を表示するメソッド
	public ArrayList<TaskDTO> homeTaskList( int taskId,int userId) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
			
	}
				
	 //  案件一覧画面のタスク項目を表示するメソッド
    public ArrayList<TaskDTO> projectList( int taskId, int userId) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
						
	} 
    
    //  案件詳細画面のタスク項目を削除するメソッド
    public ArrayList<TaskDTO> projectDetailDelete(int userId,int taskId) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
						
	}
    //   工数を登録するメソッド
    public int workRegist(int userId,int taskId) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

}