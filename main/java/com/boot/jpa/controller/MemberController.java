package com.boot.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.jpa.model.dao.MemberDao;
import com.boot.jpa.model.entity.MemberEntity;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberDao dao;
	
	@GetMapping("/test")
	public String selectAll() {
		
		List<MemberEntity> res = dao.selectAll();
		
		MemberEntity dto = res.get(0);
		System.out.println(dto.getMemberid());
		System.out.println(dto.getMemberpw());
		System.out.println(dto.getMembername());
		System.out.println(dto.getMemberno());
		
		dto = dao.findByMemberidAndMemberpw("admin", "1234");
		System.out.println(dto.getMemberid());
		System.out.println(dto.getMemberpw());
		System.out.println(dto.getMembername());
		System.out.println(dto.getMemberno());
		
		return "index";
	}
		
	@GetMapping("/list")
	public String selecAll(Model model) {
		System.out.println("[list]");
		
		List<MemberEntity> list = dao.findAll(Sort.by(Sort.Direction.DESC, "memberno"));
		
		model.addAttribute("list", list);
		return "memberlist";
	}
	
	@GetMapping("/detail")
	public String selectOne(int memberno, Model model) {
		System.out.println("[detail]");
		
		MemberEntity res = dao.findByMemberno(memberno);
		
		model.addAttribute("dto", res);
		return "memberdetail";
	}
	
	@GetMapping("/insertform")
	public String insertForm() {
		System.out.println("[insertForm]");
		return "memberinsert";
	}
	
	@PostMapping("/insert")
	public String insert(MemberEntity dto) {
		System.out.println("[insert]");
		
		dao.save(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("/updateform")
	public String updateForm(int memberno, Model model) {
		System.out.println("[updateForm]");
		
		model.addAttribute("dto", dao.findByMemberno(memberno));
		return "memberupdate";
	}
	
	@PostMapping("/update")
	public String update(MemberEntity dto) {
		
		dao.save(dto);
		
		return "redirect:/member/detail?memberno=" + dto.getMemberno();
	}
	
	@GetMapping("/delete")
	public String delete(int memberno) {
		System.out.println("[delete]");
		
		dao.deleteByMemberno(memberno);
		
		return "redirect:/member/list";
	}
}
