package com.SpringMongo.SpringMongo.repository;

import com.SpringMongo.SpringMongo.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
/*
 * The interface MongoRepository takes the model class and primary key datatype as argument
 */
public interface TaskRepository extends MongoRepository<Task,String> {

    // specifying the methods from service to SpringBoot when the methods are not present in repo interface
    // creating the method in repo from service class
    // write the signature of method and SpringBoot understands what exactly does user want
    List<Task> findBySeverity(int severity); // SpringBoot understands that user wants to fetch the task based on severity
    List<Task> findByAssigneeAndStorypoint(String assignee,int storypoint); // SpringBoot understands that user wants to fetch the task based on severity

    // specifying the query method for fetching the task based on assignee
    // here for specifying the query in repo we can write any method name there isn't any specific syntax for it
    // we use Annotation "Query" to specify that the below method is user defined query to fetch data
    // Query takes the field as argument and the 0 after questionmark is index parameter being specified
    // we can add more than one field in Query as:- Query("{fieldName1 : ?0 , fieldName2 : ?1 }")
    @Query("{assignee: ?0}") //Annotation
    List<Task> getTaskByAssignee(String assignee); // it is Interface abstract method and Interface abstract method cannot have body, so its body is in Service class
}
