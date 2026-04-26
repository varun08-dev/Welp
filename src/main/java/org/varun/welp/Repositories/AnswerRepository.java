package org.varun.welp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.varun.welp.Models.Answers;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, UUID> {

    List<Answers> findByQuestionId(UUID questionId);
}
