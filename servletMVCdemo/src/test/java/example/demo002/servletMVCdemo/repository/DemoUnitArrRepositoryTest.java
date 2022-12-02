package example.demo002.servletMVCdemo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import example.demo002.servletMVCdemo.dto.DemoUnit;

public class DemoUnitArrRepositoryTest {
//	DemoUnitRepository duRepository = new DemoUnitRepository();
	DemoUnitRepository duRepository = DemoUnitRepository.getInstance();
//	DemoUnitArrRepository duRepository = new DemoUnitArrRepository.getInstance();
//	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	
	@Test
	void insert() {
		DemoUnit du = new DemoUnit("id", "name1");
		DemoUnit insertDu = duRepository.insert(du);
		DemoUnit selectDu = duRepository.selectByNo(insertDu.getNo());
		
		assertThat(insertDu).isEqualTo(selectDu);
	}
	
	@Test
	void insert2() {
		DemoUnit du = new DemoUnit("id1", "name1");
		DemoUnit insertDu = duRepository.insert(du);
		
		DemoUnit du2 = new DemoUnit("id2", "name2");
		DemoUnit insertDu2 = duRepository.insert(du2);
		
		DemoUnit selectDu = duRepository.selectByNo(insertDu.getNo());
		
		assertThat(insertDu2).isNotEqualTo(selectDu);
//		isEqualTo(selectDu);
	}
	
	@Test
	void diffRepository() {
//		DemoUnitArrRepository duRepository1 = new DemoUnitArrRepository();
//		DemoUnitArrRepository duRepository2 = new DemoUnitArrRepository();
		
//		DemoUnitArrRepository duRepository1 = DemoUnitArrRepository.getInstance();
		//싱글톤 new 로 객체 생성 불가능 
		DemoUnit du1 = new DemoUnit("id1", "name1");
		duRepository.insert(du1);
//		DemoUnitArrRepository duRepository2 = DemoUnitArrRepository.getInstance();
		DemoUnit du2 = new DemoUnit("id1", "name1");
		duRepository.insert(du2);
		
		
		
		//같은 값을 가지는 객체 -> DB 2개에 저장 -> 1개의 DB에서만 삭제 - 다른 DB에 남아있음 ->동기화 안됨
		//
//		DemoUnit sDu1 = duRepository1.selectByNo(du1.)
		List<DemoUnit> db1 = duRepository.selectAll();
		List<DemoUnit> db2 = duRepository.selectAll();
		
		assertThat(db1.size()).isEqualTo(2);
		assertThat(db2.size()).isEqualTo(2);
		
		
	}
	@AfterEach
	void afterDeleteAll() {
		duRepository.deleteAll();
	}
}
