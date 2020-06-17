package com.rab3.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.rab3.dao.entity.ProfileEntity;


@Repository
public class ProfileDaoImpl implements ProfileDao{
	
	@Autowired
	@Qualifier("pkdataSource")
	private DataSource ds;
	
	
	@Override
	public String saveProfile(ProfileEntity profileEntity){
		

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		
		Timestamp timestamp = new Timestamp(new Date().getTime());
		LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue sqlLobValue = null;
		
		try {
			
			sqlLobValue = new SqlLobValue(profileEntity.getPhoto().getBytes(),lobHandler);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "insert into profiles_tbl(username,password,name,email,gender,photo,doe) values(?,?,?,?,?,?,?)";
		
		Object[] data = {profileEntity.getUsername(),profileEntity.getPassword(),profileEntity.getName(),profileEntity.getEmail(),profileEntity.getGender(),
						sqlLobValue,timestamp};
	
		jdbcTemplate.update(sql,data,new int [] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BLOB,Types.TIMESTAMP});
		
		return "success";
		
	}
	
	@Override
	public byte[] findPhotoById (int aid) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		
		String sql = "select photo from profiles_tbl where aid =" + aid;
		byte[] photo = jdbcTemplate.queryForObject(sql, byte[].class);
		return photo;
		
	}
	
	
	
	
	@Override
	public String update(ProfileEntity profileEntity) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		LobHandler lobHandler = new DefaultLobHandler();
		
		
		SqlLobValue sqlLobValue = null;
		try {
			
			
			if (profileEntity.getPhoto() == null || profileEntity.getPhoto().getBytes().length < 3) {
				

				String sql = "update profiles_tbl set username=?, name=?, email=?, gender=? where aid=?";
				Object [] data = {profileEntity.getUsername(), profileEntity.getName(), profileEntity.getEmail(), profileEntity.getGender(),profileEntity.getAid()};
				
				jdbcTemplate.update(sql,data,new int [] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER});
				
			}
			else {
				
				sqlLobValue = new SqlLobValue(profileEntity.getPhoto().getBytes(),lobHandler);
				String sql = "update profiles_tbl set username=?, name=?, email=?, gender=?, photo=? where aid=?";
				Object [] data = {profileEntity.getUsername(), profileEntity.getName(), profileEntity.getEmail(), profileEntity.getGender(), sqlLobValue,profileEntity.getAid()};
				
				jdbcTemplate.update(sql,data,new int [] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BLOB,Types.INTEGER});
			
			}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return "success";
		
	}
	
	
	@Override
	public ProfileEntity findById(int aid) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		String sql = "select aid,username,password,name,email,gender,doe from profiles_tbl where aid = " +aid;
		ProfileEntity profileEntity = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(ProfileEntity.class));
		
		return profileEntity;
		
	}
	
	@Override
	public String delete(String uname) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		jdbcTemplate.update("delete  from  profiles_tbl where username =?",new Object[] {uname});
		
		return "success";
	}
	
	@Override
	public String forgotPassword(String email) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		
		String password=jdbcTemplate.queryForObject("select password  from profiles_tbl where email =?", new Object[] {email},String.class);
		
		return password;
	}
	
	@Override
	public List<ProfileEntity> findAll(int pageid,int total) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		String sql = "select aid,username,password,name,email,gender,doe from profiles_tbl order by aid desc limit "+(pageid-1)+","+total;
		
		 List<ProfileEntity> profileEntities = jdbcTemplate.query(sql,
				 new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileEntities;
		
	}
	
	
	@Override
	public ProfileEntity findByEmail(String email) {
		
		String sql = "select * from profiles_tbl where email = ?" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		ProfileEntity profileEntity = jdbcTemplate.queryForObject(sql,new Object[] {email},new BeanPropertyRowMapper<>(ProfileEntity.class));
		
		return profileEntity;
		
	}
	
	@Override
    public int findTotalSignup() {
        String sql = "select count(*) from profiles_tbl";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        Integer count=jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

	
	
	

}
	