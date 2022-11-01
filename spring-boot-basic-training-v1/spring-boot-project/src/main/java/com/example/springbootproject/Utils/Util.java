package com.example.springbootproject.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    private Util() {

    }

    public static LocalDate convertStringToDateFormat(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}
