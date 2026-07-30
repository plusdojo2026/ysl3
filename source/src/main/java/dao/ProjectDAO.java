package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProjectDTO;

	public class ProjectDAO {
		Connection conn = null;
		public ProjectDAO(Connection conn) {
			this.conn = conn;
	}
		
		
	//projectSelectAllメソッド完成（仮）
		public ArrayList<ProjectDTO> projectSelectAll() {
			ArrayList<ProjectDTO> projectList = new ArrayList<>();
			String sql = "SELECT DISTINCT projects.* ,users.user_name, SUM(works.work) AS total_work FROM projects LEFT JOIN tasks ON projects.project_id = tasks.project_id LEFT JOIN works  ON tasks.task_id = works.task_id  LEFT JOIN users ON projects.pm_id = users.user_id GROUP BY projects.project_id ";
			
			try{PreparedStatement ps =conn.prepareStatement(sql);
			
			// SELECT文を実行し、結果表を取得する
			ResultSet rs =ps.executeQuery();
			
			while (rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setId(rs.getInt("project_id"));
				dto.setCode(rs.getString("project_code"));
				dto.setName(rs.getString("project_name"));
				dto.setCustomer(rs.getString("customer"));
				dto.setStatus(rs.getInt("project_status"));
				dto.setPriority(rs.getInt("project_priority"));
				dto.setPmId(rs.getInt("pm_id"));
				dto.setStartDate(rs.getString("project_start_date"));
				dto.setEndDate(rs.getString("project_end_date"));
				dto.setExplainText(rs.getString("project_explanation"));
				dto.setLimitDate(rs.getString("project_limit"));
				dto.setEstimatedWork(rs.getFloat("project_estimated_works"));
				
				dto.setTotalWork(rs.getFloat("total_work"));
				dto.setPmName(rs.getString("user_name"));
				projectList.add(dto);
				}
				rs.close();
				ps.close();} catch (SQLException e)
				{e.printStackTrace();
				
				}return projectList;
				}

		//projectSearchメソッド（保留）
	public ArrayList<ProjectDTO> projectSearch(int projectStatus , int projectPriority , String projectName) throws SQLException {
		
		ArrayList<ProjectDTO> projectList  = new ArrayList<ProjectDTO>();
		String sql = "SELECT * FROM projects where project_name LIKE ? ";

			
		if (projectStatus != -1) {
			sql += " AND project_status =  ?";
		}
		if (projectPriority != -1 ) {
			sql +=  " AND project_priority =  ?";
		} 
	
		
		sql +=" ORDER BY project_id";
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		

		if (projectName != null && ! projectName.isEmpty()) {
			pStmt.setString(1, "%" + projectName + "%");
			} else {
			pStmt.setString(1, "%");
			}
		
		int index = 2;
		
		
		if(projectStatus !=-1) {
			pStmt.setInt(index++,projectStatus);
		}
	
		
		if(projectPriority != -1) {
			pStmt.setInt(index++, projectPriority);
		}
		
		
		ResultSet rs = pStmt.executeQuery();
		
		while (rs.next()) {
			ProjectDTO dto = new ProjectDTO();
			
			dto.setId(rs.getInt("project_id"));//ID
			 dto.setCode(rs.getString("project_code"));//CODE
			dto.setStatus(rs.getInt("project_status"));//ステータス
			dto.setPriority(rs.getInt("project_priority"));//優先度
			dto.setName(rs.getString("project_name"));//プロジェクト名
			dto.setCustomer(rs.getString("customer"));//顧客名
			dto.setPmId(rs.getInt("pm_id"));//PMID
			dto.setStartDate(rs.getString("project_start_date"));
			dto.setEndDate(rs.getString("project_end_date"));
            dto.setLimitDate(rs.getString("project_limit"));
            dto.setExplainText(rs.getString("project_explanation"));
            dto.setEstimatedWork(rs.getFloat("project_estimated_works"));
			
			
			projectList.add(dto);
		}
		
		rs.close();
		pStmt.close();
			
		return projectList;
	}
	
	//projectRegistメソッド （完成）
	public int projectRegist(String projectCode, String projectName ,String customer , int pmId , int projectStatus, int projectPriority, String projectStartDate , String projectEndDate , float projectEstimatedWorks,String projectExplain,String projectLimit)
			throws SQLException{
		// SELECT文を準備する
		String sql = "insert into projects(project_code,project_name,customer,pm_id,project_status,project_priority,project_start_date,project_end_date,project_estimated_works,project_explanation,project_limit)VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		// まとめる
		int ans = 0 ;
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,projectCode);
		pStmt.setString(2,projectName);
		pStmt.setString(3,customer);
		pStmt.setInt(4,pmId);
		pStmt.setInt(5,projectStatus);
		pStmt.setInt(6,  projectPriority);
		pStmt.setString(7, projectStartDate);
		pStmt.setString(8, projectEndDate);
		pStmt.setFloat(9, projectEstimatedWorks);
		pStmt.setString(10, projectExplain);
		pStmt.setString(11,projectLimit);
		
		
		try(
			PreparedStatement ps =conn.prepareStatement(sql);){
			ps.setString(1,projectCode);
			ps.setString(2,projectName);
			ps.setString(3,customer);
			ps.setInt(4,pmId);
			ps.setInt(5, projectStatus);
			ps.setInt(6,projectPriority );
			ps.setString(7,projectStartDate);
			ps.setString(8,projectEndDate);
			ps.setFloat(9,projectEstimatedWorks );
			ps.setString(10,projectExplain);
			ps.setString(11, projectLimit);
			
			
			ans=ps.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();		
			}
		return ans;
	}
	//projectUpdateメソッド（完了）
	public int projectUpdate(String projectCode , String projectName ,String customer, int pmId , int projectStatus , int projectPriority , String projectStartDate , String projectEndDate ,Float projectEstimatedWorks , String projectExplain,String projectLimit, int projectId )throws SQLException {
		// SELECT文を準備する
		String sql = "update projects SET project_code=?,project_name=?,customer=?,pm_Id=?,project_status=?,project_priority=?,project_start_date=?,project_end_date=?, project_estimated_works=? ,project_explanation=? ,project_limit=?  where project_id=?";
		int ans = 0 ;
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, projectCode);
		pStmt.setString(2, projectName);
		pStmt.setString(3, customer);
		pStmt.setInt(4, pmId);
		pStmt.setInt(5, projectStatus);
		pStmt.setInt(6,  projectPriority);
		pStmt.setString(7, projectStartDate);
		pStmt.setString(8, projectEndDate);
		pStmt.setFloat(9, projectEstimatedWorks);
		pStmt.setString(10, projectExplain);
		pStmt.setString(11,projectLimit);
		pStmt.setInt(12,  projectId);
		
		try(
				PreparedStatement ps =conn.prepareStatement(sql);){
			ps.setString(1,projectCode);
			ps.setString(2,projectName);
			ps.setString(3,customer);
			ps.setInt(4,pmId);
			ps.setInt(5, projectStatus);
			ps.setInt(6,projectPriority );
			ps.setString(7, projectStartDate);
			ps.setString(8, projectEndDate);
			ps.setFloat(9,projectEstimatedWorks );
			ps.setString(10, projectExplain);
			ps.setString(11, projectLimit);
			ps.setInt(12, projectId);
			
			ans=ps.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();		
			}
		return ans;	
		
	
	}
	//projectStatusChangeメソッド
	
	public int projectStatusChange(int projectId,int projectStatus) throws SQLException {
		
		// SELECT文を準備する（仮）
		String sql = "update projects set project_status=? where project_id=?";
		int ans = 0;
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, projectStatus);
		
		try(PreparedStatement ps =conn.prepareStatement(sql);){
			ps.setInt(1, projectStatus);
			
			ans=ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}
	//projectdetailメソッド（完成）
	// SELECT文を準備する
	public ProjectDTO projectDetail(int projecrId)throws SQLException  {
		
		//ProjectDTO Project = new ProjectDTO();
		ProjectDTO dto = null;
		
		String sql =" SELECT * FROM projects where project_id=?";
		
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
				
		// まとめる
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1,projecrId);
		
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = ps.executeQuery();
		
		//移し替え
		if(rs.next()) {
			 dto = new ProjectDTO();
			dto.setCode(rs.getString("project_id"));
			dto.setName(rs.getString("project_name"));
			dto.setCustomer(rs.getString("customer"));
			dto.setStatus(rs.getInt("project_status"));
			dto.setPriority(rs.getInt("project_priority"));
			dto.setPmId(rs.getInt("pm_id"));
			dto.setStartDate(rs.getString("project_start_date"));
			dto.setEndDate(rs.getString("project_end_date"));
			dto.setExplainText(rs.getString("project_explanation"));
			dto.setLimitDate(rs.getString("project_limit"));
			dto.setEstimatedWork(rs.getFloat("project_estimated_works"));
			dto.setPmName(rs.getString("pm_id"));
			//dto.setTotalWork(rs.getFloat("total_work"));
		}
			rs.close();
			ps.close();
			
		return dto;
	}
	//案件情報を取得する
	public ProjectDTO projectToEdit(int projectId)throws SQLException{
		
		//案件情報を格納する箱
		ProjectDTO dto = null;
		
		//SQL
		String sql = "SELECT*FROM projects WHERE project_id = ?";
		
		//実行準備
		PreparedStatement pStmt = conn.prepareStatement(sql);
		//実行
		pStmt.setInt(1,projectId);
		ResultSet rs = pStmt.executeQuery();
		
		//結果をDTOに格納
		if(rs.next()) {
			dto = new ProjectDTO();
			
			dto.setId(rs.getInt("project_id"));
			
			dto.setCode(rs.getString("project_code"));
			
			dto.setName(rs.getString("project_name"));
			
			dto.setStatus(rs.getInt("project_status"));

		    dto.setPriority(rs.getInt("project_priority"));	
		    
		    dto.setCustomer(rs.getString("customer"));
		    
		    dto.setPmId(rs.getInt("pm_id"));
		    
		    dto.setStartDate(rs.getString("project_start_date"));
		    
		    dto.setEndDate(rs.getString("project_end_date"));
		    
		    dto.setExplainText(rs.getString("project_explanation"));
		    
		    dto.setLimitDate(rs.getString("project_limit"));
		    
		    dto.setEstimatedWork(rs.getFloat("project_estimated_works"));
		    

		}
		rs.close();
		pStmt.close();
		
	return dto;
	}
	
	//サマリーカード用
	//総工数メソッド
	// 総工数メソッド（【修正】引数 String targetMonth を追加）
	public float getTotalWork(String targetMonth)	{
		float TotalWork = 0;
		
		String sql =
				"SELECT SUM(work) AS total_work FROM works "
				+ "WHERE DATE_FORMAT(work_date,'%Y-%m')=?";
		
		if (this.conn == null) {
			System.out.println("データベース接続(conn)がnullです。");
			return 0;
		}
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) 
		{
			// 【修正】? に引数の値をセットする
			ps.setString(1, targetMonth);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					// 【修正】取得名を小文字の total_work に変更
					TotalWork = rs.getFloat("total_work");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return TotalWork;
	}

	
	//案件別実績
	//
	public List<ProjectDTO> getProjectSummary(String month) {
		
		List<ProjectDTO> list = new ArrayList<>();
		
		String sql =
		          "SELECT "
		        + " p.project_name, "
		        + " p.project_estimated_works, "
		        + " COALESCE(SUM(w.work),0) AS totalWork, "
		        + " COALESCE(SUM(t.task_estimated_works),0) AS plannedWork, "
		        + " COALESCE(AVG(t.progress),0) AS progressRate "
		        + "FROM projects p "
		        + "LEFT JOIN tasks t "
		        + "ON p.project_id = t.project_id "
		        + "LEFT JOIN works w "
		        + "ON t.task_id = w.task_id "
		        + "AND DATE_FORMAT(w.work_date,'%Y-%m') = ? "
		        + "GROUP BY "
		        + "p.project_id, "
		        + "p.project_name, "
		        + "p.project_estimated_works "
		        + "ORDER BY p.project_code";
		
		try (PreparedStatement ps = conn.prepareStatement(sql);)
			{
			
			// 対象年月を設定
	        ps.setString(1, month);
	        
	        //SQL文を実行
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

	            ProjectDTO dto = new ProjectDTO();

	            // 案件名
	            dto.setName(rs.getString("project_name"));

	            // 実績工数
	            dto.setTotalWork(rs.getFloat("totalWork"));

	            // 予定工数
	            dto.setPlannedWork(rs.getFloat("plannedWork"));

	            // 残工数（見積工数－実績工数)
	            float estimatedWork = rs.getFloat("project_estimated_works");
	            dto.setRemainWork(estimatedWork - dto.getTotalWork());

	            // 進捗率
	            // 工数ベースの進捗率
	            dto.setProgressRate(rs.getFloat("progressRate"));

	            list.add(dto);
	        }

	    } catch (SQLException e) {
	    	
	    	// SQL実行時のエラー表示
	        e.printStackTrace();
	    }
			//リストを返す	
	    	return list;
		}

	
	//	ユーザー別実績
	public List<ProjectDTO> getUserSummary(String month) {
		
		List<ProjectDTO> list = new ArrayList<>();
		
		String sql=
				 "SELECT "
				+ " u.user_name, "
				+ " COALESCE(t.plannedWork,0) AS plannedWork, "
			    + " COALESCE(w.totalWork,0) AS totalWork, "
				+ " COALESCE(t.progressRate,0) AS progressRate "
				+ " FROM users u "
				+ " LEFT JOIN ( "
			    + " SELECT "
				+ " user_id, "
			    + " SUM(task_estimated_works) AS plannedWork, "
				+ " AVG(progress) AS progressRate "
			    + " FROM tasks "
				+ " GROUP BY user_id "
			    + ") t "
				+ " ON u.user_id = t.user_id "
				+ " LEFT JOIN ( "
				+ " SELECT "
			    + " user_id, "
			    + " SUM(work) AS totalWork "
			    + " FROM works "
			    + " WHERE DATE_FORMAT(work_date,'%Y-%m') = ? "
			    + " GROUP BY user_id "
			    + ") w "
			    + "ON u.user_id = w.user_id "
			    + "WHERE u.role = 0 "
			    + "ORDER BY u.user_id";
		
		try (PreparedStatement ps = conn.prepareStatement(sql);)
		{
			// 対象年月を設定
	        ps.setString(1, month);
	        
	        //SQL文を実行
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

	            ProjectDTO dto = new ProjectDTO();

	            // ユーザー名
	            dto.setUserName(rs.getString("user_name"));

	            // 実績工数
	            dto.setTotalWork(rs.getFloat("totalWork"));

	            // 予定工数
	            dto.setPlannedWork(rs.getFloat("plannedWork"));

	            // 残工数
	            dto.setRemainWork(
	                dto.getPlannedWork() - dto.getTotalWork()
	            );

	            // 進捗率
	            dto.setProgressRate(rs.getFloat("progressRate"));

	            list.add(dto);
	        }

	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}

	    	return list;
		}
			
		}
	
	

