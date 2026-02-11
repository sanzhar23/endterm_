package org.example.repository;

import org.example.exception.DatabaseOperationException;
import org.example.model.Question;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository {

    private final DataSource dataSource;

    public QuestionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Question create(Question q) {
        String sql = "INSERT INTO questions(quiz_id, text, correct_answer, points) VALUES (?,?,?,?) RETURNING id";

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, q.getQuizId());
            ps.setString(2, q.getText());
            ps.setString(3, q.getCorrectAnswer());
            ps.setInt(4, q.getPoints());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int id = rs.getInt(1);
                return new Question(id, q.getQuizId(), q.getText(), q.getCorrectAnswer(), q.getPoints());
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create question", e);
        }
    }

    public List<Question> getByQuizId(int quizId) {
        String sql = "SELECT id, quiz_id, text, correct_answer, points FROM questions WHERE quiz_id = ? ORDER BY id";
        List<Question> result = new ArrayList<>();

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, quizId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(map(rs));
                }
            }
            return result;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get questions by quizId", e);
        }
    }

    public boolean existsSameQuestion(int quizId, String text, String correctAnswer) {
        String sql = "SELECT 1 FROM questions WHERE quiz_id=? AND text=? AND correct_answer=? LIMIT 1";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, quizId);
            ps.setString(2, text);
            ps.setString(3, correctAnswer);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to check duplicate question", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete question", e);
        }
    }

    private Question map(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int quizId = rs.getInt("quiz_id");
        String text = rs.getString("text");
        String correctAnswer = rs.getString("correct_answer");
        int points = rs.getInt("points");
        return new Question(id, quizId, text, correctAnswer, points);
    }
}
