package edu.generation.jfs_jwt_demo.controller;

import edu.generation.jfs_jwt_demo.model.Course;
import edu.generation.jfs_jwt_demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CourseController
{
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @CrossOrigin("*")
    @GetMapping("/api/courses")
    public List<Course> all() {
        System.out.println("Get all courses.");
        List<Course> courses = courseService.all();
        courses.forEach(System.out::println);
        return courses;
    }

    @PostMapping("/api/course/insert")
    public Course save(@RequestBody Course course) throws ResponseStatusException {
        System.out.println("Adding " + course);
        if ( courseService.courseIdExists(course.getCourseId()) )
            throw new ResponseStatusException(HttpStatus.CONFLICT, course.getCourseId());
        else
            return courseService.save(course);
    }

    @PostMapping("api/course/delete")
    public void delete(@RequestBody String courseId) {
        System.out.println("Deleting " + courseId);
        courseService.delete(courseId);
    }
}
