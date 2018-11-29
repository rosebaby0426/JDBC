package TabelClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "David";
	private static final String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO DEPARTMENT (DEPTNO , DNAME , LOC) VALUES (?,?,?)";
	private static final String FIND_BY_PK = "SELECT * FROM DEPARTMENT WHERE DEPTNO = ?";
	private static final String UPDATE = "UPDATE DEPARTMENT SET DNAME = ? , LOC = ? WHERE DEPTNO = ?";
	private static final String GET_ALL = "SELECT * FROM DEPARTMENT";

	static {
		try {// 載入驅動
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Department department) {
		Connection con = null;// 設定連線
		PreparedStatement pstmt = null;// 設定預存SQL語法

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, department.getDeptno());// 設定部門物件的裡面欄位資料
			pstmt.setString(2, department.getDname());// 按照欄位索引值一個個找到對應
			pstmt.setString(3, department.getLoc());

			pstmt.executeUpdate();// 欄位資料設定完成後，更新資料

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();// 當資源使用完畢後關閉釋放資源，越晚建立的資源越早關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();// 當連線資源使用完畢後關閉釋放資源，最後關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(Department department) {
		Connection con = null;// 設定連線
		PreparedStatement pstmt = null;// 設定預存SQL語法
//		Department dept = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

//			dept = new Department();
			pstmt.setInt(3, department.getDeptno());
			pstmt.setString(1, department.getDname());// 按照欄位索引值一個個找到對應
			pstmt.setString(2, department.getLoc());

			pstmt.executeUpdate();// 欄位資料設定完成後，更新資料

		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();// 當資源使用完畢後關閉釋放資源，越晚建立的資源越早關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();// 當連線資源使用完畢後關閉釋放資源，最後關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(int deptno) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findByPK(int deptno) {
		Connection con = null;// 設定連線
		PreparedStatement pstmt = null;// 設定預存SQL語法
		ResultSet rs = null;//
		Department dept = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, deptno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dept = new Department();
				dept.setDeptno(deptno);
				dept.setDname(rs.getString("DNAME"));
				dept.setLoc(rs.getString("LOC"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();// 當資源使用完畢後關閉釋放資源，越晚建立的資源越早關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();// 當資源使用完畢後關閉釋放資源，越晚建立的資源越早關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();// 當連線資源使用完畢後關閉釋放資源，最後關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return dept;
	}

	@Override
	public List<Department> getAll() {
		List<Department> deptList = new ArrayList<>();
		Connection con = null;// 設定連線
		PreparedStatement pstmt = null;// 設定預存SQL語法
		ResultSet rs = null;//
		Department dept = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dept = new Department();
				dept.setDeptno(rs.getInt("DEPTNO"));
				dept.setDname(rs.getString("DNAME"));
				dept.setLoc(rs.getString("LOC"));
				deptList.add(dept);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();// 當資源使用完畢後關閉釋放資源，越晚建立的資源越早關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();// 當資源使用完畢後關閉釋放資源，越晚建立的資源越早關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();// 當連線資源使用完畢後關閉釋放資源，最後關閉
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return deptList;
	}

}
