package org.varun.welp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.varun.welp.Models.Comments;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comments, UUID> {
}
