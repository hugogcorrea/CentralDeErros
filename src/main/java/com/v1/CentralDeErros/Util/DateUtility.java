package com.v1.CentralDeErros.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

    public static Date getStringAsDate(String dateAsString) throws ParseException {
        String pattern = "dd-MM-yyyy HH:mm:ss";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.parse(dateAsString);
    }
}
