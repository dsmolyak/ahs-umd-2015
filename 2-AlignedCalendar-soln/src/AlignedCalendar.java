import java.util.Scanner;
import java.util.Arrays;

class ConvertedDate {
    int month;
    int day;

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}

public class AlignedCalendar {
    private static int[] daysInMonthNonLeap = {31,28,31, 30,31,30, 31,31,30, 31,30,31};

    private static ConvertedDate convertDate(int year, int month, int day) {

        ConvertedDate result = new ConvertedDate();

        /* ------------------- INSERT CODE HERE ---------------------*/

        /* Fill in the month and date in the ConvertedDate object appropriately. 
         *
         * If the day is a holiday in the supervillain calendar, set month = -1.
         */

        //State to calculate day of the year.	
        int dayOfYear = day;
        for (int monthIter = 1; monthIter < month; monthIter++) {
            dayOfYear += daysInMonthNonLeap[monthIter-1];
        }

        //Deal with leap year.
        if ( (month > 2) && (year%4 == 0) && ((year%100 != 0) || (year%400 == 0)) ) {
            dayOfYear++;
        }

        if(dayOfYear > 364) {
            result.month = -1;
        } else {

            //Determine new month and day.	
            int newMonth = dayOfYear / 28;
            int newDay = dayOfYear % 28;

            if (newDay == 0) {
                newDay = 28;
            }
            else {
                newMonth++;
            }

            result.month = newMonth;
            result.day = newDay;
        }

        /* -------------------- END OF INSERTION --------------------*/

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();

        for (int testCase=0; testCase < numOfTestCases; testCase++) {
            int year = sc.nextInt();
            int month = sc.nextInt();
            int day = sc.nextInt();
            ConvertedDate converted = convertDate(year,month,day);
            if(converted.month != -1) {
                System.out.println(
                        year + "/" + month + "/" + day +
                        " became " +
                        year + "/" + converted.getMonth() + "/" + converted.getDay()
                        );
            } else {
                System.out.println(
                        year + "/" + month + "/" + day +
                        " became a HOLIDAY");
            }
        }

        sc.close();
    }
}

