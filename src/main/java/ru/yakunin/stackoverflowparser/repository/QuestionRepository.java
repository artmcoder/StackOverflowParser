package ru.yakunin.stackoverflowparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yakunin.stackoverflowparser.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
