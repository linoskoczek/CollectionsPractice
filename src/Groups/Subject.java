package Groups;

import People.Student;
import People.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    String name;
    Department supervising;
    Teacher lecturer;
    private List<StudentGroup> studentGroups;

    public Subject(String name, Department supervising, Teacher lecturer) {
        this.name = name;
        this.supervising = supervising;
        this.lecturer = lecturer;
        studentGroups = new ArrayList<>();
    }

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public String getName() {
        return name;
    }

    public Department getSupervising() {
        return supervising;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (name != null ? !name.equals(subject.name) : subject.name != null) return false;
        if (supervising != null ? !supervising.equals(subject.supervising) : subject.supervising != null) return false;
        if (lecturer != null ? !lecturer.equals(subject.lecturer) : subject.lecturer != null) return false;
        return studentGroups != null ? studentGroups.equals(subject.studentGroups) : subject.studentGroups == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (supervising != null ? supervising.hashCode() : 0);
        result = 31 * result + (lecturer != null ? lecturer.hashCode() : 0);
        result = 31 * result + (studentGroups != null ? studentGroups.hashCode() : 0);
        return result;
    }
}
