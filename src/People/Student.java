package People;

import java.time.LocalDate;

public class Student extends Person{
    private int studentNumber;

    public Student(String firstName, String surname, String pesel, LocalDate birthsDate, Nationality nationality, int studentNumber) {
        super(firstName, surname, pesel, birthsDate, nationality);
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return getFirstName() + " " +
                getSurname() + " " +
                getPesel() + " " +
                getBirthsDate() + " " +
                getNationality() + " " +
                getStudentNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        return studentNumber == student.studentNumber;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + studentNumber;
        return result;
    }

    public int getStudentNumber() {
        return studentNumber;
    }
}
