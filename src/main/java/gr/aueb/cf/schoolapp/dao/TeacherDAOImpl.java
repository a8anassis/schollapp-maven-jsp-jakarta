package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";
        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();
            ps.setString(1, firstname);
            ps.setString(2, lastname);

            // Anti-pattern
            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            // return auto-generated keys
            int generatedId = 0;
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
            teacher.setId(generatedId);
            generatedKeys.close();
        } catch (SQLException e1) {
            DBUtil.rollbackTransaction();
            throw new TeacherDAOException("SQL Error in Teacher" + teacher + " insertion");
        }
        return teacher;
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();
            return teacher;
        } catch (SQLException e1) {
            //e1.printStackTrace();
            DBUtil.rollbackTransaction();
            throw new TeacherDAOException("SQL Error in Teacher" + teacher + " insertion");
        }
    }

    @Override
    public void delete(int id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();
        } catch (SQLException e) {
                //e.printStackTrace();
            DBUtil.rollbackTransaction();
            throw new TeacherDAOException("SQL Error in Teacher with id: " + id + " deletion");
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
        List<Teacher> teachers = new ArrayList<>(); // size == 0

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ) {
            ResultSet rs;
            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
                teachers.add(teacher);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new TeacherDAOException("SQL Error in get teacher with lastname: " + lastname);
        }
        return teachers;
    }

    @Override
    public Teacher getById(int id) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE ID = ?";
        Teacher teacher = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs;
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return teacher;
    }
}
