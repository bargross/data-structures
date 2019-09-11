package com.dictionary;


import com.dictionary.Util.KeywordSelector;
import com.dictionary.interfaces.Query;

import java.util.function.Consumer;
import java.util.function.Function;

public class QueryBuilder implements Query {
    //
    private StringBuffer queryBuilder;
    private String lastValue;

    //
    private KeywordSelector selector;

    // flags
    private boolean columnsAdded = false;
    private boolean mainTableAdded = false;
    private boolean whereExpressionAdded = false;
    private boolean andExpressionAdded = false;
    private boolean joinAdded = false;
    private boolean trackLastValue = false;


    public QueryBuilder() {
        selector = KeywordSelector.SELECT;
        queryBuilder = new StringBuffer(selector.getValue());
    }

    public QueryBuilder(boolean trackLastValue) {
        selector = KeywordSelector.SELECT;
        queryBuilder = new StringBuffer(selector.getValue());
        this.trackLastValue = trackLastValue;
    }

    public Query select(String ...columns) {
        if(columns.length > 0) {
            for (int index = 0; index < columns.length; ++index) {
                if(columns[index].isEmpty()) {
                    continue;
                }
                if (index < columns.length - 1) {
                    queryBuilder.append("   ");
                    queryBuilder.append(columns[index]);
                    queryBuilder.append(", \r\n");
                    continue;
                }

                queryBuilder.append("   ");
                queryBuilder.append(columns[index]);
                queryBuilder.append(" \r\n");
            }

            columnsAdded = true;
        }
        return this;
    }

    public Query from(String tableName) {
        return process(tableName, columnsAdded, validated -> {
            selector = KeywordSelector.FROM;
            queryBuilder.append(selector.getValue());
            queryBuilder.append(tableName);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = tableName;
            }
            mainTableAdded = true;
        });
    }

    public Query where(String expression) {
        return process(expression, mainTableAdded, validated -> {
            selector = KeywordSelector.WHERE;
            queryBuilder.append(selector.getValue());
            queryBuilder.append(expression);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = expression;
            }
            whereExpressionAdded = true;
        });
    }

    public Query and(String expression) {
        return process(expression, whereExpressionAdded, validated -> {
            selector = KeywordSelector.AND;
            queryBuilder.append(selector.getValue());
            queryBuilder.append(expression);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = expression;
            }
            andExpressionAdded = true;
        });
    }

    public Query join(String tableName) {
        return process(tableName, mainTableAdded, validated -> {
            selector = KeywordSelector.JOIN;
            queryBuilder.append(selector.getValue());
            queryBuilder.append(tableName);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = tableName;
            }
            joinAdded = true;
        });
    }

    public Query on(String expression) {
        return process(expression, joinAdded, validated -> {
            selector = KeywordSelector.ON;
            queryBuilder.append(selector.getValue());
            queryBuilder.append(expression);
            queryBuilder.append("\r\n");

            if (trackLastValue) {
                lastValue = expression;
            }
        });
    }

    public Query like(String value) {
        return process(value, whereExpressionAdded, validated -> {
            selector = KeywordSelector.LIKE;
            queryBuilder.append(selector.getValue());
            queryBuilder.append("'" + value + "'");
            queryBuilder.append(" \r\n");

            if (trackLastValue) {
                lastValue = value;
            }
        });
    }

    public void reset() {
        queryBuilder = new StringBuffer("SELECT \r\n");
    }

    public void removeLast() {
        replaceValue((selector.getValue()+lastValue), "");
    }

    public void replaceLast(String value) {
        if(!value.isEmpty() && lastValue != null && !lastValue.isEmpty()) {
            replaceValue(lastValue, value);
        }
    }

    public void replace(String current, String value) {
        if(queryBuilder.indexOf(value) == -1) {
            throw new IllegalArgumentException("Cannot replace value ("+current+"), it does not exist");
        }

        if(!current.isEmpty() || current == null) {
            throw new IllegalArgumentException("Current value is empty");
        }

        if(value == null) {
            throw new IllegalArgumentException("Value is null");
        }

        replaceValue(current, value);
    }

    public String getQuery() {
        return queryBuilder.toString();
    }

    private void replaceValue(String current, String value) {
            int start = queryBuilder.indexOf(current);
            int end = start + current.length();
            queryBuilder.replace(start, end, value);
    }

    private Query process(String stringValue, boolean flag, Consumer<String> func) {
        if(!stringValue.isEmpty() && flag) {
            func.accept(stringValue);
        }
        return this;
    }
}
