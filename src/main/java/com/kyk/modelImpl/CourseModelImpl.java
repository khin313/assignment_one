package com.kyk.modelImpl;

import com.kyk.domain.Course;
import com.kyk.model.CourseModel;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseModelImpl implements CourseModel {

    private static final String SELECT_ALL = "select * from course";
    private static final String INSERT = "insert into course(name, duration, fees, description) values (?, ?, ?, ?)";
    private static final String SELECT_ONE = "select * from course where id = ?";
    private DataSource dataSource;

    public CourseModelImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Course> getAll() {
        var list = new ArrayList<Course>();
        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(SELECT_ALL)) {
            var result = stmt.executeQuery();

            while (result.next()) {
                var course = new Course();
                course.setId(result.getInt("id"));
                course.setName(result.getString("name"));
                course.setFees(result.getInt("fees"));
                course.setDuration(result.getInt("duration"));
                course.setDescription(result.getString("description"));
                list.add(course);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void save(Course course) {
        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(INSERT)) {
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getDuration());
            stmt.setInt(3, course.getFees());
            stmt.setString(4, course.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course findById(int id) {
        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(SELECT_ONE)) {

            stmt.setInt(1, id);

            var result = stmt.executeQuery();

            while (result.next()) {
                var course = new Course();
                course.setId(result.getInt("id"));
                course.setName(result.getString("name"));
                course.setFees(result.getInt("fees"));
                course.setDuration(result.getInt("duration"));
                course.setDescription(result.getString("description"));
                return course;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
