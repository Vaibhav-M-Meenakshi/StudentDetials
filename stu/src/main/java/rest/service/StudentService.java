package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.StudentDao;
import rest.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> getStudents() {
        return studentDao.getAllStudent();
    }

    public void saveStudent(ArrayList<Student> students) {
        ListIterator<Student> iter = students.listIterator();
        while(iter.hasNext()){
            Student s = iter.next();
            studentDao.addStudent(s.getFirstName(),s.getLastName());
        }

    }

}
