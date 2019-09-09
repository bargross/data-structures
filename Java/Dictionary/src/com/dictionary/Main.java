package com.dictionary;

public class Main {

    public static void main(String []args) {
        try
        {
            Main obj = new Main();
            obj.run (args);
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    public void run (String[] args) throws Exception
    {
        Dictionary<String, String> b = new Dictionary<String, String>();
        Dictionary<String, String> c = b;

        Dictionary<String, String> a = new Dictionary<String, String>();

        b.add("L", "M");
        b.add("J", "A");
        b.add("A", "F");
        b.add("P", "C");
        b.add("E", "1");
        b.add("D", "4");

        b.add("F", "C");
        b.add("B", "2");
        b.add("C", "3");


        System.out.println(b.containsKey("F"));
        System.out.println(b.containsKey("B"));
        System.out.println(b.containsKey("C"));

        System.out.println(b.containsKey("R"));
        System.out.println(b.containsKey("Z"));

        System.out.println();

        System.out.println(b.equals(c));
        System.out.println(b.equals(a));
        System.out.println(b.equals(null));
        System.out.println(b.equals(new String("")));

    }
}
