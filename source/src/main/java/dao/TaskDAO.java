package dao;
import java.security.Timestamp;
import java.sql.Connection;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import model.TaskDTO;

public class TaskDAO {
	Connection conn = null;
	
	//	コンストラクタ
	public TaskDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	//	タスク一覧を表示するメソッド
	public ArrayList<TaskDTO> taskSelectAll(
			int project_id, int task_id, String task_name, int task_status, int task_priority, String task_limit, 
			float task_estimated_works, int intprogress, int user_id, Timestamp created_at, Timestamp update_at) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
		
	}
	
	//	タスクを検索するメソッド
	public ArrayList<TaskDTO> taskSearch(int project_id, int task_status,int user_id,String task_name) {
		ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
	}
	
	//	タスク登録メソッド
	public int taskRegist(int project_id,String task_name, int task_status, int task_priority,
			int progress,float task_estimated_works, Data task_start_date, Data task_limit,String task_explanation) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}

	//	タスク編集メソッド
	public int taskEdit(int project_id,String task_name, int task_status, int task_priority,
			int progress,float task_estimated_works, Data task_start_date, Data task_limit,String task_explanation) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
			
    //	タスク削除メソッド
	public int taskDelete(int task_id) {
	     int ans = 0;
		//	処理はのちに記述。今は返すだけ
		 return ans;
     }

	 //	タスクステータス更新メソッド
	public int taskStatusUpdate(int task_id, int task_status, int progress){
	     int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
			
     //	タスク詳細を表示するメソッド
	public ArrayList<TaskDTO> taskDetail(
		int project_id, int task_id, String task_name, int task_status, 	
		int task_priority, String task_start_date,String task_limit, 
		float task_estimated_works, int progress, int user_id) {
			ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
				
	}
			
     //  ホーム画面のタスク一覧を表示するメソッド
	public ArrayList<TaskDTO> taskHome(String task_name, int user_id,String task_limit,
		int task_status, int progress) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
			
	}
				
	 //  案件一覧画面のタスク項目を表示するメソッド
    public ArrayList<TaskDTO> taskProjectList(int task_id, int task_status, int progress) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
						
	} 
    
    //  案件詳細画面のタスク項目を表示するメソッド
    public ArrayList<TaskDTO> taskProjectDetail(String task_name,int user_id,Data task_limit, 
    		int task_status, int progress) {
		   ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
		//	処理はのちに記述。今は返すだけ
		return taskList;
						
	}
    //   工数を登録するメソッド
    public int taskWorkRegist(int task_id, String task_name) {
		int ans = 0;
		//	処理はのちに記述。今は返すだけ
		return ans;
	}
	
}