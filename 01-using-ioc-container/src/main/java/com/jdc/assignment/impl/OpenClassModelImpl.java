package com.jdc.assignment.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.OpenClassModel;

public class OpenClassModelImpl implements OpenClassModel {

	private static final String SELECT_SQL = """
			select oc.id, oc.start_date, oc.teacher,
			c.id courseId, c.name, c.duration, c.fees, c.description
			from open_class oc join course c on oc.course_id=c.id
			where c.id=?
			""";
	private static final String INSERT_CLASS = "insert into open_class (course_id,start_date,teacher) values (?,?,?)";
	
	private static final String SELECT_BY_OPID = """
			select oc.id, oc.start_date, oc.teacher,
			c.id courseId, c.name, c.duration, c.fees, c.description
			from open_class oc join course c on oc.course_id=c.id
			where oc.id=?
			""";
	
	private DataSource dataSource;

	public OpenClassModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<OpenClass> findByCourse(int courseId) {

		var list = new ArrayList<OpenClass>();

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(SELECT_SQL)) {

			stmt.setInt(1, courseId);

			var result = stmt.executeQuery();

			while (result.next()) {
				var c = new Course();
				c.setId(result.getInt("courseId"));
				c.setName(result.getString("name"));
				c.setDuration(result.getInt("duration"));
				c.setFees(result.getInt("fees"));
				c.setDescription(result.getString("description"));

				var oc = new OpenClass();
				oc.setId(result.getInt("id"));
				oc.setCourse(c);
				oc.setTeacher(result.getString("teacher"));
				oc.setStartDate(result.getDate("start_date").toLocalDate());

				list.add(oc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void create(OpenClass openClass) {
		
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(INSERT_CLASS)){
			
			stmt.setInt(1, openClass.getCourse().getId());
			stmt.setDate(2, Date.valueOf(openClass.getStartDate()));
			stmt.setString(3, openClass.getTeacher());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public OpenClass findById(int opId) {
		try(var conn=dataSource.getConnection();
				var stmt=conn.prepareStatement(SELECT_BY_OPID)){
			
			stmt.setInt(1, opId);
			var result = stmt.executeQuery();
			
			while(result.next()) {

				var c = new Course();
				c.setId(result.getInt("courseId"));
				c.setName(result.getString("name"));
				c.setDuration(result.getInt("duration"));
				c.setFees(result.getInt("fees"));
				c.setDescription(result.getString("description"));

				var oc = new OpenClass();
				oc.setId(result.getInt("id"));
				oc.setCourse(c);
				oc.setTeacher(result.getString("teacher"));
				oc.setStartDate(result.getDate("start_date").toLocalDate());
				
				return oc;
			}
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

//select oc.id, oc.start_date, oc.teacher,c.id courseId, c.name, c.duration, c.fees, c.description from open_class oc join course c on oc.course_id=c.idwhere c.id=?
}
