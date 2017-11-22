package Tests;

import Generators.*;
import Groups.Subject;
import People.Nationality;
import People.Person;
import People.Student;
import People.Teacher;
import Storing.AllDepartments;
import Storing.AllGroups;
import Storing.AllSubjects;
import Storing.PeopleDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AppTest {
    private PeopleGenerator peopleGenerator = new PeopleGenerator();
    private DepartmentGenerator departmentGenerator = new DepartmentGenerator();
    private GroupManager groupManager = new GroupManager();
    private SubjectGenerator subjectGenerator = new SubjectGenerator();
    private PeopleDatabase<Student> students;
    private PeopleDatabase<Teacher> teachers;
    private AllDepartments allDepartments = new AllDepartments();
    private AllGroups allGroups = new AllGroups();
    private AllSubjects allSubjects = new AllSubjects();

    @Before
    public void createPeople() {
        students = peopleGenerator.generateStudents(150);
        teachers = peopleGenerator.generateTeachers(10);
        allGroups = groupManager.createStudentGroups(students);
        allDepartments = departmentGenerator.createDepartments(3,teachers);
        allSubjects = subjectGenerator.generateSubjects(10,allDepartments,teachers,allGroups);
    }

    @Test
    public void countHowManyStudentsAndTeachersAreInDatabases() { //checking whether there were no copies
        Assert.assertEquals(150, students.size());
        Assert.assertEquals(10, teachers.size());
    }

    @Test
    public void generatePesel() {
        PeselGenerator peselGenerator = new PeselGenerator();
        System.out.print("One random pesel: " + peselGenerator.generate());
    }

    @Test
    public void showSubjectsWithLecturersSupervisingDepartmentAndStudents() {
        System.out.println("== Subject list with lecturers and students attending");
        allSubjects.forEach(s -> {
            System.out.println(s.getName() + " " + s.getLecturer() + " " + s.getSupervising().name);
            System.out.println("Attending students:");
            s.getStudentGroups().forEach(g -> {
                System.out.println("("+g.getName()+")");
                g.getGroupMembers().forEach(System.out::println);
            });
            System.out.println("___");
        });
    }

    @Test
    public void checkIfGroupHasLessOrEqualThen10AndMoreThan0Students() {
        long counter = allGroups.stream()
                .map(g -> g.getGroupMembers().size())
                .filter(size -> size == 0 && size > 10)
                .count();
        Assert.assertTrue(counter == 0);
    }

    @Test
    public void showSortedTeachers() {
        System.out.println("== Sorted teachers:\n" + teachers.listToString(teachers.sorted()));
    }

    @Test
    public void generateSinglePerson() {
        System.out.print("One random guy: ");
        Person p = peopleGenerator.generateRandomPerson();
        System.out.println(p);
    }

    @Test
    public void filterPeopleWhoAreFromUkraine() {
        List<Student> ukrainianStudents = students.getPeopleWithNationality(Nationality.Ukrainian);
        System.out.println("== Ukrainian people: \n" + students.listToString(ukrainianStudents));

        ukrainianStudents.forEach(e -> Assert.assertEquals(Nationality.Ukrainian, e.getNationality()));
    }
}
