package com.example.myproject.Service;

import com.example.myproject.Model.Task;
import com.example.myproject.Model.TaskDependency;
import com.example.myproject.Model.Workflow;
import com.example.myproject.Repostiory.TaskDependencyRepository;
import com.example.myproject.Repostiory.TaskRepository;
import com.example.myproject.Repostiory.WorkflowRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskDependencyService {

    private final TaskDependencyRepository dependencyRepo;
    private final TaskRepository taskRepo;
    private final WorkflowRepository workflowRepo;

    public TaskDependencyService(TaskDependencyRepository dependencyRepo,
                                 TaskRepository taskRepo, WorkflowRepository workflowRepo) {
        this.dependencyRepo = dependencyRepo;
        this.taskRepo = taskRepo;
        this.workflowRepo = workflowRepo;
    }

    public TaskDependency addDependency(Long sourceTaskId, Long targetTaskId, Long workflowId) {

        Workflow workflow = workflowRepo.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));

        TaskDependency dependency=new TaskDependency();
        dependency.setWorkflow(workflow);
        Task source = taskRepo.findById(sourceTaskId)
                .orElseThrow(() -> new RuntimeException("Source task not found"));
        dependency.setSourceTask(source);

        Task target = taskRepo.findById(targetTaskId)
                .orElseThrow(() -> new RuntimeException("Target task not found"));
        dependency.setTargetTask(target);

//        TaskDependency dep = new TaskDependency();
//        dep.setWorkflow(source.getWorkflow());
//        dep.setSourceTask(source);
//        dep.setTargetTask(target);

        return dependencyRepo.save(dependency);
    }

    public List<TaskDependency> getAllDependency(Long workflowId){
        return dependencyRepo.findByWorkflowId(workflowId);
    }
}