package com.company;

import com.company.interfaces.List;

public class Main {

    public static void main(String[] args) {

    }

    public void run(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(22);
        list.add(3);
        list.add(14);
        list.add(2);
        list.add(1);
        list.add(45);
        list.add(1);

        list.sort();

        for(int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }
}
