package ru.yakunin.stackoverflowparser.service;

import ru.yakunin.stackoverflowparser.entity.Detail;
import ru.yakunin.stackoverflowparser.entity.Question;

import java.util.List;

public interface QuestionService {
    void save(Question question);

    List<Question> getAll();

    Question getById(int id);
}
