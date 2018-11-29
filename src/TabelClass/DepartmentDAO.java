package TabelClass;

import java.util.List;

public interface DepartmentDAO {
	void add(Department department);

	void update(Department department);

	void delete(int deptno);

	Department findByPK(int deptno);// 我們查詢後的結果會回傳一筆部門物件，所以類型用Department來接

	List<Department> getAll();// 因為得到的會是部門物件的集合體(很多筆部門資料物件)，所以用集合來接收

}
