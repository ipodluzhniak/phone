package randomtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        RandomTest rt = new RandomTest();
        List<Integer> nums = rt.generateInitialNumbers(100, 10);
        System.out.println(nums);
        //System.out.println(rt.getSequentialGroups(nums, 10));
        //System.out.println(rt.getRoundedGroups(nums, 10));
        System.out.println(rt.getRandomGroups(nums, 10));
    }

    private List<Integer> generateInitialNumbers(int count, int max) {
        List<Integer> result = new LinkedList<>();
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < count; i++) {
            result.add(rand.nextInt(max));
        }
        return result;
    }

    private List<List<Integer>> getSequentialGroups(List<Integer> initial, int groupCount) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            result.add(new LinkedList<Integer>());
        }
        int groupSize = initial.size() / groupCount;
        for (int i = 0; i < initial.size(); i++) {
            result.get(i / groupSize).add(initial.get(i));
        }
        return result;
    }

    private List<List<Integer>> getRoundedGroups(List<Integer> initial, int groupCount) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            result.add(new LinkedList<Integer>());
        }
        for (int i = 0; i < initial.size(); i++) {
            result.get(i % groupCount).add(initial.get(i));
        }
        return result;
    }

    private List<List<Integer>> getRandomGroups(List<Integer> initial, int groupCount) {

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            result.add(new LinkedList<Integer>());
        }

        for (int i = 0; i < initial.size(); i++) {
            getAvailableGroup(result, initial.size() / groupCount, groupCount).add(initial.get(i));
        }
        return result;
    }

    private List<Integer> getAvailableGroup(List<List<Integer>> result, int maxSize, int groupCount) {
        Random rand = new Random(System.nanoTime());
        int groupNumber = rand.nextInt(groupCount);
        if (result.get(groupNumber).size() < maxSize) {
            return result.get(groupNumber);
        } else {
            for (List<Integer> item : result) {
                if (item.size() < maxSize) {
                    return item;
                }
            }
            return Collections.EMPTY_LIST;
        }
    }
}
