package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

	    Either<Integer, List<Integer>> controlFlow = new Either<>();
	    controlFlow.setContainerFlag(true);
	    controlFlow.setRight(numbers);

	    controlFlow.<Integer>map(number -> number + 1);

        System.out.println("Start: ");
        controlFlow.<Integer>map(number -> {
            System.out.println(number);
            return number;
        });
    }
}
