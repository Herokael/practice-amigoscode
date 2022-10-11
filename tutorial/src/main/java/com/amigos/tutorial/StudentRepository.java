package com.amigos.tutorial;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT  s FROM Student s WHERE s.firstName = ?1 AND s.age = ?2")
    List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

    @Query(
        value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age", 
        nativeQuery = true)
    List<Student> findStudentByFirstNameEqualsAndAgeIsGreaterThanOrEqualNative(
        @Param("firstName") String firstName, 
        @Param("age") Integer age
    );

    @Transactional
    @Modifying
    @Query("DELETE Student s WHERE s.id = ?1")
    int deleteStudentById(Long id);

}
