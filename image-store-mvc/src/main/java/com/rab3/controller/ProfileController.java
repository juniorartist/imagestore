package com.rab3.controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.service.ProfileService;


@Controller
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	
	@Autowired
	@Qualifier("pkdataSource")
	private DataSource ds;
	
	
	@GetMapping("/imageLoader")
 	public void showImage(@RequestParam int aid,
 			HttpServletResponse httpServletResponse) throws IOException {
 		byte[] photo = profileService.findPhotoById(aid);
 		//I am going to write photo into response
 		
 		httpServletResponse.setContentType("image/png");
 		
 		ServletOutputStream outputStream=httpServletResponse.getOutputStream();
 		
 		if(photo!=null && photo.length>0) {
 			//writing photo as a byte array into the response body
 			outputStream.write(photo);
 		}else {
 			outputStream.write(new byte[] {});
 		}
 		//go to the client
 		outputStream.flush();
 	}
	
	@GetMapping("/profiles")
	public String showProfile(@RequestParam(required=false,defaultValue="1") String  pageid,Model model) {
		
		int pageSize = 3;
 		int ppageid = Integer.parseInt(pageid);
 		if(ppageid <= 0) {
 			ppageid = 1;
 		}
 		//2
 		//6
 		if(ppageid > 1) {
 			ppageid = (ppageid-1)*pageSize+1;  
 		}
		
		
		List<ProfileDTO> profileDTOs = profileService.findAll(ppageid, pageSize);
		int totalRecords=profileService.findTotalSignup();
		
		model.addAttribute("profileDTOs",profileDTOs);
		model.addAttribute("recordStarts", ppageid);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageid", pageid);
		model.addAttribute("totalRecords", totalRecords);
		return "profiles";
		
	}
	
	
	@GetMapping("/register")
	public String registerPage() {
		
		return "register";
		
	}
	
	
	
	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute ProfileDTO profileDTO,Model model) {
		
		profileService.saveProfile(profileDTO);
		model.addAttribute("msg","REGISTERED SUCCESSFULLY");
		
		return "register";
		
	}
	
	
	@GetMapping("/editProfile")
	public String editProfile(@RequestParam int aid,Model model ) {
		
		ProfileDTO profileDTO = profileService.findById(aid);
		model.addAttribute("profileDTO",profileDTO);
		
		return "editProfile";
		
	}
	
	
	@GetMapping("/updateProfile")
	public String updateProfle() {
		
		return "home";
		
	}
	
	
	@PostMapping("/updateProfile")
	public String updateEditProfle(@ModelAttribute ProfileDTO profileDTO , Model model) {
		
		profileService.update(profileDTO);
		
		model.addAttribute("magic",profileDTO);
		model.addAttribute("msg","UPDATED SUCCESSFULLY");
		
		return "home";
		
	}
	
	
	@GetMapping("/deleteProfile")
	public String deleteProfile(@RequestParam String uname,Model model) {
		
		profileService.delete(uname);
		
		return "redirect:/profiles";
		
	}
	
	
	@GetMapping("/searchUser")
	public String searchUser() {
		return"searchUser";
	}
	
	
	
	@PostMapping("/searchUser")
	public String searchUser(@RequestParam String email, Model model) {
		
		ProfileDTO profileDTO =  profileService.findByEmail(email);
		model.addAttribute("profileDTO",profileDTO);
		
		return "searchUser";
		
		
	}
	
	
	
	
	
}
