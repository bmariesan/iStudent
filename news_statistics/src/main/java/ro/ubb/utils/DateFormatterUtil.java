package ro.ubb.utils;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtil {

    @Autowired
    private SimpleDateFormat format;

    public String dateToString(Date date) {
        return format.format(date);
    }

    public Date stringToDate(String date) throws ParseException {
        return format.parse(date);
    }

}
