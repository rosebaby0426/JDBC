package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "david";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT DEPTNO,DNAME,LOC FROM DEPARTMENT";

	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;// 游標物件

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// Driver名稱
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			stat = con.createStatement();
			res = stat.executeQuery(SQL);

			while (res.next()) {
				String DEPTNO = res.getString("DEPTNO");
				String DNAME = res.getString("DNAME");
				String LOC = res.getString("LOC");
				System.out.println("DEPTNO =" + DEPTNO + " " + "DNAME =" + DNAME + " " + "LOC =" + LOC);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
