package com.boot.jpa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.jpa.model.dao.JpaDao;
import com.boot.jpa.model.entity.JpaEntity;

@Controller
@RequestMapping("/board")
public class JpaController {

	@Autowired
	private JpaDao dao;
	
	@GetMapping("/list")
	public String selectAll(Model model) {
		System.out.println("[list]");
		
//		List<JpaEntity> list = dao.findAll();
		List<JpaEntity> list = dao.findAll(Sort.by(Sort.Direction.DESC, "myno"));
		
		model.addAttribute("list", list);
		return "jpalist";
	}
	
	@GetMapping("/detail")
	public String selectOne(int myno, Model model) {
		System.out.println("[detail]");
		
		JpaEntity dto = dao.findByMyno(myno);
		//findBy필드이름 : 필드 이름을 기반으로 조회	
	
		model.addAttribute("dto", dto);
		return "jpadetail";
	}
	
	@GetMapping("/insertform")
	public String inserForm() {
		System.out.println("[insertForm]");
		return "jpainsert";
	}
	
	@PostMapping("/insert")
	public String insert(JpaEntity dto) {
		System.out.println("[insert]");
		dto.setMydate(new Date());
		
		//dto.setMyno(1) 이렇게 코드를 작성하게 되면 no가 1인 값이 update된다. 
		//			     동일한 no값을 넣을 경우 update, 동일한 no값이 없는 경우 insert 
		dao.save(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("/updateform")
	public String updateForm(int myno, Model model) {
		System.out.println("[updateForm]");
		
		model.addAttribute("dto", dao.findByMyno(myno));
		return "jpaupdate";
	}
	
	@PostMapping("/update")
	public String update(JpaEntity dto) {
		System.out.println("[update]");
		
		dao.save(dto);
		//jpa에서 save() => insert or update 작업을 진행한다.
		//id가 null => insert 실행
		//id가 존재, db에 id와 일치하는 데이터 존재 => update 실행
		
		return "redirect:/board/detail?myno=" + dto.getMyno();
	}
	
	@GetMapping("/delete")
	public String delete(int myno) {
		System.out.println("[delete]");
		
		//dao.deleteById(myno);
		dao.deleteByMyno(myno);
		
		return "redirect:/board/list";
	}
}
