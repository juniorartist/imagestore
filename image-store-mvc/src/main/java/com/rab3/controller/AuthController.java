package com.rab3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3.controller.dto.ProfileDTO;

import com.rab3.service.ProfileService;

@Controller
public class AuthController {
	
	@Autowired
	private ProfileService profileService;
	
	
	
	@Autowired
	@Qualifier("pkdataSource")
	private DataSource ds;
	
	@GetMapping("/foo")
	public String helloWorld() {
		return "aha";   
	}
	
	@GetMapping({"/auth","/","/login"})
	public String showLogin() {
		return "login";
	}
	
	//action=auth
	//method = POST
	@PostMapping("/auth")
	public String auth(@RequestParam String username,@RequestParam String password,Model model,HttpSession  session) {
			JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
			String sql="select username,password,name,email,gender,aid from profiles_tbl where username =? and password = ?";
			List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql, new Object[] {username,password},new BeanPropertyRowMapper<ProfileDTO>(ProfileDTO.class));
			if(profileDTOs.size()==1) {
				session.setAttribute("profileDTO", profileDTOs.get(0));
				model.addAttribute("magic", profileDTOs.get(0));
				return "home";
			}else {
				model.addAttribute("msg", "Sorry!! username and password are not valid!!!!!!!!!!!!!!!");
				return "login";
			}
	}
	
	
	
	@GetMapping("/forgotPassword")
	public String forgetPasswordPage() {
		return "forgotPassword";
	}
	
	
	@PostMapping("/forgotPassword")
	public String forgetPasswordPage(@RequestParam String email, Model model) {
		   
		
		String passWord = profileService.findPassword(email);
		if(passWord != null) {
			
			model.addAttribute("password","Hello your password is   "+ passWord);
		}
		else {
		
		model.addAttribute("password", "Sorry!! email is not valid!!!!!!!!!!!!!!!");
		}
		return "forgotPassword";
	}
	

}
