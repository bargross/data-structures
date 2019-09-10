package com.dictionary.interfaces;

public interface Query {
    Query select(String ...columns);
    Query from(String tableName);
    Query where(String expression);
    Query and(String expression);
    Query join(String table);
    Query on(String expression);
    Query like(String value);
    String getQuery();
}
