package com.amigos.tutorial;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    
    // Clave compuesta conformada por studentId y courseId
    @EmbeddedId
    private EnrolmentId enrolmentId;

    @Column(
        name = "created_at",
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(
        name = "student_id",
        foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
        )
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
        name = "course_id",
        foreignKey = @ForeignKey(name = "enrolment_course_id_fk"))
    private Course course;

    public Enrolment(){}

    public Enrolment(EnrolmentId enrolmentId, Student student, Course course, LocalDateTime createdAt){
        this.enrolmentId = enrolmentId;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

}
