package Storing;

import Groups.StudentGroup;
import People.Student;

import java.util.HashSet;
import java.util.TreeSet;

public class GroupMembers extends HashSet<Student> {
    static int maximalNumberOfStudentsInGroup = 10;

    private StudentGroup group;

    public GroupMembers(StudentGroup group) {
        this.group = group;
    }

    public StudentGroup getGroup() {
        return group;
    }

    public boolean isFull() {
        return size() >= 10;
    }

    @Override
    public boolean add(Student s) {
        if(size() >= maximalNumberOfStudentsInGroup)
            System.out.println("Group " + group.getName() + "is full!");
        else
            return super.add(s);
        return false;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        this.forEach(e -> string.append(e.toString()).append("\n"));
        return string.toString();
    }
}
