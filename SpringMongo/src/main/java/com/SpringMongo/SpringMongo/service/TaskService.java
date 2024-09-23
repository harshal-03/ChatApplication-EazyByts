package com.SpringMongo.SpringMongo.service;

import com.SpringMongo.SpringMongo.model.Task;
import com.SpringMongo.SpringMongo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    // Injecting repositroy inside this service
    @Autowired
    private TaskRepository repo;

    // CRUD

    // to save some task into MongoDB altas we create method below (basically for create opertaion)
    public Task addTask(Task task){
        // we are creating a random UUID as taskId as we haven't assigned any ID initially
        // we have to initialize the primary key with some data initially but as we haven't, so we create a random UUID
        task.setTaskId(UUID.randomUUID().toString().split( "-")[0]);
        // save() takes entity as argument which in NoSQL means object, so we pass task object as argument
        return repo.save(task);
    }

    // read operation :- to read document from mongoDB atlas
    // below method returns the list of all tasks from mongoDB atlas
    public List<Task> findAllTask(){
        // findAll() :- this method returns all the task object from mongoDB atlas
        return repo.findAll();
    }
    // to search any specific task document from mongodb using any field(we defined inside Task class in model package)
    public Task getTaskByTaskId(String taskId){
        // findById() :- it is a method that find the specific object of task using its ID()
        // we use .get() at end to fetch that specific required task object so that we can return it
        return repo.findById(taskId).get();
    }

    // to search task object by severity
    public List<Task> getTaskBySeverity(int severity){
        // Writing the method that later convert into query
        // for that
        // use repo interface object to intercept with database then use findBy method with the correct name of field successing it
        // in this case we use repo.findBySeverity() where severity the exact field name we are using to assign the severity to task
        // NOTE:- always right the exact duplicate field name from model package class in findBy method in camel casing
        return repo.findBySeverity(severity);
        // this method will give error if it is not present in our TaskRepo interface
    }

    // creating a method that uses two fields to find the task
    public List<Task> getTaskByAssigneeAndStoryPoint(String assignee, int storypoint){
        return repo.findByAssigneeAndStorypoint(assignee,storypoint);
        // this method will give error if it is not present in our TaskRepo interface
    }

    // writing the query to fetch the task
    public List<Task> getTaskByAssignee(String assignee){
        return repo.getTaskByAssignee(assignee);
    }

    // Update method
    // below method takes the Task field that you want to update as argument and the method's returnType is Task which means it will return the updated Task Object
    public Task updateTask(Task taskRequest){
        // fetching the existing document from MongoDB atlas
        Task existingTask = repo.findById(taskRequest.getTaskId()).get();

        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStorypoint(taskRequest.getStorypoint());

        return repo.save(existingTask);
    }

    // DELETE operation

    public String deleteTask(String taskId){
        repo.deleteById(taskId);
        return "Task deleted from dashboard";
    }
}
