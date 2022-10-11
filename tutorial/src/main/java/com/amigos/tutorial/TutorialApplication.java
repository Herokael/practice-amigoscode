package com.amigos.tutorial;


import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import com.github.javafaker.Faker;

@SpringBootApplication
public class TutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository){

		return args -> {

			Student student = new Student("Brian", "Perez", "brian@gmail.com", 28);

			Book book1 = new Book("Clean Code", LocalDateTime.now());
			Book book2 = new Book("Data Base Fundamentals", LocalDateTime.now());
			Book book3 = new Book("Spring Boot", LocalDateTime.now());

			student.addBook(book1);
			student.addBook(book2);
			student.addBook(book3);

			Course course1 = new Course("Data Base 1", "IT");
			Course course2 = new Course("Programming Languages", "IT");

			// student.enroleToCourse(course1);
			// student.enroleToCourse(course2);

			student.addEnrolment(new Enrolment(
				new EnrolmentId(1L, 1L),
				student,
				course1,
				LocalDateTime.now()
			));

			student.addEnrolment(new Enrolment(
				new EnrolmentId(1L, 2L),
				student,
				course2,
				LocalDateTime.now().minusDays(18)
			));

			studentRepository.save(student);

			// ----------------- Probando Join - OneToOne -----------------
			// Faker faker = new Faker();
			// String firstName = faker.name().firstName();
			// String lastName = faker.name().lastName();
			// String email = String.format("%s.%s@gmail.com", firstName, lastName);
			// Integer age = faker.number().numberBetween(17, 55);
			// Student student = new Student(
			// 	firstName,
			// 	lastName,
			// 	email,
			// 	age
			// );

			// StudentIdCard studentIdCard = new StudentIdCard("123456789", student);

			// studentIdCardRepository.save(studentIdCard);

			// ----------------- Paginación y Sort ---------------------

				// createStudents(studentRepository);

				// PageRequest pageRequest = PageRequest.of(
				// 	0,
				// 	5,
				// 	Sort.by("firstName").ascending()
				// );

				// Page<Student> page = studentRepository.findAll(pageRequest);

				// System.out.println(page);

		};
	}

	public void sortStudentsByName(StudentRepository studentRepository){
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
		studentRepository.findAll(sort).forEach(student -> System.out.println(student.getFirstName()));
	}

	public void createStudents(StudentRepository studentRepository){
		Faker faker = new Faker();
		for(int i=0; i < 20; i++){
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Integer age = faker.number().numberBetween(17, 55);
			Student student = new Student(
				firstName,
				lastName,
				email,
				age
			);
			studentRepository.save(student);
		};
	}

}
