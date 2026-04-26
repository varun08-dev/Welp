package org.varun.welp.Controllers;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.varun.welp.Models.Answers;
import org.varun.welp.Models.Questions;
import org.varun.welp.Repositories.AnswerRepository;
import org.varun.welp.Repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public AnswerController(AnswerRepository answerRepository,QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @PostMapping
    public ResponseEntity<Answers> createAnswer(@PathVariable("questionId") UUID id, @RequestBody Answers answer){
        Optional<Questions> q = questionRepository.findById(id);
        if (q.isPresent()){
            answer.setQuestion(q.get());
            Answers savedAns = answerRepository.save(answer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAns);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<Answers> updateAnswer(@PathVariable ("answerId") UUID id, @RequestBody Answers updatedAnswer){

        return answerRepository.findById(id).map(
                answers -> {
                    if (updatedAnswer.getAnswer()!=null){
                        answers.setAnswer(updatedAnswer.getAnswer());
                    }
                    Answers saved = answerRepository.save(answers);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Answers>> getAnswer(@PathVariable("questionId") UUID id){
        List<Answers> answersList= answerRepository.findByQuestionId(id);
        System.out.println("All answer has been fetched");
        return ResponseEntity.ok(answersList);
    }

}
