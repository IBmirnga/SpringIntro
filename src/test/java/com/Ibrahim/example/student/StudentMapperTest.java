package com.Ibrahim.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("John", "Doe", "johndoe@gmail.com", 1);

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
        //Student student = mapper.toStudent(null);

       var message = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student dto should not be null", message.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        //Given
        Student student = new Student("John", "Smith", "johnsmith@gmail.com", 25);

        //When
        StudentResponseDto response = mapper.toStudentResponseDto(student);

        //Then
        assertEquals(response.firstname(), student.getFirstname());
        assertEquals(response.lastname(), student.getLastname());
        assertEquals(response.email(), student.getEmail());

    }
}


//@BeforeAll
//    static void beforeAll() {
//        System.out.println("Inside The before all method");
//    }
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("Inside The before each method");
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("Inside The after each method");
//    }
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("Inside The after all method");
//    }
//
//    @Test
//    public void testMethod1() {
//        System.out.println("My first test method");
//    }
//
//    @Test
//    public void testMethod2() {
//        System.out.println("My second test method");
//    }