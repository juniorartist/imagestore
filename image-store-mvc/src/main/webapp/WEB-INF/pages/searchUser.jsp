<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>searchUser</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<header style="height: 30px; background-color: #03a9f4;"> </header>
	<div class="container">
		
		<form action="searchUser"  method="post">
      <label><b>Email Id</b></label>
      <input type="email" name="email"  class="form-control"  value="${param.email}">
      <br/>
      
		<button type="submit" class="btn btn-primary">GO</button>
		
     
      </form>
   
   		<br/>
     	SHOW PROFILE
			

		<hr />
		
		<h4>NAME : ${profileDTO.name}</h4>
		<h4>EMAIL : ${profileDTO.email}</h4>
		<h4>GENDER : ${profileDTO.gender}</h4>
			
				
			
				
		
		<hr />
		
	</div>
</body>
</html>