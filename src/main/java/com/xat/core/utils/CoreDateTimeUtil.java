package com.xat.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CoreDateTimeUtil {
   public static Date numberToDate(int day, int month, int year) throws ParseException {
      String dateString = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
      Date date = (new SimpleDateFormat("dd/MM/yyyy")).parse(dateString);
      return date;
   }

   public static Date getLastDayOfMonth(int month, int year) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String date = "01/" + String.format("%02d", month) + "/" + year;
      LocalDate localDate = LocalDate.parse(date, formatter);
      LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
      Date lastDayOfMonth = Date.from(lastDay.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(lastDayOfMonth);
      calendar.set(11, 23);
      calendar.set(12, 59);
      calendar.set(13, 59);
      calendar.set(14, 999);
      return calendar.getTime();
   }

   public static List<LocalDate> getListMonthByMonthYear(int fromMonth, int fromyear, int toMonth, int toYear) {
      new ArrayList();
      List<LocalDate> retCalendar = new ArrayList();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate beginCalendar = LocalDate.parse("01/" + String.format("%02d", fromMonth) + "/" + fromyear, formatter);

      for(LocalDate finishCalendar = LocalDate.parse(getLastDayOfMonth(toMonth, toYear).getDate() + "/" + String.format("%02d", toMonth) + "/" + toYear, formatter); beginCalendar.isBefore(finishCalendar); beginCalendar = beginCalendar.plusMonths(1L)) {
         retCalendar.add(beginCalendar);
      }

      return retCalendar;
   }

   public static Date getEndOfDay(Date date) {
      if (date != null) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.set(11, 23);
         calendar.set(12, 59);
         calendar.set(13, 59);
         calendar.set(14, 999);
         return calendar.getTime();
      } else {
         return null;
      }
   }

   public static Date getStartOfDay(Date date) {
      if (date != null) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.set(11, 0);
         calendar.set(12, 0);
         calendar.set(13, 0);
         calendar.set(14, 0);
         return calendar.getTime();
      } else {
         return null;
      }
   }

   public static Date getPrevDay(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(5, -1);
      calendar.set(11, 23);
      calendar.set(12, 59);
      calendar.set(13, 59);
      calendar.set(14, 999);
      return calendar.getTime();
   }

   public static Date getNextDay(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(5, 1);
      calendar.set(11, 0);
      calendar.set(12, 0);
      calendar.set(13, 0);
      calendar.set(14, 0);
      return calendar.getTime();
   }

   public static List<Date> getDatesInMonthJava7(int year, int month) {
      Calendar calendar = Calendar.getInstance();
      int date = 1;
      calendar.set(year, month, date);
      Date startDate = calendar.getTime();
      List<Date> datesInRange = new ArrayList();
      calendar.setTime(startDate);
      Calendar endCalendar = Calendar.getInstance();
      int maxDay = calendar.getActualMaximum(5);
      endCalendar.set(year, month, maxDay);
      endCalendar.add(5, 1);
      Date endDate = endCalendar.getTime();
      endCalendar.setTime(endDate);

      while(calendar.before(endCalendar)) {
         Date result = calendar.getTime();
         datesInRange.add(result);
         calendar.add(5, 1);
      }

      return datesInRange;
   }

   public static List<Date> getDatesByYearMonth(int year, int month) {
      Calendar calendar = Calendar.getInstance();
      int date = 1;
      calendar.set(year, month, date);
      Date startDate = calendar.getTime();
      List<Date> datesInRange = new ArrayList();
      calendar.setTime(startDate);
      Calendar endCalendar = Calendar.getInstance();
      int maxDay = calendar.getActualMaximum(5);
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
      if (simpleDateFormat.format(new Date()).equals(simpleDateFormat.format(calendar.getTime()))) {
         endCalendar = Calendar.getInstance();
      } else {
         endCalendar.set(year, month, maxDay);
      }

      endCalendar.add(5, 1);
      Date endDate = endCalendar.getTime();
      endCalendar.setTime(endDate);

      while(calendar.before(endCalendar)) {
         Date result = calendar.getTime();
         datesInRange.add(result);
         calendar.add(5, 1);
      }

      return datesInRange;
   }

   public static Date getLastTimeInYear(int year) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(1, year);
      calendar.set(2, 11);
      calendar.set(5, 31);
      calendar.set(11, 23);
      calendar.set(12, 59);
      calendar.set(13, 59);
      calendar.set(14, 999);
      return calendar.getTime();
   }

   public static Date getFirstTimeInYear(int year) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(1, year);
      calendar.set(2, 0);
      calendar.set(5, 1);
      calendar.set(11, 0);
      calendar.set(12, 0);
      calendar.set(13, 0);
      calendar.set(14, 0);
      return calendar.getTime();
   }
}
