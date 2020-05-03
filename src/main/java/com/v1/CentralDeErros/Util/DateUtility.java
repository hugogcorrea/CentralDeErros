package com.v1.CentralDeErros.Util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public final class DateUtility {

    public static Date getStringAsDate(String dateAsString) throws ParseException {
        String pattern = "dd-MM-yyyy HH:mm:ss";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.parse(dateAsString);
    }
}
