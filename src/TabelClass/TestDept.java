package TabelClass;

import java.util.Scanner;

public class TestDept {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入編號：");
		int deptno = sc.nextInt();
		System.out.println("請輸入名稱：");
		String dname = sc.next();
		System.out.println("請輸入所在地：");
		String loc = sc.next();

//		sc.close();

//		Department dept = new Department(deptno, dname, loc);
//		DepartmentDAOImpl dao = new DepartmentDAOImpl();
//		dao.add(dept);
//		System.out.println("新增成功");

//		DepartmentDAOImpl dao = new DepartmentDAOImpl();
//		Department dept = dao.findByPK(deptno);
//		System.out.println("DEPTNO = " + dept.getDeptno());
//		System.out.println("DNAME = " + dept.getDname());
//		System.out.println("LOC = " + dept.getLoc());

//		List<Department> deptList = dao.getAll();
//		DepartmentDAOImpl dao = new DepartmentDAOImpl();
//		for (Department dept : deptList) {
//			System.out.println("DEPTNO = " + dept.getDeptno());
//			System.out.println("DNAME = " + dept.getDname());
//			System.out.println("LOC = " + dept.getLoc());
//			System.out.println("==========================");
//		}

		Department dept = new Department(deptno, dname, loc);
		DepartmentDAOImpl dao = new DepartmentDAOImpl();
		dao.update(dept);
		System.out.println("更新成功");
	}

}
