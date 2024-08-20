package com.quiz_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.quiz_app.model.Question;
import com.quiz_app.model.QuestionWrapper;
import com.quiz_app.model.Response;
import com.quiz_app.service.QuizService;
import org.springframework.stereotype.Controller;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.List;
// controller for quiz
@Controller
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public String createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        quizService.createQuiz(category, numQ, title);
        return "redirect:/quizzes"; // Redirect to a list of quizzes or some other page
    }

    @GetMapping("questions/{id}")
    public String getQuizQuestions(@PathVariable Integer id, Model model) {
        ResponseEntity<List<QuestionWrapper>> response = quizService.getQuizQuestions(id);
        List<QuestionWrapper> questions = response.getBody();
        model.addAttribute("quizId", id);
        model.addAttribute("questions", questions);
        return "questions"; // Thymeleaf template name
    }

    @PostMapping("submit/{id}")
    public String submitQuiz(@PathVariable Integer id, @RequestParam Map<String, String> responses, Model model) {
        List<Response> responseList = responses.entrySet().stream()
                .map(entry -> new Response(Integer.parseInt(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());

        ResponseEntity<Integer> result = quizService.calculateResult(id, responseList);
        model.addAttribute("result", result.getBody());
        return "result"; // Thymeleaf template name
    }
}