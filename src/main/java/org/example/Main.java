package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int[]arr = {199, 453, 220, 601,199,199,199};
//        int managers = 6;
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

        //если суммы не хватает
        if (sum / managers < 1) return 0;

        //если на всех счетах одинаковые суммы
        long distinctValues = Arrays.stream(arr).distinct().count();
        if (distinctValues == 1 && managers == arr.length) return arr[0];

        arr = Arrays.stream(arr)
                .filter(i->i!=0)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        long upperBound = sum / managers;
        long lowerBound = arr[arr.length - 1];
        if (lowerBound == upperBound) return lowerBound;

        if (lowerBound > upperBound) lowerBound = 1;

        int diff = (int) (upperBound - lowerBound);
        if (diff == 0) return 1;

        long hypothesis = 0;
        for(long i = lowerBound; i<=upperBound; i++){
            long satisfiedManagers = 0;
            for (long accountSum : arr) {
                if (accountSum >= i) {
                    satisfiedManagers += accountSum / i;
                    if (satisfiedManagers >= managers) {
                        hypothesis = i;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (satisfiedManagers < managers && hypothesis != 0) break;
        }
        return hypothesis;
    }
}



