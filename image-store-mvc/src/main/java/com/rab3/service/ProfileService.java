package com.rab3.service;

import java.util.List;

import com.rab3.controller.dto.ProfileDTO;

public interface ProfileService {
	

	String saveProfile(ProfileDTO profileDTO);

	

	String update(ProfileDTO profileDTO);



	ProfileDTO findById(int aid);


	String delete(String uname);



	String findPassword(String email);


	ProfileDTO findByEmail(String email);



	byte[] findPhotoById(int aid);



	List<ProfileDTO> findAll(int pageid, int total);



	int findTotalSignup();

}
