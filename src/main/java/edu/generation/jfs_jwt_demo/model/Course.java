package edu.generation.jfs_jwt_demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public final class Course {

    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String courseId;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String objectives;

    public Course() {}

    public Course(String courseId, String name, String objectives) {
        this.courseId = courseId;
        this.name = name;
        this.objectives = objectives;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Course) obj;
        return Objects.equals(this.courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", objectives=\n" + objectives + "}";
    }
}
