package com.example.myproject.Repostiory;

import com.example.myproject.Model.WorkflowRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRunRepository extends JpaRepository<WorkflowRun, Long> {
}
