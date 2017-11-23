package Generators;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

public class PeselGenerator {
    private final Random rnd = new Random();

    public String generate() {
        StringBuilder firstTen = new StringBuilder(getRandomDate()).append(getRandomSeries());

        return firstTen.append(getControlDigit(firstTen)).toString();
    }

    private StringBuilder getRandomDate() {
        String s_month, s_day, s_year;
        int year = rnd.nextInt(100) + 1900;
        int shortYear = year % 100;
        int month = rnd.nextInt(12) + 1;
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),month-1,cal.get(Calendar.DAY_OF_MONTH));
        int day = rnd.nextInt(cal.getActualMaximum(Calendar.DAY_OF_MONTH)) + 1;

        month = getMonthNumber(year, month);

        s_year = shortYear < 10 ? "0"+Integer.toString(year) : Integer.toString(year);
        s_month = month < 10 ? "0"+Integer.toString(month) : Integer.toString(month);
        s_day = day < 10 ? "0"+Integer.toString(day) : Integer.toString(day);

        return new StringBuilder(s_year.substring(2,4)).append(s_month).append(s_day);
    }

    private int getMonthNumber(int year, int month) {
        if(year >= 2000) {
            if(year <= 2099)
                month += 20;
            else if(year <= 2199)
                month += 40;
            else
                month += 60;
        }
        else if(year <= 1899)
            month += 80;

        return month;
    }

    private String getRandomSeries() {
        return Integer.toString(rnd.nextInt(900) + 100) + Integer.toString(rnd.nextInt(2));
    }

    public String getControlDigit(StringBuilder string) {
        int control = Character.getNumericValue(string.charAt(0)) * 9 +
                Character.getNumericValue(string.charAt(1)) * 7 +
                Character.getNumericValue(string.charAt(2)) * 3 +
                Character.getNumericValue(string.charAt(3)) +
                Character.getNumericValue(string.charAt(4)) * 9 +
                Character.getNumericValue(string.charAt(5)) * 7 +
                Character.getNumericValue(string.charAt(6)) * 3 +
                Character.getNumericValue(string.charAt(7)) +
                Character.getNumericValue(string.charAt(8)) * 9 +
                Character.getNumericValue(string.charAt(9)) * 7;
        return Integer.toString(control % 10);
    }

    public LocalDate getLocalDate(String pesel) {
        int year = Integer.parseInt(pesel.substring(0,2));
        int month = Integer.parseInt(pesel.substring(2,4));
        int day = Integer.parseInt(pesel.substring(4,6));
        if(month > 80) {
            month -= 80;
            year += 1800;
        }
        else if(month > 60) {
            month -= 60;
            year += 2200;
        }
        else if(month > 40) {
            month -= 40;
            year += 2100;
        }
        else if(month > 20) {
            month -= 20;
            year += 2000;
        }
        else
            year += 1900;
        return LocalDate.of(year,month,day);
    }
}
