package Generators;

import Groups.StudentGroup;
import People.Nationality;
import People.Student;
import Storing.AllGroups;
import Storing.GroupMembers;
import Storing.PeopleDatabase;

import java.text.Collator;
import java.util.List;

public class GroupManager {

    int groupStartingNumber = 10;
    private AllGroups listOfGroupDatabases = new AllGroups();
    private StudentGroup studentGroup = new StudentGroup("Group " + Integer.toString(groupStartingNumber++)+"c");

    public AllGroups createStudentGroups(PeopleDatabase<Student> students) {
        students.forEach(s -> {
            if(!studentGroup.getGroupMembers().isFull())
                studentGroup.getGroupMembers().add(s);
            else
                generateStudentGroup();
        });
        return listOfGroupDatabases;
    }

    private void generateStudentGroup() {
        studentGroup = new StudentGroup("Group " + Integer.toString(groupStartingNumber++)+"c");
        listOfGroupDatabases.add(studentGroup);
    }

}
