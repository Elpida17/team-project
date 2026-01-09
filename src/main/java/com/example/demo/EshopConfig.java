package com.example.demo;


import org.springframework.boot.*;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Configuration
public class EshopConfig implements CommandLineRunner {
	
	@Autowired
	CitizenRepository citizenRepo;
	
	@Autowired
	ProductRepository productRepo;

	@Autowired
	StoreRepository storeRepo;
	
	
	/*@Override
	public void run(String... args) throws Exception {
		Professor p1 = new Professor("Apostolis", "Associate Prof");
		hs.addProfessor(p1);
		Professor p2 = new Professor("Daniel", "Assistant Prof");
		hs.addProfessor(p2);
		Professor p3 = new Professor("Alex", "Prof");
		hs.addProfessor(p3);
		
		Course c3 = new Course("OOP", 3);
		c3.setProfessor(p3);
		Course c4 = new Course("SE", 4);
		c4.setProfessor(p3);
		Course c1 = new Course("SQA", 5);
		c1.setProfessor(p1);
		Course c2 = new Course("Mobile", 6);
		c2.setProfessor(p1);
		Course c5 = new Course("Patterns", 7);
		c5.setProfessor(p2);

		
		Student s1 = new Student("Nikos",28,"Thessaloniki");
		s1.addCourse(c2);
		s1.addCourse(c3);
		hs.addStudent(s1);
		
		Student s2 = new Student("Elvira",35,"Thessaloniki");
		s2.addCourse(c1);
		s2.addCourse(c4);
		hs.addStudent(s2);
		
		Student s3 = new Student("Sofia",36,"Groningen");
		s3.addCourse(c5);
		hs.addStudent(s3);*/
	}

}

