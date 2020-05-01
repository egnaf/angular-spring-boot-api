package com.github.egnaf.app.services.impls;

import com.github.egnaf.app.exceptions.StudentNotFoundException;
import com.github.egnaf.app.models.Student;
import com.github.egnaf.app.repositories.StudentRepository;
import com.github.egnaf.app.services.StudentService;
import com.github.egnaf.app.utils.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveOrUpdate(Student student) {
        Student savedStudent;
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            savedStudent = studentRepository.findById(student.getId()).get();
            savedStudent.setFirstName(student.getFirstName());
            savedStudent.setLastName(student.getLastName());
            savedStudent.setMajor(student.getMajor());
            savedStudent.setBirthday(student.getBirthday());
            log.info("Update: {}", savedStudent);
        } else {
            savedStudent = student;
            savedStudent.setId(StringHelper.generateId());
            log.info("Save: {}", savedStudent);
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        log.info("findAll");
        return studentRepository.findAll();
    }

    @Override
    public Student findById(String id) {
        log.info("findById: {}", id);
        return studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student by id not found"));
    }

    @Override
    public void deleteById(String id) {
        log.info("deleteById: {}", id);
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException("Student by id not found");
        }
    }
}
