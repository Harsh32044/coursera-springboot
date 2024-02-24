package com.udemy.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @GetMapping
    public String getCourse() {
        return "You're on the courses API.";
    }

    @GetMapping("/{courseId}")
    public String getCourse(@PathVariable String courseId) {
        return "You're on the courses API. Current courseId is: ".concat(courseId);
    }
}
