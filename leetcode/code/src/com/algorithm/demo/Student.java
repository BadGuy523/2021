package com.algorithm.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student {
    private int age;
    private String sex;

    public Student() {
    }

    public Student(int age, String sex) {
        this.age = age;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student studentOne = new Student(12,"男");
        Student studentTwo = new Student(19,"女");
        Student studentThree = new Student(23,"男");
        Student studentFour = new Student(4,"女");
        Student studentFive = new Student(87,"男");
        students.add(studentOne);
        students.add(studentTwo);
        students.add(studentThree);
        students.add(studentFour);
        students.add(studentFive);
        sort(students);
        Map<String, List<Student>> collect1 = students.stream().collect(Collectors.groupingBy(stu -> stu.getSex()));
        System.out.println();
    }

    private static void sort(List<Student> students) {
        List<Student> collect = students.parallelStream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age - o2.age;
            }
        }).collect(Collectors.toList());
        collect.forEach(student -> System.out.println(student.age));
    }
}
