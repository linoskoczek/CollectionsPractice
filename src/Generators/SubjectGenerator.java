package Generators;

import Groups.Department;
import Groups.StudentGroup;
import Groups.Subject;
import People.Teacher;
import Storing.AllDepartments;
import Storing.AllGroups;
import Storing.AllSubjects;
import Storing.PeopleDatabase;

import java.util.*;

public class SubjectGenerator {

    public AllSubjects generateSubjects(int numberOfSubjects, AllDepartments departments, PeopleDatabase<Teacher> teachers, AllGroups allGroups) {
        Teacher[] teachersArr = teachers.toArray(new Teacher[teachers.size()]);
        Department[] departmentsArr = departments.toArray(new Department[departments.size()]);
        StudentGroup[] studentGroupsArr = allGroups.toArray(new StudentGroup[allGroups.size()]);
        List<StudentGroup> listOfStudentGroups = new LinkedList<>(Arrays.asList(studentGroupsArr));
        Random r = new Random();

        AllSubjects allSubjects = new AllSubjects();
        int subjectCount = 0;
        int minimalGroupsPerSubject = studentGroupsArr.length/numberOfSubjects + 1;

        for (int i = 0; i < numberOfSubjects && i < Data.subjectNames.length; i++) {
            Subject subject = new Subject(Data.subjectNames[subjectCount++],
                    departmentsArr[r.nextInt(departmentsArr.length)],
                    teachersArr[r.nextInt(teachersArr.length)]);
            allSubjects.add(subject);

            int howMany = r.nextInt(2) + minimalGroupsPerSubject;

            for(int j = 0; j < howMany; j++) {
                if(listOfStudentGroups.size() == 0) {
                    listOfStudentGroups = new LinkedList<>(Arrays.asList(studentGroupsArr));
                    break;
                }
                subject.getStudentGroups().add(listOfStudentGroups.get(0));
                listOfStudentGroups.remove(0);
            }
        }

        return allSubjects;
    }

}
