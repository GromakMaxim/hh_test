package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int accounts = scanner.nextInt();
        int managers = scanner.nextInt();

        int[] arr = new int[accounts];
        for (int i = 0; i < accounts; i++) {
            int value = scanner.nextInt();
            if (value != 0) arr[i] = value;
        }

        System.out.println(check(arr, managers));
    }

    public static long check(int[] arr, long managers) {
        //если всего 1 менеджер - берём самый большой счет
        if (managers == 1) return Arrays.stream(arr).max().getAsInt();

        long sum = Arrays.stream(arr).mapToLong(i -> i).sum();

        //если сумма по счетам = 0 или суммы не хватает
        if (sum == 0 || (sum / managers) < 1) return 0;

        //убираем 0
        long[] tempArr = Arrays.stream(arr).filter(i -> i != 0).mapToLong(i -> i).toArray();

        //если на всех счетах одинаковые суммы
        long distinctValues = Arrays.stream(tempArr).distinct().count();
        if (distinctValues == 1 && managers == tempArr.length) return tempArr[0];

        long upperBound = sum / managers;
        long lowerBound = Arrays.stream(tempArr).min().getAsLong();
        if (lowerBound == upperBound) return lowerBound;

        if (lowerBound > upperBound) lowerBound = 1;

        int diff = (int) (upperBound - lowerBound);
        if (diff == 0) return 1;

        long[] testValues = createTestArray(lowerBound, diff + 1);

        long hypothesis = 0;
        for (long value : testValues) {
            long satisfiedManagers = 0;
            for (long accountSum : tempArr) {
                if (accountSum >= value) {
                    satisfiedManagers += accountSum / value;
                    if (satisfiedManagers >= managers) {
                        hypothesis = value;
                    }
                }
            }
        }
        return hypothesis;
    }


    private static long[] createTestArray(long startValue, int length) {
        long[] testValues = new long[(int) length];

        long index = startValue;
        for (long i = 0; i < length; i++) {
            testValues[(int) i] = index;
            index++;
        }

        return testValues;
    }
}



