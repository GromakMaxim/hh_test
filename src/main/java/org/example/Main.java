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
            if (value !=0) arr[i] = value;
        }

        System.out.println(check(arr, managers));
    }

    public static long check(int[] arr, int managers) {
        long sum = Arrays.stream(arr).mapToLong(i -> i).sum();
        if (sum < managers) return 0;
        if (managers == 1){
            return Arrays.stream(arr).mapToLong(i -> i).max().getAsLong();
        }

        long distinctValues = Arrays.stream(arr).distinct().count();
        if(distinctValues == 1 && managers <= arr.length) return arr[0];

        return bin(1, (sum / managers)+1, arr, managers);
    }

    public static long bin(long low, long high, int[] arr, int managers) {
        long mid = (high - low) / 2 + low;//медиана
        long candidate = 1;
        while (mid != 1) {
            int satisfiedManagers = 0;
            for (int value : arr) {
                satisfiedManagers += value / mid;
                if (satisfiedManagers >= managers){
                    candidate = Math.max(mid, candidate);
                    break;
                }
            }

            if (satisfiedManagers < managers) {
                high = mid;
            }
            if (satisfiedManagers > managers) {
                low = mid;
            }
            if (satisfiedManagers == managers){
                low = mid;
            }
            mid = (high - low) / 2 + low;
            if (candidate == mid) break;
        }


        return candidate;
    }

}



