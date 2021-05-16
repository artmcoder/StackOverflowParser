package ru.yakunin.stackoverflowparser.service.impl;

import org.springframework.stereotype.Service;
import ru.yakunin.stackoverflowparser.entity.Detail;
import ru.yakunin.stackoverflowparser.entity.Question;
import ru.yakunin.stackoverflowparser.repository.QuestionRepository;
import ru.yakunin.stackoverflowparser.service.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question getById(int id) {
        return questionRepository.findById(id).orElse(null);
    }
}
