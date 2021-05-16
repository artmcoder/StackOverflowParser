package ru.yakunin.stackoverflowparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yakunin.stackoverflowparser.entity.Question;
import ru.yakunin.stackoverflowparser.parser.StackOverflowParser;
import ru.yakunin.stackoverflowparser.service.QuestionService;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String saveAllQuestions(Model model) {
        List<Question> questionList = questionService.getAll();
        model.addAttribute("questions", questionList);
        return "questions";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Integer id,
                          Model model) {
        model.addAttribute("question", questionService.getById(id));
        return "detail";
    }
}

