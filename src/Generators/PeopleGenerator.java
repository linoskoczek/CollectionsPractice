package Generators;

import People.Nationality;
import People.Person;
import People.Student;
import People.Teacher;
import Storing.PeopleDatabase;

import java.text.Collator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeopleGenerator {
    private Random rnd = new Random();
    private PeselGenerator peselGenerator = new PeselGenerator();
    private ArrayList<String> peselsUsed = new ArrayList<>();
    private ArrayList<Integer> studentNumberUsed = new ArrayList<>();
    Collator collator = Collator.getInstance(Nationality.Polish.getLocale());

    public PeopleDatabase<Student> generateStudents(int numberOfStudents) {
        PeopleDatabase<Student> students = new PeopleDatabase<>();

        for (int i = 0; i < numberOfStudents; i++) {
            students.add(generateStudent());
        }

        return students;
    }

    public PeopleDatabase<Teacher> generateTeachers(int numberOfTeachers) {
        PeopleDatabase<Teacher> teachers = new PeopleDatabase<>();

        for (int i = 0; i < numberOfTeachers; i++) {
            teachers.add(generateTeacher());
        }

        return teachers;
    }

    public Person generateRandomPerson() {
        int group = rnd.nextInt(2);
        switch(group) {
            case 0:
                return generateStudent();
            case 1:
                return generateTeacher();
        }
        return null;
    }

    public Student generateStudent() {
        Object[] data = generateBasicData();
        int studentNo = rnd.nextInt(99999);
        while(studentNumberUsed.contains(studentNo)) studentNo = rnd.nextInt(99999);

        peselsUsed.add((String) data[2]);
        studentNumberUsed.add(studentNo);

        return new Student((String)data[0],(String)data[1],(String)data[2],(LocalDate)data[3],(Nationality)data[4],studentNo);
    }

    public Teacher generateTeacher() {
        Object[] data = generateBasicData();
        LocalDate hiredate = generateDateAfter((LocalDate) data[3]);
        peselsUsed.add((String) data[2]);
        Teacher.AcademicDegree degree = Teacher.AcademicDegree.values()[rnd.nextInt(Teacher.AcademicDegree.values().length)];

        return new Teacher((String)data[0],(String)data[1],(String)data[2],(LocalDate)data[3],(Nationality)data[4],degree, hiredate);
    }

    private LocalDate generateDateAfter(LocalDate date) {
        long diff = LocalDate.now().toEpochDay() - date.toEpochDay();
        return date.plusDays(rnd.nextInt((int) diff));
    }

    private Object[] generateBasicData() {
        String pesel = peselGenerator.generate();
        while(peselsUsed.contains(pesel)) peselGenerator.generate();

        return new Object[] {
                Data.firstNames[rnd.nextInt(Data.firstNames.length)],
                Data.surnames[rnd.nextInt(Data.surnames.length)],
                pesel,
                peselGenerator.getLocalDate(pesel),
                Nationality.values()[rnd.nextInt(Nationality.values().length)]
        };
    }

    private LocalDate generateBirthsDate() {
        int year = rnd.nextInt(100) + 1917;
        int month = rnd.nextInt(12) + 1;
        int day = rnd.nextInt(LocalDate.of(year,month,1).lengthOfMonth()) + 1;

        return LocalDate.of(year,month,day);
    }

}
