<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<body>

<div class="container mt-4">
		<h1>Using IoC Container</h1>
		<h3>Add New Student Registeration for ${course.name } on ${opClass.startDate }</h3>

	
	<div class="row">
		<div class="col-4">
			 
			 <c:url var="save" value="/registrations">
			 	<c:param name="courseId" value="${course.id }"></c:param>
			 	<c:param name="classId" value="${openClass.id }"></c:param>
			 </c:url>
			<form action="${save }" method="post">
			
			
				<div class="mb-3">
					<label class="form-label">Open Class Id</label>
					<input type="number" name="classId" value="${openClass.id }" readonly="readonly" class="form-control" required="required"/>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Course Name</label>
					<input type="text" name="courseName" value="${course.name }" readonly="readonly" class="form-control" required="required"/>
				</div>
				
				
								
				<div class="mb-3">
					<label class="form-label">Student</label>
					<input type="text" name="stu_name" placeholder="Enter Student Name"  class="form-control" required="required"/>
				</div>
				
								
				<div class="mb-3">
					<label class="form-label">Phone</label>
					<input type="text" name="phone" placeholder="Enter Phone" class="form-control" required="required"/>
				</div>
				
								
				<div class="mb-3">
					<label class="form-label">Email</label>
					<input type="email" name="email" placeholder="Enter Email" class="form-control" required="required"/>
				</div>
				
				
				<input type="submit" value="Save Registration" class="btn btn-primary" />
				<input type="reset" value="Clear" class="btn btn-primary" />
			</form>
		</div>
		
		
	</div>
</div>
</body>
</html>