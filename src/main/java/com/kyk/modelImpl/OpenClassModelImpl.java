package com.kyk.modelImpl;

import com.kyk.domain.Course;
import com.kyk.domain.OpenClass;
import com.kyk.model.OpenClassModel;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpenClassModelImpl implements OpenClassModel {


    private static final String SELECT_SQL = """
            select oc.id id, oc.start_date date, oc.teacher, 
            c.id courseId, c.name, c.duration, c.fees,  c.description 
            from open_class oc join course c on oc.course_id = c.id where c.id = ?
            """;
    private static final String INSERT_SQL = "insert into open_class(course_id, start_date, teacher) values (?, ?, ?)";
    private static final String SELECT_ONE = "select * from open_class where id = ?";
    private DataSource dataSource;

    public OpenClassModelImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<OpenClass> findByCourse(int id) {

        var list = new ArrayList<OpenClass>();

        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(SELECT_SQL)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var c = new Course();
                c.setId(rs.getInt("courseId"));
                c.setName(rs.getString("name"));
                c.setFees(rs.getInt("fees"));
                c.setDuration(rs.getInt("duration"));
                c.setDescription(rs.getString("description"));

                var oc = new OpenClass();
                oc.setId(rs.getInt("id"));
                oc.setCourse(c);
                oc.setTeacher(rs.getString("teacher"));
                oc.setStartDate(rs.getDate("date").toLocalDate());

                list.add(oc);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void create(OpenClass openClass) {

        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(INSERT_SQL)) {

            stmt.setInt(1, openClass.getCourse().getId());
            stmt.setDate(2, java.sql.Date.valueOf(openClass.getStartDate()));
            stmt.setString(3, openClass.getTeacher());


            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public OpenClass findById(int ocId) {

        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(SELECT_ONE)) {

            stmt.setInt(1, ocId);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var openClass = new OpenClass();
                openClass.setId(rs.getInt("id"));
                openClass.setStartDate(rs.getDate("start_date").toLocalDate());
                openClass.setTeacher(rs.getString("teacher"));
                return openClass;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
