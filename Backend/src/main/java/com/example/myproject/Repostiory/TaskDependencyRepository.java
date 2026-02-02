package com.example.myproject.Repostiory;

import com.example.myproject.Model.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDependencyRepository
        extends JpaRepository<TaskDependency, Long> {

    List<TaskDependency> findByWorkflowId(Long workflowId);
}
