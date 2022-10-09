package generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsExample {
    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3);
        printNumberList(intList);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3);
        printNumberList(doubleList);
        List<String> strList = List.of("hello", "world");
//        printNumberList(strList);  // Compile error!!

        /*
         * https://tedblob.com/java-generics-extends-vs-super/
         */
        List<? extends Number> numberList = getDoubleValues();  // Upper bounded
        Number number = numberList.get(0);  // Read only
//        numberList.add(10.1);   // Compile error!! Cannot write

        List<? super Integer> integerList = getIntValues();     // Lower bounded
//        Number value = integerList.get(0);  // Compile error!! Cannot read
//        integerList.add(new Object());  // Compile error!!
        integerList.add(10);    // Can add Integer or subclass of Integer
    }

    private static void printNumberList(List<? extends Number> list) {
        for (Number o : list) {
            System.out.println(o);  // Retrieve
        }
    }

    private static ArrayList<Integer> getIntValues() {
        ArrayList<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(10, 9, 7));
        return intList;
    }

    private static ArrayList<Double> getDoubleValues() {
        ArrayList<Double> doubleList = new ArrayList<>();
        doubleList.addAll(Arrays.asList(10.1, 9.1, 7.1));
        return doubleList;
    }
}
