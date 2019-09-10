package com.dictionary;


import com.dictionary.interfaces.Query;

public class QueryBuilder implements Query {
    //
    private StringBuffer queryBuilder;
    private String lastValue;
    private String lastAction;

    // flags
    private boolean columnsAdded = false;
    private boolean mainTableAdded = false;
    private boolean whereExpressionAdded = false;
    private boolean andExpressionAdded = false;
    private boolean joinAdded = false;
    private boolean trackLastValue = false;


    public QueryBuilder() { queryBuilder = new StringBuffer("SELECT \r\n"); }
    public QueryBuilder(boolean trackLastValue) {
        queryBuilder = new StringBuffer("SELECT \r\n");
        this.trackLastValue = trackLastValue;
    }

    public Query select(String ...columns) {
        if(columns.length > 0) {
            for (int index = 0; index < columns.length; ++index) {
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
        if(!tableName.isEmpty() && columnsAdded) {
            queryBuilder.append("FROM " );
            queryBuilder.append(tableName);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = tableName;
                lastAction = "FROM ";
            }
            mainTableAdded = true;
        }
        return this;
    }

    public Query where(String expression) {
        if(!expression.isEmpty() && mainTableAdded) {
            queryBuilder.append("WHERE ");
            queryBuilder.append(expression);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = expression;
                lastAction = "WHERE ";
            }
            whereExpressionAdded = true;
        }
        return this;
    }

    public Query and(String expression) {
        if (!expression.isEmpty() && whereExpressionAdded) {
            queryBuilder.append("AND ");
            queryBuilder.append(expression);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = expression;
                lastAction = "AND ";
            }
            andExpressionAdded = true;
        }
        return this;
    }

    public Query join(String tableName) {
        if (!tableName.isEmpty() && mainTableAdded) {
            queryBuilder.append("JOIN ");
            queryBuilder.append(tableName);
            queryBuilder.append("\r\n");

            if(trackLastValue) {
                lastValue = tableName;
                lastAction = "JOIN ";
            }
            joinAdded = true;
        }
        return this;
    }

    public Query on(String expression) {
        if (!expression.isEmpty() && joinAdded) {
            queryBuilder.append("ON ");
            queryBuilder.append(expression);
            queryBuilder.append("\r\n");

            if (trackLastValue) {
                lastValue = expression;
                lastAction = "ON ";
            }
        }
        return this;
    }

    public Query like(String value) {
        if(!value.isEmpty() && whereExpressionAdded) {
            queryBuilder.append("LIKE ");
            queryBuilder.append("'" + value + "'");
            queryBuilder.append(" \r\n");

            if (trackLastValue) {
                lastValue = value;
                lastAction = "LIKE ";
            }
        }
        return this;
    }

    public void reset() {
        queryBuilder = new StringBuffer("SELECT \r\n");
    }

    public void removeLast() {
        replaceValue((lastAction+lastValue), "");
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
}
