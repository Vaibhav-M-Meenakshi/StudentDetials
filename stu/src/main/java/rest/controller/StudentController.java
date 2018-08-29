package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.model.Student;
import rest.service.StudentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        System.out.println(String.format("Get Students called - %s", new Date()));
        return studentService.getStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody ArrayList<Student> students) {
        System.out.println(String.format("Save Student called - %s", new Date()));
        studentService.saveStudent(students);
    }

}
