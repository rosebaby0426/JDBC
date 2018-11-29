package JDBC_FIRST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hello_JDBC {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "David";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO DEPARTMENT (DEPTNO , DNAME , LOC) VALUES (50,'總務部','桃園中壢')";
	private static final String QUERY_STMT = "SELECT * FROM DEPARTMENT";

	public static void main(String[] args) {
		Connection con = null;// (需給初始值)建立連線物件
		Statement stmt = null;// 建立送出SQL指令物件
		ResultSet rs = null;// 建立游標物件

		try {
			// 第一步驟：載入驅動
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("載入成功");

			// 第二步驟：取得連線
			con = DriverManager.getConnection(URL, USER, PASSWORD);// DriverManager類別，官方定義好的
			System.out.println("取得連線");

			// 第三步驟：送出SQL指令
//			stmt = con.createStatement();
//			int count = stmt.executeUpdate(INSERT_STMT);
//			System.out.println("新增成功" + count + "筆資料");

			stmt = con.createStatement();
			rs = stmt.executeQuery(QUERY_STMT);
			while (rs.next()) {
				int deptno = rs.getInt("DEPTNO");
				String dname = rs.getString("DNAME");
				String loc = rs.getString("LOC");

				System.out.println("DEPNO = " + deptno);
				System.out.println("DNAME = " + dname);
				System.out.println("LOC = " + loc);
				System.out.println("======================");
			}

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
					System.out.println("關閉連線");// 非意外斷線
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
					System.out.println("關閉連線");// 非意外斷線
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
					System.out.println("關閉連線");// 非意外斷線
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

}
