package com.amigos.tutorial;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "Student")
@Table(
    name = "student",
    uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique", columnNames = "email")
    }
)
public class Student {

    @Id
    @SequenceGenerator(
        name = "sequence_generator",
        sequenceName = "sequence_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "sequence_generator"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
        name = "first_name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
        name = "last_name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
        name = "email",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String email;

    @Column(
        name = "age",
        nullable = false
    )
    private Integer age;

    @OneToOne( 
        mappedBy = "student", 
        orphanRemoval = true,
        cascade = CascadeType.PERSIST
        )
    private StudentIdCard studentIdCard;

    @OneToMany(
        mappedBy = "student",
        orphanRemoval = true,
        cascade = CascadeType.ALL
    )
    private final List<Book> books = new ArrayList<>();

    @OneToMany(
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        mappedBy = "student"
    )
    private List<Enrolment> enrolments = new ArrayList<>();

    public Student(String firstName, String lastName, String email, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student(){}

    public void addBook(Book book){
        if(!getBooks().contains(book)){
            getBooks().add(book);
            book.setStudent(this);
        }
    }

    public void removeBooks(Book book){
        if(getBooks().contains(book)){
            getBooks().remove(book);
            book.setStudent(null);
        }
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
