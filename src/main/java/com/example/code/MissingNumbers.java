package com.example.code;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MissingNumbers {
    public static List<Integer> findMissingNumbers(int[] sequence) {
        int start = 0;
        List<Integer> missingNumbers = new ArrayList<>();
        for (int i = 1; i <= sequence[sequence.length - 1]; ) {
            if (i == sequence[start]) {
                start++;
                i++;
            } else {
                // 添加缺失的数字，并跳过已存在的数字
                while (start < sequence.length && sequence[start] < i) {
                    start++;
                }
                missingNumbers.add(i++);
            }
        }

        return missingNumbers;
    }

    public static List<Integer> findMissingNumbersTreeSet(int[] sequence) {
        List<Integer> missingNumbers = new ArrayList<>();
        TreeSet<Integer> numbers = new TreeSet<>();

        // 将序列中的所有数字添加到TreeSet中
        for (int num : sequence) {
            numbers.add(num);
        }

        // 创建一个从0到序列最大值之间的有序集合，用于比较缺失的数字
        TreeSet<Integer> expectedNumbers = new TreeSet<>();
        int maxInSequence = sequence[sequence.length - 1];

        for (int i = 0; i <= maxInSequence; i++) {
            expectedNumbers.add(i);
        }

        // 找出并打印缺失的数字
        System.out.println("Missing numbers: ");
        for (Integer expectedNum : expectedNumbers) {
            if (!numbers.contains(expectedNum)) {
                missingNumbers.add(expectedNum);
            }
        }
        return missingNumbers;
    }

//    public static void main(String[] args) {
//        int[] sequence = {1, 5, 6, 9999};
//        long timeMillis1 = System.currentTimeMillis();
//        List<Integer> result1 = findMissingNumbers(sequence);
//        System.out.println("time1=>" + (System.currentTimeMillis() - timeMillis1));
////        System.out.println("Missing numbers: " + result1);
//
//    }

    public static void main(String[] args) {
        int[] sequence = {1, 5, 6, 9999};
        long timeMillis2 = System.currentTimeMillis();
        List<Integer> result2 = findMissingNumbersTreeSet(sequence);
        System.out.println("time=>" + (System.currentTimeMillis() - timeMillis2));
//        System.out.println("Missing numbers: " + result2);
    }
}
