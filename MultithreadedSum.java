// ***************************************************************************
// 
//   Joel John Thomas 
//   Z2004783
//   CSCI 470 - PE1 
// 
//   I certify that this is my own work and where appropriate an extension 
//   of the starter code provided for the assignment. 
// ***************************************************************************

import java.util.Random;
import java.util.concurrent.*;

// Task that sums a portion of an array using Callable
class ArraySumTask implements Callable<Integer> {
    private final int[] array;
    private final int start, end;

    public ArraySumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int sum = 0;
        // Sum elements in the assigned range
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }
}

public class MultithreadedSum {
    public static void main(String[] args) throws Exception {
        int arraySize = 1_000_000;
        int[] array = new int[arraySize];
        Random rand = new Random();

        // Fill array with random integers between 0 and 99
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100);
        }

        int numThreads = 4; // Number of threads (adjustable)
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        int segmentSize = arraySize / numThreads;

        // Start multithreaded summation
        long startTimeMulti = System.nanoTime();
        Future<Integer>[] futures = new Future[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * segmentSize;
            int end = (i == numThreads - 1) ? arraySize : start + segmentSize;
            futures[i] = executor.submit(new ArraySumTask(array, start, end));
        }

        // Collect partial sums from all threads
        int totalSum = 0;
        for (Future<Integer> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();
        long endTimeMulti = System.nanoTime();

        // Start single-threaded summation for comparison
        long startTimeSingle = System.nanoTime();
        int singleThreadSum = 0;
        for (int num : array) {
            singleThreadSum += num;
        }
        long endTimeSingle = System.nanoTime();

        // Print results
        System.out.println("Multithreaded sum: " + totalSum + ", Time: " + (endTimeMulti - startTimeMulti)/1e6 + " ms");
        System.out.println("Single-threaded sum: " + singleThreadSum + ", Time: " + (endTimeSingle - startTimeSingle)/1e6 + " ms");
    }
}