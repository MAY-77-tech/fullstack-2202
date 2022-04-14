package com.jdc.assignment.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.RegistrationModel;

public class RegistrationModelImpl implements RegistrationModel {

	private static final String SELECT_SQL = """
			select reg.id, reg.student, reg.phone, reg.email, 
			c.name course,oc.teacher, oc.start_date, c.fees,c.duration,c.description
			from registration reg 
			join open_class oc on oc.id=reg.open_class_id 
			join course c on c.id = oc.course_id where oc.id=?;
			""";
	private static final String INSERT_REG = "insert into registration (open_class_id,student, phone, email) values (?,?,?,?)";
	
	private DataSource dataSource;

	public RegistrationModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Registration> getAll(int openClassId,int courseId) {
		
		var list = new ArrayList<Registration>();

		try(var conn=dataSource.getConnection();
				var stmt=conn.prepareStatement(SELECT_SQL)){
			stmt.setInt(1, openClassId);
			var result = stmt.executeQuery();
			
			while(result.next()) {
				
				var course = new Course();
				course.setName(result.getString("course"));
				course.setDuration(result.getInt("duration"));
				course.setFees(result.getInt("fees"));
				course.setDescription(result.getString("description"));
				
				var opClass = new OpenClass();
				opClass.setId(openClassId);
				opClass.setCourse(course);
				opClass.setStartDate(result.getDate("start_date").toLocalDate());
				opClass.setTeacher(result.getString("teacher"));
				
				var reg = new Registration();
				reg.setId(result.getInt("id"));
				reg.setStudent(result.getString("student"));
				reg.setPhone(result.getString("phone"));
				reg.setEmail(result.getString("email"));
				reg.setOpenClass(opClass);
				
				list.add(reg);
			
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void save(Registration reg) {
		try(var conn=dataSource.getConnection();
				var stmt=conn.prepareStatement(INSERT_REG)){
			
			stmt.setInt(1, reg.getOpenClass().getId());
			stmt.setString(2, reg.getStudent());
			stmt.setString(3, reg.getPhone());
			stmt.setString(4, reg.getEmail());
			
			stmt.executeUpdate();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	
}
