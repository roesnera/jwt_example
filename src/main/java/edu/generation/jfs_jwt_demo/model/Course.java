package edu.generation.jfs_jwt_demo.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public final class Course {

    @Id
    private final String id;
    private final String name;
    private final String objectives;

    public Course(String id, String name, String objectives) {
        this.id = id;
        this.name = name;
        this.objectives = objectives;
    }

    public Course(String name, String objectives) {
        this(null, name, objectives);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String objectives() {
        return objectives;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Course) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.objectives, that.objectives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, objectives);
    }

    @Override
    public String toString() {
        return "Course[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "objectives=" + objectives + ']';
    }

}
