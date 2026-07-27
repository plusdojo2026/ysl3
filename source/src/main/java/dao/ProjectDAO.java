package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProjectDTO;

	public class ProjectDAO {
		Connection conn = null;
		public ProjectDAO(Connection conn) {
			this.conn = conn;
	}
		
		
	//projectSelectAllメソッド完成（仮）
		public ArrayList<ProjectDTO> projectSelectAll() {
			ArrayList<ProjectDTO> projectList = new ArrayList<>();
			String sql = "SELECT DISTINCT projects.* FROM projects INNER JOIN tasks ON projects.project_id = tasks.project_id WHERE tasks.user_id = ? ;";
			
			try{PreparedStatement ps =conn.prepareStatement(sql);
			
			// SELECT文を実行し、結果表を取得する
			ResultSet rs =ps.executeQuery();
			
			while (rs.next()) {
				ProjectDTO dto = new ProjectDTO();
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
		String sql = "SELECT * FROM projects where 1 = 1 ";

			
		if (projectStatus != 0 ) {
			sql += " AND project_Status =  ?";
		}
		if (projectPriority != 0 ) {
			sql +=  " AND project_priority =  ?";
		} 
		
		if (projectName != null && ! projectName.isEmpty()) {
			sql += " AND project_name Like ?";
		}
		
		sql +="ORDER BY project_id";
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		int index = 1;
		
		
		if(projectStatus !=0) {
			pStmt.setInt(index++,projectStatus);
		}
		
		if(projectPriority != 0) {
			pStmt.setInt(index++, projectPriority);
		}
		
		if(projectName != null && !projectName.isEmpty());
		{
			pStmt.setString(index++, "%" + projectName + "%");
		}
		ResultSet rs = pStmt.executeQuery();
		
		while (rs.next()) {
			ProjectDTO dto = new ProjectDTO();
			
			dto.setId(rs.getInt("project_id"));
			dto.setStatus(rs.getInt("project_status"));
			dto.setPriority(rs.getInt("project_priority"));
			dto.setName(rs.getString("project_name"));
			
			projectList.add(dto);
		}
		
		rs.close();
		pStmt.close();
			
		return projectList;
	}
	
	//projectRegistメソッド （完成）
	public int projectRegist(String projectCode, String projectName ,String customer , int pmId , int projectStatus, int projectPriority, String projectStartDate , String projectEndDate , String projectExplain)
			throws SQLException{
		// SELECT文を準備する
		String sql = "insert into projects(project_code,project_name,customer,pm_id,project_status,project_priority,project_dtart_date,project_end_date,project_explain)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
		// まとめる
		int ans = 0 ;
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,  projectCode);
		pStmt.setString(2, projectName);
		pStmt.setString(3, customer);
		pStmt.setInt(4, pmId);
		pStmt.setInt(5, projectStatus);
		pStmt.setInt(6,  projectPriority);
		pStmt.setString(7, projectStartDate);
		pStmt.setString(8, projectEndDate);
		pStmt.setString(9, projectExplain);
		
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
			ps.setString(9, projectExplain);
			
			ans=ps.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();		
			}
		return ans;
	}
	//projectUpdateメソッド（完了）
	public int projectUpdate(String projectCode , String projectName ,String customer, int pmId , int projectStatus , int projectPriority , String projectStartDate , String projectEndDate , String projectExplain)throws SQLException {
		// SELECT文を準備する
		String sql = "update projects SET project_code=?,project_name=?,customer=?,pm_Id=?,project_status=?,project_priority=?,project_startDate=?,project_end_date=?,project_explain=?  where project_id=?_";
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
		pStmt.setString(9, projectExplain);
		
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
			ps.setString(9, projectExplain);
			
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
			
			dto.setName(rs.getString("project_name"));
			
			dto.setStatus(rs.getInt("project_status"));

		    dto.setPriority(rs.getInt("project_priority"));	
		}
		rs.close();
		pStmt.close();
		
	return dto;
	}
}
