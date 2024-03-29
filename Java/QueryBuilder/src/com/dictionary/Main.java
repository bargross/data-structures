package com.dictionary;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    public void run(String[] args) throws Exception {
        try {
            QueryBuilder builder = new QueryBuilder(true);
            String query = builder.select("id", "name", "surname")
                    .from("table1")
                    .where("name")
                    .like("leo")
                    .and("name2 = 'leo'")
                    .getQuery();

            System.out.println(query);

            builder.replaceLast("name NOT leo");
            System.out.println(builder.getQuery());
        } catch (Exception e) {

        }
    }
}
