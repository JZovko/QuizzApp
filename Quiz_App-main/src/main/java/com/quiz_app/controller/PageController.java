package com.quiz_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.quiz_app.model.Quiz;
import com.quiz_app.service.QuizService;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public String showQuizzes(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes(); // Osiguraj da metoda postoji u QuizService
        model.addAttribute("quizzes", quizzes);
        return "quizzes"; // Thymeleaf predložak za prikaz kvizova
    }

    @GetMapping("/questions")
    public String showQuestions(Model model) {
        // model.addAttribute("questions", questionService.getAllQuestions());
        return "questions";
    }

    @GetMapping("/add-question")
    public String addQuestion() {
        return "add-question";
    }

}