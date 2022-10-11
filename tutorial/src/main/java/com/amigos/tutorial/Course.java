package com.amigos.tutorial;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Course")
@Table(name = "course")
public class Course {
    
    @Id
    @SequenceGenerator(
        name = "course_id_generator",
        sequenceName = "course_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_id_generator"
    )
    private Long id;

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @Column(
        name = "department",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String department;

    @OneToMany(
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        mappedBy = "course"
        )
    private List<Enrolment> enrolments = new ArrayList<>();

    public Course(){}

    public Course(String name, String department){
        this.name = name;
        this.department = department;
    }

    public void addEnrolment(Enrolment enrolment){
        if(!getEnrolments().contains(enrolment)){
            getEnrolments().add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment){
        getEnrolments().remove(enrolment);
    }
}
