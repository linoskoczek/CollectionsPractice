package People;

import java.time.LocalDate;

public abstract class Person implements Comparable<Person> {
    private String firstName, surname, pesel;
    private LocalDate birthsDate;
    private Nationality nationality;

    public Person(String firstName, String surname, String pesel, LocalDate birthsDate, Nationality nationality) {
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
        this.birthsDate = birthsDate;
        this.nationality = nationality;
    }

    @Override
    public int compareTo(Person person) {
        int result = surname.compareTo(person.surname);
        if(result == 0)
            result = firstName.compareTo(person.firstName);
        if(result == 0)
            result = birthsDate.compareTo(person.birthsDate);
        if(result == 0)
            result = pesel.compareTo(person.pesel);
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

    public LocalDate getBirthsDate() {
        return birthsDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (pesel != null ? !pesel.equals(person.pesel) : person.pesel != null) return false;
        if (birthsDate != null ? !birthsDate.equals(person.birthsDate) : person.birthsDate != null) return false;
        return nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        result = 31 * result + (birthsDate != null ? birthsDate.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        return result;
    }
}
