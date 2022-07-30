package edu.generation.jfs_jwt_demo.repositories;

import edu.generation.jfs_jwt_demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseId(String courseId);

    boolean existsByCourseId( String courseId );
}
