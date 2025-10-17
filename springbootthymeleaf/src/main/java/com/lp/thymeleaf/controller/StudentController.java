package com.lp.thymeleaf.controller;

import com.lp.thymeleaf.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {


    @GetMapping("/students")
    public String listStudent(Model model){

        Student s1 = new Student(1, "Lalit", "lalit@gmail.com", "7038898323");
        Student s2 = new Student(2, "Sunil", "sunil@gmail.com", "1234556778");
        Student s3 = new Student(3, "Mayur", "mayur@gmail.com", "9876543212");
        Student s4 = new Student(4, "Aman", "aman@gmail.com", "52138618236");
        Student s5 = new Student(103, "Jaynam", "jaynam@gmail.com", "98979797987");

        List<Student> list = new ArrayList<>(List.of(s1,s2,s3,s4,s5));

        model.addAttribute("students", list);

        return "studentList";
    }

    @GetMapping("/profile")
    public String showStudentProfile(Model model){

        Student student = new Student(4,"Rahul", "rahul@gmail.com", "7894561234");

        model.addAttribute("student" ,student);

        return "student_profile";
    }

    //Message Excptretion demo
    @GetMapping("/message-expression")
    public String showMessageExpression(Model model) {
        return "message-expression";
    }

    // Show the form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student()); // empty object for binding
        return "register";
    }

    // Handle form submission
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) {
        // Normally you’d save to DB here
        model.addAttribute("message", "Student Registered Successfully!");
        model.addAttribute("student", student);
        return "success";
    }

}
