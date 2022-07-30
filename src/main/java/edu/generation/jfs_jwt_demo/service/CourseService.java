package edu.generation.jfs_jwt_demo.service;

import edu.generation.jfs_jwt_demo.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> all();

    Course save(Course course);

    void delete(String courseId);

    boolean courseIdExists(String courseId);
}
