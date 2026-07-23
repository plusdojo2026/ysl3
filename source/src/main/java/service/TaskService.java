package service;

import java.sql.Connection;
import java.sql.SQLException;
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
		public ArrayList<TaskDTO> taskSelectAll(int task_id,int user_id) throws SQLException {
			ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
			
			// DB接続
			access();
			
			try {
				// DAOを実体化
				TaskDAO dao = new TaskDAO(conn);
				
				// タスク一覧取得処理を実施。DAOのメソッドを実行
				taskList = dao. taskSelectAll(task_id,user_id);
				
			} finally {
				
				// DB接続解除
				close();
			}
			
			// 戻り値
			return taskList;
		}
		
		
		
		
		// タスク検索をするメソッド
				public ArrayList<TaskDTO> taskSearch(int task_id,int project_id, int task_status,int user_id,String task_name) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク検索を実施。DAOのメソッドを実行
						taskList = dao. taskSearch(task_id,project_id,task_status, task_name, user_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}
			
				
				
	  // タスク登録メソッド
		public int taskRegist(int task_id,int user_id,int project_id,String task_name, int task_status, int task_priority,
				int progress,float task_estimated_works, String task_start_date, String task_limit,String task_explanation) {
			int ans = 0;
					
			        // DB接続
					access();
					
					try {
						
						// DAOを実体化
							TaskDAO dao = new TaskDAO(conn);
						
						// ユーザー登録処理を実施。DAOのメソッドを実行
							ans = dao.taskRegist(task_id,user_id,project_id, task_name, task_status, task_priority,
									progress,task_estimated_works, task_start_date, task_limit,task_explanation);
						} finally {
						
						// DB接続解除
							close();
						}
					
					
					// 戻り値
					return ans;
				}		

		
		
	 // タスク編集メソッド
		public int taskUpdate(int task_id,int user_id,int project_id,String task_name, int task_status, int task_priority,
				int progress,float task_estimated_works, String task_start_date, String task_limit,String task_explanation) {
				int ans = 0;
						
				        // DB接続
						access();
						
						try {
							
							// DAOを実体化
								TaskDAO dao = new TaskDAO(conn);
							
							// ユーザー登録処理を実施。DAOのメソッドを実行
								ans = dao.taskUpdate(task_id,user_id,project_id, task_name, task_status, task_priority,
										progress, task_estimated_works,  task_start_date, task_limit, task_explanation);
							} finally {
							
							// DB接続解除
								close();
							}
						
						
						// 戻り値
						return ans;
					}		
			
		
		
	 // ユーザー削除メソッド
			public int taskDelete(int user_id,int task_id) {
					int ans = 0;
							
					    // DB接続
						access();
							
						try {
								
							// DAOを実体化
								TaskDAO dao = new TaskDAO(conn);
								
							// ユーザー登録処理を実施。DAOのメソッドを実行
								ans = dao.taskDelete(user_id,task_id);
							} finally {
								
							// DB接続解除
								close();
							}
							
							
							// 戻り値
							return ans;
						}	
			
			
			
			
	// タスクステータス変更メソッド
			public int taskStatusChange(int user_id,int task_id) {
					int ans = 0;
										
							// DB接続
							access();
										
								try {
											
								// DAOを実体化
									TaskDAO dao = new TaskDAO(conn);
											
									// ユーザー登録処理を実施。DAOのメソッドを実行
										ans = dao.taskStatusChange(user_id, task_id);
									} finally {
											
							// DB接続解除
								close();
						}
										
										
								// 戻り値
								return ans;
							}	
			  
			
			
			  
	 //タスク詳細を表示するメソッド
			public TaskDTO taskDetail(int user_id,int task_id) {
					TaskDTO dto = null;

					// DB接続
					access();

					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(this.conn);
						
						// ログイン処理を実施。DAOのメソッドを実行
						dto = dao.taskDetail(user_id,task_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return dto;
				}
			  
			  
			
			
		//  ホーム画面のタスク一覧を表示するメソッド
			  public ArrayList<TaskDTO> homeTaskList(int task_id,int user_id) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク一覧取得処理を実施。DAOのメソッドを実行
						taskList = dao.homeTaskList(task_id,user_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}	  
			  
		
			  
	//  案件一覧画面のタスク項目を表示するメソッド
			  public ArrayList<TaskDTO> projectList(int task_id,int user_id) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク一覧取得処理を実施。DAOのメソッドを実行
						taskList = dao.projectList(task_id,user_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}	  
			    
			  
			  
	//  案件詳細画面のタスク項目を削除するメソッド
			  public ArrayList<TaskDTO> projectDetailDelete(int user_id,int task_id) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク一覧取得処理を実施。DAOのメソッドを実行
						taskList = dao.projectDetailDelete(user_id,task_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}
			  
			  
			  
	//   工数を登録するメソッド
			    public int workRegist(int user_id,int task_id) {
					int ans = 0;
					//	処理はのちに記述。今は返すだけ
					return ans;
				}
}
