package com.Ibrahim.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    //which service we want to test
    @InjectMocks
    private StudentService studentService;

    //declare the dependencies
    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {

        //Given
        StudentDto dto = new StudentDto("John", "Doe", "johndoe@gmail.com", 1);

        Student student = new Student("John", "Doe", "johndoe@gmail.com", 25);

        Student savedStudent = new Student("John", "Doe", "johndoe@gmail.com", 25);
        savedStudent.setId(1);

        //Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        
        when(repository.save(student)).thenReturn(savedStudent);

        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto("John", "Doe", "johndoe@gmail.com"));

        //When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        //Then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);

    }

    @Test
    public void should_return_all_students() {

        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "johndoe@gmail.com", 25));

        //Mock the calls
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Doe", "johndoe@gmail.com"));

        //when
        List<StudentResponseDto> responseDto = studentService.findAllStudent();

        //Then
        assertEquals(students.size(), responseDto.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {

        //Given
        Integer studentId = 1;
        Student student = new Student("John", "Doe", "johndoe@gmail.com", 25);

        //Mock the calls
        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Doe", "johndoe@gmail.com"));

        //when
        StudentResponseDto dto = studentService.findStudentById(studentId);

        //Then
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());

        verify(repository, times(1)).findById(studentId);
    }

    @Test
    public void should_return_student_by_name() {
        //Given
        String studentName = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "johndoe@gmail.com", 25));

        //Mock the calls
        when(repository.findAllByFirstnameContaining(studentName)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Doe", "johndoe@gmail.com"));

        //when
        var responseDto = studentService.findStudentByName(studentName);

        //Then
        assertEquals(students.size(), responseDto.size());

        verify(repository, times(1)).findAllByFirstnameContaining(studentName);
    }


}