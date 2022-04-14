package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;
import com.jdc.assignment.model.RegistrationModel;

@WebServlet(urlPatterns = { "/registrations", "/registration-edit" })
public class RegistrationServlet extends AbstractBeanFactoryServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var courseId = Integer.parseInt(req.getParameter("courseId"));
		var course = getBean("courseModel", CourseModel.class).findById(courseId);
		req.setAttribute("course", course);
		
		var classId = Integer.parseInt(req.getParameter("classId"));
		var opClass = getBean("openClassModel", OpenClassModel.class).findById(classId);
		req.setAttribute("openClass", opClass);

		var page = switch (req.getServletPath()) {
		case "/registrations" -> {
			var regModel = getBean("regModel", RegistrationModel.class).getAll(classId, courseId);
			req.setAttribute("registrations", regModel);
			yield "registrations";

		}
		default -> "registration-edit";
		};

		System.out.println(page);

		getServletContext().getRequestDispatcher("/%s.jsp".formatted(page)).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var classId = Integer.parseInt(req.getParameter("classId"));
		var courseId = Integer.parseInt(req.getParameter("courseId"));
		
		var reg = new Registration();
		reg.setOpenClass(getBean("openClassModel", OpenClassModel.class).findById(classId));
		reg.setStudent(req.getParameter("stu_name"));
		reg.setPhone(req.getParameter("phone"));
		reg.setEmail(req.getParameter("email"));
		
		var model = getBean("regModel", RegistrationModel.class);
		
		model.save(reg);
		
		resp.sendRedirect("/registrations?courseId=%d&classId=%d".formatted(courseId,classId));
		
	}

}
