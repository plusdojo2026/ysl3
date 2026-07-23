package dao;
import java.sql.Connection;
import java.util.ArrayList;

import model.TaskDTO;

public class TaskDAO {
	Connection conn = null;
	
	//	コンストラクタ
	public TaskDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	//	タスク一覧を表示するメソッド
	public ArrayList<TaskDTO> taskSelectAll(int user_idx,int task_id) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
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