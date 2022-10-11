package com.amigos.tutorial;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Book")
@Table(name = "book")
public class Book {
    
    @Id
    @SequenceGenerator(
        name = "book_id_sequence",
        sequenceName = "book_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_id_sequence"
    )
    @Column(
        name = "id",
        nullable = false
    )
    private Long id;

    @Column(
        name = "book_name",
        columnDefinition = "TEXT",
        nullable = false
    )
    private String name;

    //timestamp

    @Column(
        name = "created_at",
        nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_book_fk"
            )
    )
    private Student student;

    public Book(){}

    public Book(String name, LocalDateTime createdAt){
        this.name = name;
        this.createdAt = createdAt;
    }
}
