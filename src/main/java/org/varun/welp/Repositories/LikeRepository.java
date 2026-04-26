package org.varun.welp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.varun.welp.Models.Likes;

import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Likes, UUID> {
}
