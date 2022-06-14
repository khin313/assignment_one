package com.kyk.modelImpl;

import com.kyk.domain.Course;
import com.kyk.domain.OpenClass;
import com.kyk.domain.Registration;
import com.kyk.model.RegistrationModel;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationModelImpl implements RegistrationModel {

    private static final String SELECT_ALL = """
            select rg.id regId, rg.student, rg.phone, rg.email, 
            oc.start_date date, oc.teacher, oc.id ocId 
            from registration rg join open_class oc on rg.open_class_id = oc.id where oc.id = ?
            """;
    private static final String INSERT_SQL = "insert into registration(open_class_id, student, phone, email) values (?, ?, ?, ?)";
    private DataSource dataSource;

    public RegistrationModelImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Registration> findByClassModel(int id) {
        var list = new ArrayList<Registration>();

        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(SELECT_ALL)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            while (rs.next()) {

                var c = new Course();


                var oc = new OpenClass();

                oc.setId(rs.getInt("ocId"));
                oc.setTeacher(rs.getString("teacher"));
                oc.setStartDate(rs.getDate("date").toLocalDate());

                var rg = new Registration();
                rg.setId(rs.getInt("regId"));
                rg.setOpenClass(oc);
                rg.setStudent(rs.getString("student"));
                rg.setPhone(rs.getString("phone"));
                rg.setEmail(rs.getString("email"));

                list.add(rg);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }

    @Override
    public void create(Registration registration) {

        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(INSERT_SQL)) {

            stmt.setInt(1, registration.getOpenClass().getId());
            stmt.setString(2, registration.getStudent());
            stmt.setString(3, registration.getPhone());
            stmt.setString(4, registration.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
