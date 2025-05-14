import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class WednesdayPractice {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
//        scanName();
//        System.out.println("The sum is: " + sum());
//        System.out.println("The avarage is: " + String.format("%.2f",scanThreeDoubles()));
//        printEachDigit();
//        reverseString();
//        formatTime();
//        convertStringDate();\
//        convertStringToIng();
//        custingDoubleToInt();
//convertCharToAsciiCode();
//        sumIntAndDouble();
//        gradeAverage();
        convertAsciiCodeToChar();

    }


    static void scanName() {

        System.out.println("Please enter your name");
        //  String name = in.nextLine();//It scans a whole line. But you have a problem with scaning a cople of lines it scans the enter as a line.
        String name = in.next(); //It scans only a single word
        System.out.println("Hello ,[" + name + "]");
    }

    static int sum() {

        System.out.println("Please enter the first number");
        int first = in.nextInt();
        System.out.println("Please enter the second number");
        int second = in.nextInt();
        return first + second;
    }

    static double scanThreeDoubles() {
        System.out.println("Enter the first double number");
        double first = in.nextDouble();
        System.out.println("Enter the second double number");
        double second = in.nextDouble();
        System.out.println("Enter the third double number");
        double third = in.nextDouble();
        return avarageDouble(first, second, third);
    }

    static double avarageDouble(double first, double second, double third) {
        return (first + second + third) / 3;
    }

    static void printEachDigit() {

        System.out.println("Please enter a 3 length number");
        int num = in.nextInt();
        System.out.println(num);

        String stringNum = ((num + "").length() > 3 ? (num + "").substring(0, 3) : (num + ""));
        for (char c : stringNum.toCharArray()) {
            System.out.println(c);
        }
    }

    static void reverseString() {
        System.out.println("Enter a string");
        String str = in.next();
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
    }

    static void formatTime() {
        System.out.println("Enter time in second");
        int timeInSeconds = in.nextInt();
        int second = 1, minuet = second * 60, hour = minuet * 60;
        int resultHour = 0, resultMinuets = 0, resultsSeconds = 0;

        resultHour = timeInSeconds / hour;
        timeInSeconds -= resultHour * hour;

        resultMinuets = timeInSeconds / minuet;
        timeInSeconds -= resultMinuets * minuet;

        resultsSeconds = timeInSeconds / second;
//timeInSeconds -= resultsSeconds*second;
        System.out.println(resultHour + ":" + resultMinuets + ":" + resultsSeconds);
    }

//    static void formatTime() {
//        System.out.println("Enter time in second");
//        int timeInSeconds = in.nextInt();
//
//        int hours = timeInSeconds / 3600;
//        int minutes = (timeInSeconds % 3600) / 60;
//        int seconds = timeInSeconds % 60;
//
//        System.out.printf("%02d:%02d:%02d", hours, minutes, seconds);
//    }


    static void convertStringDate(){
        System.out.println("Enter a day as a number");
        int day = in.nextInt() % 31;
        System.out.println("Enter a month as a number");
        String month = getMonth(in.nextInt());
        System.out.println("Enter a year as a number");
        int year = in.nextInt();
        System.out.printf("%04d-%s-%02d",year,month,day);
    }
    static String getMonth(int month){
        switch (month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
        }
        return "January";
    }

    static void convertStringToIng(){
        int sum =0,avarage =0,counter =0;
        System.out.println("Enter a string only with digits");
        String num = in.next();

        for (char c : num.toCharArray()){
            sum+= Integer.parseInt(c+"");
            counter++;
            System.out.print(c+" ");
        }
        System.out.println();
        System.out.println("Sum of all: "+sum);
        System.out.println("Average: "+sum/counter);
    }

    static void custingDoubleToInt(){
        System.out.println("Enter a number");
double num = in.nextDouble();
        System.out.println((int)num);
        System.out.println(num);

    }

    static void convertCharToAsciiCode(){
        System.out.println("Enter a char");
        char symbol = in.next().charAt(0);
        System.out.println((int)symbol);
        System.out.println(symbol);

    }

    static void sumIntAndDouble(){
        int x = 10;
        double y = 13.0043;
        System.out.println(x+y);
        System.out.println((int)(x+y) );
    }

    static void gradeAverage(){
        double first = 95.33,second = 89.99, third = 97.7;
        System.out.println((first+second+third)/3);
        System.out.println((int)(first+second+third)/3);
    }

    static void convertAsciiCodeToChar(){
        System.out.println("Enter an ascii code");
        int asciiCode = in.nextInt();
        char ascii = (char) asciiCode;
        System.out.println(ascii);
        System.out.println((char) (asciiCode+32));
    }

    /* Exercise 3*/

    static  void printArray(){
        int[] arr = {3,4,9,87,};
        for (int current : arr){
            System.out.println(current);
        }
    }
}
