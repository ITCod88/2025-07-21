package com.boot.jpa.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boot.jpa.model.entity.MemberEntity;

@Repository
public interface MemberDao extends JpaRepository<MemberEntity, Integer> {
	
	//id와 pw가 같은 데이터 조회
	public MemberEntity findByMemberidAndMemberpw(String myid, String mypw);
	
	@Query("SELECT m FROM MemberEntity m ORDER BY m.memberno DESC")
	public List<MemberEntity> selectAll();
	
	public List<MemberEntity> findAll();

	public MemberEntity findByMemberno(int memberno);
	
	@Transactional
	public void deleteByMemberno(int memberno);
}
