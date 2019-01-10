package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdatePractice {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "david";
	private static final String PASSWORD = "123456";
	private static final String SQL1 = "INSERT INTO DEPARTMENT(DEPTNO,DNAME,LOC) VALUES(?,?,?)";
	private static final String SQL2 = "UPDATE DEPARTMENT SET DNAME=?,LOC=? WHERE DEPTNO=?";

	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL1);
			pstmt.setInt(1, 80);
			pstmt.setString(2, "飛航部");
			pstmt.setString(3, "松山機場");
			pstmt.addBatch();
			System.out.println("check1");

			pstmt.setInt(1, 90);
			pstmt.setString(2, "跳機部");
			pstmt.setString(3, "機場內");
			pstmt.addBatch();
			System.out.println("check2");
			pstmt.executeBatch();

			pstmt2 = con.prepareStatement(SQL2);
			pstmt2.setString(1, "品管部");
			pstmt2.setString(2, "雲林");
			pstmt2.setInt(3, 70);
			pstmt2.addBatch();
			System.out.println("check3");
			pstmt2.executeBatch();
			System.out.println("check4");

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

}
