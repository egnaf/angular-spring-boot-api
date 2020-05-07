package com.github.egnaf.app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Student {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String major;
    private long birthday;
}
