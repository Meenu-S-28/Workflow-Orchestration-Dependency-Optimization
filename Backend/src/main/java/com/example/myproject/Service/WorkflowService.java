package com.example.myproject.Service;

import com.example.myproject.Model.Workflow;
import com.example.myproject.Repostiory.WorkflowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkflowService {

    private final WorkflowRepository workflowRepo;

    public WorkflowService(WorkflowRepository workflowRepo) {
        this.workflowRepo = workflowRepo;
    }

    public Workflow createWorkflow(Workflow workflow) {
        workflow.setCreatedAt(LocalDate.now());
        workflow.setStatus(Workflow.Status.DRAFT);
        return workflowRepo.save(workflow);
    }

    public List<Workflow> getAllWorkflows() {
        return workflowRepo.findAll();
    }
}