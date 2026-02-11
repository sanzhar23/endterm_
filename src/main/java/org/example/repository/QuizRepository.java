package org.example.repository;

import org.example.exception.DatabaseOperationException;
import org.example.model.EasyQuiz;
import org.example.model.HardQuiz;
import org.example.model.Quiz;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizRepository {

    private final DataSource dataSource;

    public QuizRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Quiz> getAll() {
        String sql = "SELECT id, name, level FROM quizzes ORDER BY id";
        List<Quiz> result = new ArrayList<>();

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(map(rs));
            }
            return result;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get all quizzes", e);
        }
    }

    public Quiz getById(int id) {
        String sql = "SELECT id, name, level FROM quizzes WHERE id = ?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
                return null;
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get quiz by id", e);
        }
    }

    public Quiz create(String name, String level) {
        String sql = "INSERT INTO quizzes(name, level) VALUES (?, ?) RETURNING id";

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, level);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int id = rs.getInt(1);
                return "EASY".equals(level) ? new EasyQuiz(id, name) : new HardQuiz(id, name);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create quiz", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM quizzes WHERE id = ?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete quiz", e);
        }
    }

    private Quiz map(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String level = rs.getString("level");
        return "EASY".equals(level) ? new EasyQuiz(id, name) : new HardQuiz(id, name);
    }
}
