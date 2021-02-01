package com.challenge.simpleApi.domains.tasks.repositories;

import com.challenge.simpleApi.domains.tasks.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TasksRepositories  extends JpaRepository<Tasks,Long> {
  public List<Tasks> findByUserId(Long Id);
}
