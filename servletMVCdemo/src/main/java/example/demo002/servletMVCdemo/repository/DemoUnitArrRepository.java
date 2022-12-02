package example.demo002.servletMVCdemo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.demo002.servletMVCdemo.dto.DemoUnit;


public class DemoUnitArrRepository {
	private static List<DemoUnit> db = new ArrayList<>();
//	private List<DemoUnit> db;
	private static int seq = 1;
	private static final DemoUnitArrRepository instance = new DemoUnitArrRepository();
//	static -class레벨이다
	public static DemoUnitArrRepository getInstance() {
//		DemoUnitArrRepository instance = new DemoUnitArrRepository(); 이렇게 되면 싱글톤이 깨지는것 
		return instance;
	}
	
	private DemoUnitArrRepository() {
//		db = new ArrayList<>(); //new 할때마다 db 내용 날라가니까 그렇게 하지 못하게 하기 위해
	}
	public DemoUnit insert(DemoUnit demoUnit) {
		demoUnit.setNo(seq++);
		db.add(demoUnit);
		return demoUnit;
	}
	public DemoUnit selectByNo(int no) {
		for(DemoUnit du : db) {
			if(du.getNo() == no) {
				return du;
			}
		}
		return null;
	}
	
	public List<DemoUnit> selectAll() {
		return db;
	}
	
	public void deleteAll() {
		db.clear();
	}
}
