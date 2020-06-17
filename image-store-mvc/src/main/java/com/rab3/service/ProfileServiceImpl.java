package com.rab3.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.dao.ProfileDao;
import com.rab3.dao.entity.ProfileEntity;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	
	@Autowired
	private ProfileDao profileDao;
	
	
	@Override
 	public byte[] findPhotoById(int aid) {
 		byte[] imaga=profileDao.findPhotoById(aid);
 		return imaga;
 	}

	

	@Override
	public String update(ProfileDTO profileDTO) {
		ProfileEntity profileEntity = new ProfileEntity();
		BeanUtils.copyProperties(profileDTO,profileEntity);
		String result = profileDao.update(profileEntity);
		return result;
	}

	@Override
	public ProfileDTO findById(int aid) {
		ProfileEntity entity = profileDao.findById(aid);
		ProfileDTO profileDTO = new ProfileDTO();
		BeanUtils.copyProperties(entity	,profileDTO);
		return profileDTO;
	}

	@Override
	public String delete(String uname) {
		String result = profileDao.delete(uname);
		return result;
	}

	@Override
	public String findPassword(String email) {
		String result = profileDao.forgotPassword(email);
		return result;
	}

	@Override
	public List<ProfileDTO> findAll(int pageid, int total) {
		
		
		List<ProfileEntity> listOfEntity = profileDao.findAll(pageid,total);
		List<ProfileDTO> profileDTOs = new ArrayList<>();
		
	
		
		for(ProfileEntity entity:listOfEntity) {
			
			ProfileDTO profileDTO = new ProfileDTO();
			BeanUtils.copyProperties(entity	,profileDTO);
			profileDTOs.add(profileDTO);
			
		}
		
		return profileDTOs;
	}

	@Override
	public ProfileDTO findByEmail(String email) {
		ProfileEntity entity = profileDao.findByEmail(email);
		ProfileDTO profileDTO = new ProfileDTO();
		BeanUtils.copyProperties(entity	,profileDTO);
		
		
		return profileDTO;
	}



	@Override
	public String saveProfile(ProfileDTO profileDTO) {
		ProfileEntity entity = new ProfileEntity();
		BeanUtils.copyProperties(profileDTO	,entity);
		String result = profileDao.saveProfile(entity);
		
		return result;
	}



	@Override
	public int findTotalSignup() {
		// TODO Auto-generated method stub
		return profileDao.findTotalSignup();
	}

	
	

}
