package com.SpringMongo.SpringMongo.controller;

import com.SpringMongo.SpringMongo.model.Task;
import com.SpringMongo.SpringMongo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task") // defining root url
public class TaskController {
    // now calling each and every method from service as we are going to use all services from controller
    // for calling all methods from service we inject it into controller
    // to inject use annotation @Autowired
    @Autowired
    private TaskService service;

    // defining the end point
    // the method is to create the task using addTask() from services
    //since here we are going to give the create request we use PostMapping annotation
    @PostMapping
    //The @ResponseStatus(HttpStatus.CREATED) annotation ensures that the response will have the HTTP status code 201 Created, which signals that the task was successfully created.
    @ResponseStatus(HttpStatus.CREATED)
    // The (@RequestBody Task task):- tells Spring to map the incoming JSON request body to the Task object.
    public Task createTask(@RequestBody Task task){
        return service.addTask(task);
    }


    @GetMapping
    // now designing method for findTask()
    public List<Task> getTasks(){
        return service.findAllTask();
    }

    @GetMapping("/{taskId}") //as we want to append the passed argument as part of URL, so we pass it inside the GetMapping annotation to append it as a part of URL
    // now for getTaskByTaskId() method
    // @PathVariable annotation :- as we want to pass this parameter as a part of request URL, so we use this annotation
    public Task findTaskByTaskId(@PathVariable String taskId){
        return service.getTaskByTaskId(taskId);
    }

    @GetMapping("/severity/{severity}") //as we want to append the passed argument as part of URL, so we pass it inside the GetMapping annotation to append it as a part of URL
    public List<Task> findTaskBySeverity(@PathVariable int severity){
        return service.getTaskBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}/storypoint/{storypoint}") // Mapping to pass both assignee and storypoint in the URL
    public List<Task>findTaskByAssigneeAndStoryPoint(@PathVariable String assignee,@PathVariable int storypoint){
        return service.getTaskByAssigneeAndStoryPoint(assignee,storypoint);
    }

    @GetMapping("/assignee/{assignee}")
    // as we want to pass assignee as part of request url to specify that we use @pathVariable as annotation (same for all wherever we used PathVariable as annotation)
    public List<Task> findTaskBySeverity(@PathVariable String assignee){
        return service.getTaskByAssignee(assignee);
    }

    // now for update methods
    // as we are updating/modifying the data, so we use @PutMapping annotation
    @PutMapping
    // as we are passing object as parameter, so we use @RequestBody annotation
    public Task modifyTask(@RequestBody Task task){
        return service.updateTask(task);
    }

    // now for delete
    // here annotation act as HTTP method
    @DeleteMapping("/{taskId}") // since we want to delete the resource we have to use HTTP method for that we use specific DeleteMapping annotation
    public String deleteTask(@PathVariable String taskId){
        return service.deleteTask(taskId);
    }
}
