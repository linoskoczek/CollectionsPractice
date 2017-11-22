package Tests;

import People.Nationality;
import People.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;

class LocaleTest {

    void displayLocales() {
        Locale list[] = DateFormat.getAvailableLocales();
        for (Locale aLocale : list) {
            System.out.println(aLocale.toString() + " " + aLocale.getDisplayCountry());
        }
        Locale polish = new Locale("pl", "PL");
        Assertions.assertTrue(polish.getDisplayCountry().equals("Poland"));
    }

    @Test
    void localeTest() {
        LocalDate date = LocalDate.of(1960,11,11);
        Student student1 = new Student("Jan","Kowalski","11111111111", date, Nationality.Polish, 1234);
        date = LocalDate.of(1970,11,11);
        Student student2 = new Student("Jan","Kowalski","11111111111", date, Nationality.Polish, 1234);

        Assertions.assertEquals(1970, student2.getBirthsDate().getYear());
        Assertions.assertEquals(1960,student1.getBirthsDate().getYear());

        Assertions.assertEquals(Nationality.Polish, student1.getNationality());
    }

}