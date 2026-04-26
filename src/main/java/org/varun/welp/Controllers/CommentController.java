package org.varun.welp.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.varun.welp.Models.Answers;
import org.varun.welp.Models.Comments;
import org.varun.welp.Repositories.AnswerRepository;
import org.varun.welp.Repositories.CommentRepository;

import java.util.UUID;

@RestController
@RequestMapping
public class CommentController {

    private final AnswerRepository answersRepository;
    private final CommentRepository commentRepository;


    public CommentController(AnswerRepository answersRepository, CommentRepository commentRepository) {
        this.answersRepository = answersRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/answers/{answerId}/comments")
    public ResponseEntity<Comments> addCommentOnAnswer(@PathVariable("answerId")UUID id, @RequestBody Comments comments){
        Answers answers= answersRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Answer not found"));

        comments.setAnswers(answers);
        comments.setParentComment(null);

        Comments saved= commentRepository.save(comments);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }

    @PostMapping("/comments/{commentId}/comments")
    public ResponseEntity<Comments> addCommentOnComment(@PathVariable("commentId") UUID id, @RequestBody Comments comments){

        Comments parent = commentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Comment not found"));

        comments.setParentComment(parent);

        Comments saved = commentRepository.save(comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
