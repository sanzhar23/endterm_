package org.example.repository;

import org.example.db.DatabaseConnection;
import org.example.model.EasyQuiz;
import org.example.model.HardQuiz;
import org.example.model.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {

    public List<Quiz> getAll() {
        String sql = "SELECT id, name, level FROM quizzes ORDER BY id";
        List<Quiz> quizzes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                quizzes.add(mapRow(rs));
            }
            return quizzes;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to get quizzes", e);
        }
    }
    public void delete(int id) {
        String sql = "DELETE FROM quizzes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete quiz", e);
        }
    }

    public Quiz getById(int id) {
        String sql = "SELECT id, name, level FROM quizzes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to get quiz by id", e);
        }
    }

    public Quiz create(String name, String level) {
        String sql = "INSERT INTO quizzes(name, level) VALUES (?, ?) RETURNING id, name, level";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, level);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create quiz", e);
        }
    }

    private Quiz mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String level = rs.getString("level");

        if ("EASY".equalsIgnoreCase(level)) return new EasyQuiz(id, name);
        if ("HARD".equalsIgnoreCase(level)) return new HardQuiz(id, name);
        throw new SQLException("Unknown level: " + level);
    }
}
