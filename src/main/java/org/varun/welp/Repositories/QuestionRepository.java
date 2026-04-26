package org.varun.welp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.varun.welp.Models.Questions;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, UUID> {

    List<Questions> findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(String title, String body);

    List<Questions> findByTopicTagsContaining(String tag);

}
