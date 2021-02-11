package com.challenge.simpleApi.domains.users.repositories;

import com.challenge.simpleApi.domains.users.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
  Optional<Users> findByusername(String username);
}
