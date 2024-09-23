package com.SpringMongo.SpringMongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// we treat Task class as document as we are stroing its data in mongodb for that we add annotation Document
@Document(collection = "task") // by-default collection name is our class name
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id // this defines the taskId as primary key
    private String taskId;
    private String description;
    private int severity;
    private String assignee;
    private int storypoint;
}
