package edu.generation.jfs_jwt_demo.service;

import edu.generation.jfs_jwt_demo.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    private final Map<String, Course> courseList = new HashMap<>();

    public CourseServiceImpl() {
        courseList.put("INTRO-CS-1", new Course("INTRO-CS-1", "Introduction to Computer Science",
                """
                        -Explain the basics about how the internet works
                        -Explain the difference between a client and a server
                        -Explain the TCP/IP protocol on a basic level
                        -Explain the HTTP protocol
                        -Explain and use the HTTP methods GET / POST / PUT / DELETE
                        -Use HTTP methods GET and POST with an HTTP Client (Postman)
                        -Use developer tools
                        -Describe the difference between a Website and a Web Application
                        -Give examples of Web Applications and Web Sites"""));
        courseList.put("INTRO-CS-2", new Course("INTRO-CS-2", "Introduction to Algorithms",
                """
                        -Explain what an algorithm is\s
                        -Explain the structure of an algorithm
                        -Explain what code comments are and how to write them in JavaScript
                        -Describe what reserved words in JavaScript are and give examples
                        -Use primitive types to create variables and algorithms
                        -Declare and use variables of different types
                        -Write simple and correct programs using JavaScript
                        -Use the Javascript prompt function to capture a user input"""));
        courseList.put("INTRO-CS-3", new Course("INTRO-CS-3", "Algorithm Design and Problem Solving - Introduction",
                """
                        -Declare and use conditionals
                        -Define functions using JavaScript
                        -Write algorithms that solve mathematical problems
                        -Manipulate Strings with JavaScript
                        -Write algorithms that solve problems using String Functions
                        -Use the Web debugger for JavaScript code on the Browser
                        -Write algorithms that solve problems taking user inputs"""));
        courseList.put("INTRO-CS-4", new Course("INTRO-CS-4", "Algorithm Design and Problem Solving - Advanced",
                """
                        -Define and use arrays for numeric values
                        -Define and use arrays for String values
                        -Write algorithms that solve mathematical problems using arrays
                        -Read, understand and fix code written by someone else
                        -Write algorithms that solve problems using logical expressions
                        -Explain and do code refactoring to improve code
                        -Iterate arrays and modify its data
                        -Write algorithms that solve basic sorting algorithms using arrays"""));
    }

    @Override
    public List<Course> all() {
        System.out.println("Courses follow:");
        courseList.values().forEach(System.out::println);
        return new ArrayList<>(courseList.values());
    }

    @Override
    public void save(Course course) {
        courseList.put(course.id(), course);
    }

    @Override
    public void delete(String id) {
        courseList.remove(id);
    }
}
