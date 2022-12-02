package example.demo002.servletMVCdemo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.demo002.servletMVCdemo.dto.DemoUnit;

//MVC(Spring MVC)
//M Model --매개변수 모음집 컨트롤러로 가져온 데이터를 view로 넘길때 
//V View --화면을 그리는 부분
//C controller --서버 -repository - DB 쪽 처리
//Repository = Dao 와 같음
public class DemoUnitRepository {
	private static final DemoUnitRepository instance = new DemoUnitRepository();//싱글톤 만들기 ~
		
	private static Map<Integer,DemoUnit> db = new HashMap<>();
	//Map = key에 해당하는 value를 연결해주는 자료구조다 <key,value>
	private static int seq = 1;
	
	private DemoUnitRepository() {}//싱글톤 만들기 ~
	public static DemoUnitRepository getInstance() {
//		DemoUnitArrRepository instance = new DemoUnitArrRepository(); 이렇게 되면 싱글톤이 깨지는것 
		return instance;
	}
	
	public DemoUnit insert(DemoUnit demoUnit) {
		demoUnit.setNo(seq++);
		db.put(demoUnit.getNo(), demoUnit);
		return demoUnit;
	}
	public DemoUnit selectByNo(int no) {
		DemoUnit demoUnit = db.get(no);
		return demoUnit;
	}
	
	public List<DemoUnit> selectAll() {
		
		return new ArrayList<>(db.values());
	}
	
	public void deleteAll() {
		db.clear();
	}
}
