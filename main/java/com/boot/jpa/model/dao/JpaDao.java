package com.boot.jpa.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.jpa.model.entity.JpaEntity;

@Repository
public interface JpaDao extends JpaRepository<JpaEntity, Integer> {
	//findAll(), save(), findById(), delete() 이미 구현되어 있는 메서드, Transactional이 어노테이션되어 있다.
	
	public List<JpaEntity> findAll();
	
	public JpaEntity findByMyno(int myno); //findByMyno는 데이터변경이 아니기때문에 Transactional을 안붙인다.
	
	//public JpaEntity save(JpaEntity dto); , save와 delete는 다시 선언하지 않고 만들어진 것을 사용하는게 가장 좋은 방법이다.
	
	@Transactional
	public void deleteByMyno(int myno);
	//데이터 변경 등의 작업을 실행하기위해 Transactional을 어노테이션해야한다. (내부적으로 만들어진 메서드가 아닌 임의로 생성한 메서드는 Transactional을 설정해야한다.)

	//개발자가 jpql을 작성해 실행한다.
	@Modifying
	@Query("DELETE FROM JpaEntity j WHERE j.myno= :myno")
	@Transactional
	public void delBoard(@Param("myno")int myno);
	

}
