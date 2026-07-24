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
		
		
	//projectSelectAllメソッド
		public ArrayList<ProjectDTO> projectSelectAll() {
			ArrayList<ProjectDTO> projectList = new ArrayList<>();
			String sql = "SELECT * FROM projects";
			
			try{PreparedStatement ps =conn.prepareStatement(sql);
			
			// SELECT文を実行し、結果表を取得する
			ResultSet rs =ps.executeQuery();
			
			while (rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setCode(rs.getString("projectId"));
				dto.setName(rs.getString("projectName"));
				dto.setCustomer(rs.getString("customer"));
				dto.setStatus(rs.getInt("projectStatus"));
				dto.setPriority(rs.getInt("projectPriority"));
				dto.setPmId(rs.getInt("pmId"));
				dto.setStartDate(rs.getString("projectStartDate"));
				dto.setEndDate(rs.getString("projectEndDate"));
				dto.setExplainText(rs.getString("explainText"));
				dto.setLimitDate(rs.getString("limitDate"));
				dto.setEstimatedWork(rs.getFloat("estimatedWork"));
				dto.setPmName(rs.getString("pmName"));
				dto.setTotalWork(rs.getFloat("totalWork"));
				
				projectList.add(dto);
				}rs.close();
				ps.close();} catch (SQLException e) {
				e.printStackTrace();
				
				}return projectList;
				}

		//projectSearchメソッド
	public ArrayList<ProjectDTO> projectSearch(int projectStatus , int projectPriority , String projectName) throws SQLException {
		
		ArrayList<ProjectDTO> projectList  = new ArrayList<ProjectDTO>();
		String sql = "SELECT * FROM projects where project_name LIKE ? AND project_priority LIKE ? , project_states order by project_id";
		
		pStmt = conn.prepareStatement(sql);
		
		if (projects.getProjectName() != null && !projects.getProjectName().isEmpty()) {
			pStmt.setString(1, "%" + projects.getName() + "%");
		} else {
			pStmt.setString(1, "%");
			
		if(projects.getProjectPriority() ! = null && ! projects.getProjectPriority().isEmpty()) {
			pStmt.setInt(2,"%" + projects.getPriority() + "%");
		}else {
			pStmt.setInt(2,"%");
			
		if(projects.getProjectStates() ! = null && ! projects.getProjectPriority().isEmpty()) {
			pStmt.setInt(3,"%"+ projects.getStates() + "%");
		}else {
			pStmt.setInt(3,"%");
		}
		
			
		rs = pStmt.executeQuery();
		
		while (rs.next())
			
		return projectList;
	}
	//projectRegistメソッド 完成
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
	//projectUpdateメソッド完了
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
		
		// SELECT文を準備する
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
	//projectdetailメソッド
	// SELECT文を準備する
	public ArrayList<ProjectDTO> projectdetail(int projecrId)throws SQLException  {
		
		ArrayList<ProjectDTO> ProjectList = new ArrayList<ProjectDTO>();
		
		String sql =" SELECT * FROM projects where project_id=?";
		
		//デバッグ（SQL文の確認用）
		System.out.println(sql);
				
		// まとめる
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		
		//移し替え
		while(rs.next()) {
			ProjectDTO dto = new ProjectDTO();
			dto.setCode(rs.getString("projectId"));
			dto.setName(rs.getString("projectName"));
			dto.setCustomer(rs.getString("customer"));
			dto.setStatus(rs.getInt("projectStatus"));
			dto.setPriority(rs.getInt("projectPriority"));
			dto.setPmId(rs.getInt("pmId"));
			dto.setStartDate(rs.getString("projectStartDate"));
			dto.setEndDate(rs.getString("projectEndDate"));
			dto.setExplainText(rs.getString("explainText"));
			dto.setLimitDate(rs.getString("limitDate"));
			dto.setEstimatedWork(rs.getFloat("estimatedWork"));
			dto.setPmName(rs.getString("pmName"));
			dto.setTotalWork(rs.getFloat("totalWork"));
			
			}
		return ProjectList;
	}
}
