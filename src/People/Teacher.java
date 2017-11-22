package People;

import java.time.LocalDate;

public class Teacher extends Person {
    public enum AcademicDegree {
        PaE,Bachelor,Master
    }

    private AcademicDegree degree;
    private LocalDate hireDate;

    public Teacher(String firstName, String surname, String pesel, LocalDate birthsDate, Nationality nationality, AcademicDegree degree, LocalDate hireDate) {
        super(firstName, surname, pesel, birthsDate, nationality);
        this.degree = degree;
        this.hireDate = hireDate;
    }

    public AcademicDegree getDegree() {
        return degree;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return getFirstName() + " " +
                getSurname() + " " +
                getPesel() + " " +
                getBirthsDate() + " " +
                getNationality() + " " +
                getDegree() + " " +
                getHireDate();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Teacher teacher = (Teacher) o;

        if (degree != teacher.degree) return false;
        return hireDate != null ? hireDate.equals(teacher.hireDate) : teacher.hireDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        return result;
    }
}
