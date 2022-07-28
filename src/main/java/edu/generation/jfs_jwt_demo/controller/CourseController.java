package edu.generation.jfs_jwt_demo.controller;

import edu.generation.jfs_jwt_demo.model.Course;
import edu.generation.jfs_jwt_demo.service.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController
{
    private final CourseServiceImpl courseServiceImpl;

    public CourseController(CourseServiceImpl courseServiceImpl) {
        this.courseServiceImpl = courseServiceImpl;
    }

    @CrossOrigin("*")
    @GetMapping("/api/courses")
    public List<Course> all() {
        System.out.println("Get all courses.");
        List<Course> courses = courseServiceImpl.all();
        courses.forEach(System.out::println);
        return courses;
    }

    @PostMapping("/api/courses")
    public void save(@RequestBody Course course) {
        courseServiceImpl.save(course);
    }

    @PostMapping("api/courses/{id}")
    public void delete(@PathVariable("id") long id) {
        courseServiceImpl.delete(id);
    }
}
