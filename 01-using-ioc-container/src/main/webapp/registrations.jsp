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
<body class="container mt-4">

	<h1>Using IoC Container</h1>

	<h3>List of Registeration for ${course.name } on ${openClass.startDate }</h3>

	<div>
		<c:url var="addNewReg" value="/registration-edit">
			<c:param name="classId" value="${openClass.id }"></c:param>
			<c:param name="courseId" value="${course.id }"></c:param>
		</c:url>
		<a class="btn btn-primary" href="${addNewReg }">Add New Registration</a>
	</div>
	
	<c:choose>
		
			<c:when test="${empty registrations }">
				<div class="alert alert-warning">There is no Registration for ${course.name } on ${openClass.startDate }.Please create new Registration.</div>
			</c:when>
			
			<c:otherwise>
				<table class="table table-striped">
				
					<thead>
						<tr>
							<th>Registration ID</th>
							<th>Studen</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Course</th>
							<th>Teacher</th>
							<th>Start Date</th>
							<th>Fees</th>
							<th>Duration</th>
							<th>Description</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var= "reg" items="${registrations }">
							<tr>
								<th>${reg.id }</th>
								<th>${reg.student }</th>
								<th>${reg.phone }</th>
								<th>${reg.email }</th>
								<th>${reg.openClass.course.name }</th>
								<th>${reg.openClass.teacher }</th>
								<th>${reg.openClass.startDate }</th>
								<th>${reg.openClass.course.fees }</th>
								<th>${reg.openClass.course.duration } Months</th>
								<th>${reg.openClass.course.description }</th>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
			
		</c:choose>
		<div class="mt-2"><a href="/">Home</a></div>
	
</body>

</body>
</html>