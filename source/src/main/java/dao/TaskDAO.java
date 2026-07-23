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
	public ArrayList<TaskDTO> taskSelectAll(int user_id,int task_id) throws SQLException{
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	(処理)s
		// SELECT文を準備する
				String sql ="SELECT * FROM task WHERE user_id = ?";
				//デバッグ（SQL文の確認用）
				System.out.println(sql);
				
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, user_id);
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while(rs.next()) {
		TaskDTO dto = new TaskDTO(user_id, task_id);			
		dto.setUserId(rs.getInt("user_id"));
		dto.setTaskId(rs.getInt("task_id"));
		taskList.add(dto);
				}
		
		return taskList;
		
	}
	
	//	タスクを検索するメソッド
	public ArrayList<TaskDTO> taskSearch(int project_id, int task_status,int user_id,String task_name, int task_id) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
	}
	
	//	タスク登録メソッド
	public int taskRegist( int task_id,int user_id,int project_id,String task_name, int task_status, int task_priority,
			int progress,float task_estimated_works, String task_start_date, String task_limit,String task_explanation) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

	//	タスク編集メソッド
	public int taskUpdate( int task_id, int user_id,int project_id,String task_name, int task_status, int task_priority,
			int progress,float task_estimated_works, String task_start_date, String task_limit,String task_explanation) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
			
    //	タスク削除メソッド
	public int taskDelete(int user_id,int task_id) {
	     int ans = 0;
		//	処理はのちに記述。今は返すだけ
		 return ans;
     }

	 //	タスクステータス更新メソッド
	public int taskStatusChange(int user_id,int task_id){
	     int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
			
     //	タスク詳細を表示するメソッド
	public TaskDTO taskDetail(int user_id,int task_id) {
		TaskDTO  dto = null;
		//	処理はのちに記述。今は返すだけ
		return dto;
	}
	
	
     //  ホーム画面のタスク一覧を表示するメソッド
	public ArrayList<TaskDTO> homeTaskList( int task_id,int user_id) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
			
	}
				
	 //  案件一覧画面のタスク項目を表示するメソッド
    public ArrayList<TaskDTO> projectList( int task_id, int user_id) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
						
	} 
    
    //  案件詳細画面のタスク項目を削除するメソッド
    public ArrayList<TaskDTO> projectDetailDelete(int user_id,int task_id) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
						
	}
    //   工数を登録するメソッド
    public int workRegist(int user_id,int task_id) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

}