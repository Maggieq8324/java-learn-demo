package com.coisini.springbootlearn.enumTest;

public interface Period {

    enum WeekEnum implements Period{
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, weekend
    }

    enum MonthEnum implements Period{
        January, February, March, April, May, June
    }

    class PeriodTest {
        public static void main(String[] args) {
            Period week = WeekEnum.Friday;
            Period month = MonthEnum.April;

            System.out.println(month + " " + week); // April Friday
        }
    }

}
