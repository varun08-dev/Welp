package org.varun.welp.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.varun.welp.Models.Questions;
import org.varun.welp.Repositories.QuestionRepository;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/welp/v1/questions")
public class QuestionController {

    private QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostMapping
    public ResponseEntity<Questions> CreateQues(@RequestBody Questions q){
        Questions savedQues = questionRepository.save(q);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQues);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Questions>> searchQuestions(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String tag) {

        List<Questions> result;
        if (text !=null && tag!= null){
            List<Questions> textResult = questionRepository.findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(text,text);

            List<Questions> tagResult = questionRepository.findByTopicTagsContaining(tag);

            result = textResult.stream()
                    .filter(tagResult::contains)
                    .toList();
        } else if (text!=null) {
            result= questionRepository.findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(text,text);
        } else if (tag!= null) {
            result = questionRepository.findByTopicTagsContaining(tag);
        }else result= questionRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Questions> updateQuestion(@PathVariable UUID id,
                                                    @RequestBody Questions updatedQuestion){
        return questionRepository.findById(id).map(
                questions -> {

                    if (updatedQuestion.getTitle()!= null){
                        questions.setTitle(updatedQuestion.getTitle());
                    }
                    if (updatedQuestion.getBody()!=null){
                        questions.setBody(updatedQuestion.getBody());
                    }
                    if (updatedQuestion.getTopicTags()!=null){
                        questions.setTopicTags(updatedQuestion.getTopicTags());
                    }

                     Questions saved=questionRepository.save(questions);
                    return ResponseEntity.ok(saved);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
