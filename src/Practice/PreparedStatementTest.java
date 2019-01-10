package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementTest {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "david";
	private static final String PASSWORD = "123456";
	private static final String SQL = "UPDATE DEPARTMENT SET DNAME=?,LOC=? WHERE DEPTNO=?";

	public static void main(String[] args) {
		// 因為資源要確定能正確關閉(在try裡無法正確關掉資源)，所以資源物件設定成實體變數
		Connection con = null;
		PreparedStatement pre = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pre = con.prepareStatement(SQL);

			pre.setString(1, "神經部");
			pre.setString(2, "在天空");
			pre.setInt(3, 80);
			pre.execute();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pre != null) {
				try {
					pre.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
