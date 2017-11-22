package Generators;

import Groups.Department;
import People.Teacher;
import Storing.AllDepartments;
import Storing.PeopleDatabase;

import java.util.Arrays;
import java.util.Random;

public class DepartmentGenerator {
    public AllDepartments createDepartments(int numberOfDepartments, PeopleDatabase<Teacher> teachers) {
        int departmentCount = 0;
        AllDepartments allDepartments = new AllDepartments();

        for (int i = 0; i < numberOfDepartments && i < Data.departmentNames.length; i++) {
            Department department = new Department(Data.departmentNames[departmentCount++]);
            allDepartments.add(department);
        }

        assignRandomTeachersToDepartments(allDepartments,teachers);

        return allDepartments;
    }

    public void assignRandomTeachersToDepartments(AllDepartments allDepartments, PeopleDatabase<Teacher> teachers) {
        Random r = new Random();
        Department[] departments = allDepartments.toArray(new Department[allDepartments.size()]);
        teachers.forEach(t -> departments[r.nextInt(departments.length)].getEmployees().add(t));
    }
}
