package Package;

import java.sql.*;

public class DatabasePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Problem in loading the driver");
			ex.printStackTrace();
		}
		
		try {
			String dbName = "Employee.accdb";
			String dbURL = "jdbc:ucanaccess://"+dbName;
			conn = DriverManager.getConnection(dbURL); //establish connection
			stat = conn.createStatement();
			String query;
			//String query = "INSERT INTO EMP (EName,Salary) VALUES('Alvin',9500)";
			
			//stat.executeUpdate(query);
			
			query = "UPDATE EMP SET SALARY = 120000 WHERE Ename = 'Lysa'";
			stat.executeUpdate(query);
			
			query = "DELETE FROM EMP WHERE EName = 'John'";
			stat.executeUpdate(query);
			
			rs = stat.executeQuery("SELECT * FROM EMP");
			
			int id;
			String name;
			double salary;
			
			while(rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				salary = rs.getDouble(3);
				System.out.println("ID: "+id+ " Name: "+name+" Salary: "+salary);
			}
		} catch(SQLException ex) {
			System.out.println("problem with database");
		} finally {
			try {
				if(conn!=null) {
					rs.close();
					stat.close();
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

}
