package ru.yakunin.stackoverflowparser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.yakunin.stackoverflowparser.entity.Detail;
import ru.yakunin.stackoverflowparser.entity.Question;
import ru.yakunin.stackoverflowparser.service.QuestionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// We no longer need this class. We have already written the values to the database.

@Component
public class StackOverflowParser {
    private Logger log = LoggerFactory.getLogger(StackOverflowParser.class);
    @Value("${stack.overflow.link}")
    private String stackOverflowLink;
    private final QuestionService questionService;

    public StackOverflowParser(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Question> getQuestionFromMainPageStackOverflow() {
        List<Question> questionList = new ArrayList<>();
        try {
            Document mainStackOverflowPage = Jsoup.connect(stackOverflowLink).get();
            log.info("Connect to {}", stackOverflowLink);
            Elements nameAndLinkElements = mainStackOverflowPage
                    .getElementsByAttributeValue("class", "question-hyperlink");
            for (Element el : nameAndLinkElements) {
                String author;
                String dateOfCreated;
                int answers = 0;
                String linkToShowThisQuestionDetails = stackOverflowLink +
                        el.attr("href");
                Question question = new Question();
                question.setName(el.text());
                Document pageToShowQuestionDetails = Jsoup
                        .connect(linkToShowThisQuestionDetails).get();
                Elements answersElements = pageToShowQuestionDetails
                                .getElementsByAttributeValue("class",
                                        "js-vote-count grid--cell fc-black-500" +
                                                " fs-title grid fd-column ai-center");
                Element answerElement = answersElements.get(0);
                try {
                    answers = Integer.parseInt(answerElement.attr("data-value"));
                } catch (Exception e) {
                    log.error("Exception to cast answers to int: {}", e.getMessage());
                }
                Elements dateOfCreateElements = pageToShowQuestionDetails
                        .getElementsByAttributeValue("itemprop", "dateCreated");
                Element dateOfCreatedElement = dateOfCreateElements.get(0);
                dateOfCreated = dateOfCreatedElement.attr("datetime");

                Elements authorElements = pageToShowQuestionDetails
                        .getElementsByAttributeValue("class", "d-none");
                Element authorElement = authorElements.get(0);
                author = authorElement.text();
                Detail detail = new Detail(author, dateOfCreated, answers);
                question.setDetail(detail);
                questionList.add(question);
                log.info("Add new question with" +
                        " name: {}, link: {}, author: {}, date of created: {}, answers: {}",
                        question.getName(), linkToShowThisQuestionDetails, detail.getAuthor(),
                        detail.getDateOfCreated(), detail.getAnswers());

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return questionList;
    }

    public void saveAllQuestions() {
        List<Question> questionList = getQuestionFromMainPageStackOverflow();
        questionList.forEach(question -> questionService.save(question));
    }
}
