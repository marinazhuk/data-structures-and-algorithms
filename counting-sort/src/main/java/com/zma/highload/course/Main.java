package com.zma.highload.course;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tests started");
        Random random = new Random();

        int[] smallDifference10 = random
                .ints(0, 5)
                .limit(10).toArray();

        int[] bigDifference10 = random
                .ints(0, 10000)
                .limit(10).toArray();

        int[] smallDifference10000 = random
                .ints(0, 5)
                .limit(10000).toArray();

        int[] bigDifference10000 = random
                .ints(0, 10000)
                .limit(10000).toArray();

        int[][] datasets = {smallDifference10, bigDifference10, smallDifference10000, bigDifference10000};

        List.of(new CountingSort(), new MergeSort()).forEach( sorter -> {

            for (int[] dataset : datasets) {
                sortAndMeasure(sorter, dataset.clone());
            }
            System.out.println();
        });
        System.out.println("Tests finished");
    }

    private static void sortAndMeasure(Sort sorter, int[] input) {
        long start = System.nanoTime();
        sorter.sort(input);
        long res = (System.nanoTime() - start) / 1000;

        System.out.println(sorter.getName() + " for " + input.length + " elements in range [0," + input[input.length-1] + "]: " + res + " microseconds");
    }
}