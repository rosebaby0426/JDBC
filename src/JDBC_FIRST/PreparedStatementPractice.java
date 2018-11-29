package JDBC_FIRST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementPractice {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "David";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO DEPARTMENT (DEPTNO , DNAME , LOC) VALUES (?,?,?)";
	// 為了讓使用者方便新增資料，所以value的地方放入?當變數
	private static final String QUERY_STMT = "SELECT * FROM DEPARTMENT";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入編號");
		int deptno = sc.nextInt();
		System.out.println("請輸入名稱");
		String dname = sc.next();
		System.out.println("請輸入所在地");
		String loc = sc.next();

		sc.close();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 第一步驟：載入驅動
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("載入成功");

			// 第二步驟：取得連線
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("取得連線");

			// 第三步驟：送出SQL指令
			pstmt = con.prepareStatement(INSERT_STMT);
			// 為了避免被駭客入侵取得資料，事先取得資料庫連線
			pstmt.setInt(1, deptno);// 將所有的SQL指令事先設定好，可以防止有心人士使用SQL指令
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);

			pstmt.executeUpdate();// 因為參數的SQL指令在前面已經先設定好了，所以後面直接接執行程式即可

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

			if (pstmt != null) {
				try {
					pstmt.close();// 因為有連線，所以就要記得關閉連線
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
