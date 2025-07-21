package com.boot.jpa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MYBOARD")
public class JpaEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //시퀀스 기반
	@Column
	private int myno;
	
	@Column(updatable=false, nullable=false) //updatable=false : updata, set에서 제외
	private String myname;
	@Column
	private String mytitle;
	@Column
	private String mycontent;
	
	@Column(updatable=false)
	@Temporal(TemporalType.DATE) //날짜 값들을 어떤 형식으로 저장할 것이냐 (TIME:시간, DATE:날짜, TIMESTAMP:날짜, 시간 둘다 저장)
	private Date mydate;
	
	public JpaEntity() {
		super();
	}
	public JpaEntity(int myno, String myname, String mytitle, String mycontent, Date mydate) {
		super();
		this.myno = myno;
		this.myname = myname;
		this.mytitle = mytitle;
		this.mycontent = mycontent;
		this.mydate = mydate;
	}
	
	public int getMyno() {
		return myno;
	}
	public void setMyno(int myno) {
		this.myno = myno;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public String getMytitle() {
		return mytitle;
	}
	public void setMytitle(String mytitle) {
		this.mytitle = mytitle;
	}
	public String getMycontent() {
		return mycontent;
	}
	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}
	public Date getMydate() {
		return mydate;
	}
	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}
	
}



//이전에 Dto를 Entity로 변경