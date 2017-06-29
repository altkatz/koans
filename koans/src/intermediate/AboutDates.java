package intermediate;

import com.sandwich.koan.Koan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;


public class AboutDates {

    private Date date = new Date(100010001000L);

    @Koan
    public void dateToString() {
        assertEquals(date.toString(), "Sat Mar 03 20:33:21 CST 1973");
    }

    @Koan
    public void changingDateValue() {
        int oneHourInMiliseconds = 3600000;
        date.setTime(date.getTime() + oneHourInMiliseconds);
        assertEquals(date.toString(), "Sat Mar 03 21:33:21 CST 1973");
    }

    @Koan
    public void usingCalendarToChangeDates() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        assertEquals(cal.getTime().toString(), "Tue Apr 03 21:33:21 CST 1973");
    }

    @Koan
    public void usingRollToChangeDatesDoesntWrapOtherFields() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.roll(Calendar.MONTH, 12);
        assertEquals(cal.getTime().toString(), "Sat Mar 03 21:33:21 CST 1973");
    }

    @Koan
    public void usingDateFormatToFormatDate() {
        Locale locale = new Locale("zh","CN");
        String formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT,locale).format(date);
        assertEquals(formattedDate, "1973-3-3");
    }

    @Koan
    public void usingDateFormatToFormatDateShort() {
        Locale locale = new Locale("zh","CN");
        String formattedDate = DateFormat.getDateInstance(DateFormat.SHORT, locale).format(date);
        assertEquals(formattedDate, "73-3-3");
    }

    @Koan
    public void usingDateFormatToFormatDateFull() {
        Locale locale = new Locale("zh","CN");
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL, locale).format(date);
        // There is also DateFormat.MEDIUM and DateFormat.LONG... you get the idea ;-)
        assertEquals(formattedDate, "1973年3月3日 星期六");
    }

    @Koan
    public void usingDateFormatToParseDates() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date2 = dateFormat.parse("01-01-2000");
        assertEquals(date2.toString(), date2.toString());
        // What happened to the time? What do you need to change to keep the time as well?
    }
}
