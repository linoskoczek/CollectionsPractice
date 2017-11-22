package Groups;

import People.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Department {
    public String name;
    private List<Teacher> employees;

    public Department(String name) {
        this.name = name;
        employees = new ArrayList<>();
    }

    public List<Teacher> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return employees != null ? employees.equals(that.employees) : that.employees == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        return result;
    }
}
