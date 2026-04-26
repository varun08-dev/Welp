package org.varun.welp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.varun.welp.Models.Users;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
}
