package com.example.WebApplication.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("Email is taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        boolean studentExist = studentRepository.existsById(id);

        if (!studentExist){
            throw new IllegalStateException(
                    "student with id " + id + " does not exist"
            );
        }else {
            studentRepository.deleteById(id);
        }

    }

    @Transactional
    public void updateStudent(Long id, Student student) {
        Student studentFound = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student does not exist"));

        if (student.getName() != null && student.getName().length() > 0){
            studentFound.setName(student.getName());
        }

        if (student.getEmail() != null && student.getEmail().length() > 0 && student.getEmail().contains("@")){
            Optional<Student> studentExists = studentRepository.findStudentByEmail(student.getEmail());
            if (studentExists.isPresent()){
                throw new IllegalStateException("email taken");
            }
            studentFound.setEmail(student.getEmail());

        }

    }
}
