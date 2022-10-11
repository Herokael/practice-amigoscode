package com.amigos.tutorial;

import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class EnrolmentId implements Serializable{ // Clase utilizada para instanciar objetos de clave compuesta

    private Long studentId;

    private Long courseId;

    public EnrolmentId(){}

    public EnrolmentId(Long studentId, Long courseId){
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        EnrolmentId that = (EnrolmentId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(studentId, courseId);
    }
    
}
