package com.amigos.tutorial;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "StudentIdCard")
@Table(
    name = "student_id_card",
    uniqueConstraints = {@UniqueConstraint( name = "student_id_card_number_unique", columnNames = "card_number") }
)
public class StudentIdCard {
    @Id
    @SequenceGenerator(
        name = "student_card_id_sequence",
        sequenceName = "student_card_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_card_id_sequence"
    )
    @Column(
        name = "id",
        insertable = false,
        updatable = false
    )
    private Long id;

    @Column(
        name = "card_number",
        nullable = false,
        columnDefinition = "TEXT",
        length = 15
    )
    private String cardNumber;

    // --------------- Foreign Key -------------
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
        name = "student_id",
        referencedColumnName = "id",
        foreignKey = @ForeignKey( 
            name = "student_id_fk_student_id_card"
            )
    )
    private Student student;


    // --------------- Constructors --------------
    public StudentIdCard(String cardNumber, Student student){
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard(){}
}
