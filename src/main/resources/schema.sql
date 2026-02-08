CREATE TABLE IF NOT EXISTS quizzes (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL UNIQUE,
    level VARCHAR(10) NOT NULL,
    CONSTRAINT chk_quiz_level CHECK (level IN ('EASY', 'HARD'))
    );

CREATE TABLE IF NOT EXISTS questions (
                                         id SERIAL PRIMARY KEY,
                                         quiz_id INT NOT NULL,
                                         text TEXT NOT NULL,
                                         correct_answer TEXT NOT NULL,
                                         points INT NOT NULL,
                                         CONSTRAINT fk_questions_quiz
                                         FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE,
    CONSTRAINT chk_points_positive CHECK (points > 0)
    );

-- optional: avoid duplicates for same quiz
CREATE UNIQUE INDEX IF NOT EXISTS uq_question_per_quiz
    ON questions(quiz_id, text, correct_answer);
