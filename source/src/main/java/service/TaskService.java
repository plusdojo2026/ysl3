package service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.xml.crypto.Data;

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
		public ArrayList<TaskDTO> taskSelectAll(int user_id) {
			ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
			
			// DB接続
			access();
			
			try {
				// DAOを実体化
				TaskDAO dao = new TaskDAO(conn);
				
				// タスク一覧取得処理を実施。DAOのメソッドを実行
				taskList = dao. taskSelectAll(user_id);
				
			} finally {
				
				// DB接続解除
				close();
			}
			
			// 戻り値
			return taskList;
		}
		
		
		// タスク検索をするメソッド
				public ArrayList<TaskDTO> taskSearch(int project_id, int task_status,int user_id,String task_name) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク検索を実施。DAOのメソッドを実行
						taskList = dao. taskSearch(project_id,task_status, user_id, task_name);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}
				
	  // ユーザー登録メソッド
		public int taskRegist(int project_id,String task_name, int task_status, int task_priority,
				int progress,float task_estimated_works, Data task_start_date, Data task_limit,String task_explanation) {
			int ans = 0;
					
			        // DB接続
					access();
					
					try {
						
						// DAOを実体化
							TaskDAO dao = new TaskDAO(conn);
						
						// ユーザー登録処理を実施。DAOのメソッドを実行
							ans = dao.taskRegist(project_id, task_name, task_status, task_priority,
									progress,task_estimated_works, task_start_date, task_limit,task_explanation);
						} finally {
						
						// DB接続解除
							close();
						}
					
					
					// 戻り値
					return ans;
				}		

	 // ユーザー編集メソッド
		public int taskUpdate(int project_id,String task_name, int task_status, int task_priority,
				int progress,float task_estimated_works, Data task_start_date, Data task_limit,String task_explanation) {
				int ans = 0;
						
				        // DB接続
						access();
						
						try {
							
							// DAOを実体化
								TaskDAO dao = new TaskDAO(conn);
							
							// ユーザー登録処理を実施。DAOのメソッドを実行
								ans = dao.taskUpdate(project_id, task_name, task_status, task_priority,
										progress, task_estimated_works,  task_start_date, task_limit, task_explanation);
							} finally {
							
							// DB接続解除
								close();
							}
						
						
						// 戻り値
						return ans;
					}		
			
	 // ユーザー削除メソッド
			public int taskDelete(int task_id) {
					int ans = 0;
							
					    // DB接続
						access();
							
						try {
								
							// DAOを実体化
								TaskDAO dao = new TaskDAO(conn);
								
							// ユーザー登録処理を実施。DAOのメソッドを実行
								ans = dao.taskDelete(task_id);
							} finally {
								
							// DB接続解除
								close();
							}
							
							
							// 戻り値
							return ans;
						}	
			
			
	// タスクステータス変更メソッド
			public int taskStatusChange(int task_id) {
					int ans = 0;
										
							// DB接続
							access();
										
								try {
											
								// DAOを実体化
									TaskDAO dao = new TaskDAO(conn);
											
									// ユーザー登録処理を実施。DAOのメソッドを実行
										ans = dao.taskStatusChange(task_id);
									} finally {
											
							// DB接続解除
								close();
						}
										
										
								// 戻り値
								return ans;
							}	
			  
			  
	 //タスク詳細を表示するメソッド
			public TaskDTO taskDetail(int task_id) {
					TaskDTO dto = null;

					// DB接続
					access();

					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(this.conn);
						
						// ログイン処理を実施。DAOのメソッドを実行
						dto = dao.taskDetail(task_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return dto;
				}
			  
			  
		//  ホーム画面のタスク一覧を表示するメソッド
			  public ArrayList<TaskDTO> homeTaskList(int user_id) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク一覧取得処理を実施。DAOのメソッドを実行
						taskList = dao.homeTaskList(user_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}	  
			  
			  
	//  案件一覧画面のタスク項目を表示するメソッド
			  public ArrayList<TaskDTO> ProjectList(int user_id) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク一覧取得処理を実施。DAOのメソッドを実行
						taskList = dao.ProjectList(user_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}	  
			    
			  
	//  案件詳細画面のタスク項目を削除するメソッド
			  public ArrayList<TaskDTO> projectDetail(int task_id) {
					ArrayList<TaskDTO> taskList = new ArrayList<TaskDTO>();
					
					// DB接続
					access();
					
					try {
						// DAOを実体化
						TaskDAO dao = new TaskDAO(conn);
						
						// タスク一覧取得処理を実施。DAOのメソッドを実行
						taskList = dao.projectDetail(task_id);
						
					} finally {
						
						// DB接続解除
						close();
					}
					
					// 戻り値
					return taskList;
				}	  
	//   工数を登録するメソッド
			    public int workRegist(int task_id) {
					int ans = 0;
					//	処理はのちに記述。今は返すだけ
					return ans;
				}
}
