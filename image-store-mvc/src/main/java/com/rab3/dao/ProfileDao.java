package com.rab3.dao;


import java.util.List;

import com.rab3.dao.entity.ProfileEntity;



public interface ProfileDao {

	

	String update(ProfileEntity profileEntity);

	ProfileEntity findById(int aid);

	String delete(String uname);

	String forgotPassword(String email);

	List<ProfileEntity> findAll(int pageid, int total);	

	ProfileEntity findByEmail(String email);

	byte[] findPhotoById(int aid);

	String saveProfile(ProfileEntity profileEntity);

	int findTotalSignup();



	


	
	

}
