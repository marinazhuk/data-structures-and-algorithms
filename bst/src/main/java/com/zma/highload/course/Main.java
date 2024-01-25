package com.zma.highload.course;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final Random RANDOM = new Random();
    private static final String FILE_NAME = "measurements-AVL";
    private static final String FILE_HEADER = "dataset size, insert ns, search ns, delete ns (nanoseconds)";
    private static final String FILE_EXTENSION = ".csv";


    public static void main(String[] args) throws IOException {
        System.out.println("Start tests");

        int diff = 10000;
        List<Integer> datasetSizes = new ArrayList<>(List.of(100, 1000, 5000, 10000));
        for (int i=0; i < 99; i++) {
            int nextValue = datasetSizes.get(datasetSizes.size() - 1) + diff;
            datasetSizes.add(nextValue);
        }

        List<Measurement> measurements = new ArrayList<>();
        for (int datasetSize : datasetSizes) {

            AVLTree avlTree = createAvlTree(datasetSize);

            Measurement measurement = testTree(avlTree, datasetSize);

            measurements.add(measurement);
        }

        writeResultsToCsv(measurements);

        System.out.println("Finish tests");
    }

    private static AVLTree createAvlTree(int size) {

        AVLTree avlTree = new AVLTree();

        new Random().ints()
                .distinct()
                .limit(size)
                .boxed()
                .forEach(avlTree::insert);
        return avlTree;
    }

    private static Measurement testTree(AVLTree tree, int size) {

        int count = 10;

        Set<Integer> ints = generateIntegers(count);

        long startDelete = System.nanoTime();
        for (int key : ints) {
            tree.delete(key);
        }
        long delete = (System.nanoTime() - startDelete) / count;

        long startInsert = System.nanoTime();
        for (int key : ints) {
            tree.insert(key);
        }
        long insert = (System.nanoTime() - startInsert) / count;

        ints = generateIntegers(count);
        long startSearch = System.nanoTime();
        for (int key : ints) {
            tree.find(key);
        }
        long search = (System.nanoTime() - startSearch) / count;

        return new Measurement(size, insert, search, delete);
    }

    private static Set<Integer> generateIntegers(int count) {
        return RANDOM.ints().distinct().limit(count).boxed()
                .collect(Collectors.toSet());
    }

    private static void writeResultsToCsv(List<Measurement> measurements) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_NAME + FILE_EXTENSION));

        printWriter.println(FILE_HEADER);
        measurements.forEach(printWriter::println);
        printWriter.close();
    }

}