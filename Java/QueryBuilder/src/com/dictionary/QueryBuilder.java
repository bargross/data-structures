package com.dictionary;


import com.dictionary.interfaces.Query;

public class QueryBuilder implements Query {
    private StringBuffer QueryBuilder;
    private boolean columnsAdded = false;
    private boolean mainTableAdded = false;
    private boolean whereExpressionAdded = false;
    private boolean andAdded = false;
    private boolean joinAdded = false;


    public QueryBuilder() { QueryBuilder = new StringBuffer("SELECT \r\n"); }

    public Query select(String ...columns) {
        if(columns.length > 0) {
            for (int index = 0; index < columns.length; ++index) {
                if (index < columns.length - 1) {
                    QueryBuilder.append("   ");
                    QueryBuilder.append(columns[index]);
                    QueryBuilder.append(", \r\n");
                    continue;
                }

                QueryBuilder.append("   ");
                QueryBuilder.append(columns[index]);
                QueryBuilder.append(" \r\n");
            }

            columnsAdded = true;
        }
        return this;
    }

    public Query from(String tableName) {
        if(!tableName.isEmpty() && columnsAdded) {
            QueryBuilder.append("FROM " );
            QueryBuilder.append(tableName);
            QueryBuilder.append("\r\n");
            mainTableAdded = true;
        }
        return this;
    }

    public Query where(String expression) {
        if(!expression.isEmpty() && mainTableAdded) {
            QueryBuilder.append("WHERE " + expression + "\r\n");
            whereExpressionAdded = true;
        }
        return this;
    }

    public Query and(String expression) {
        if (!expression.isEmpty() && whereExpressionAdded) {
            QueryBuilder.append("AND " + expression + "\r\n");
            andAdded = true;
        }
        return this;
    }

    public Query join(String tableName) {
        if (!tableName.isEmpty() && mainTableAdded) {
            QueryBuilder.append("JOIN " + tableName + "\r\n");
            joinAdded = true;
        }
        return this;
    }

    public Query on(String expression) {
        if (!expression.isEmpty() && joinAdded) {
            QueryBuilder.append("ON " + expression + "\r\n");
            // onAdded = true;
        }
        return this;
    }

    public Query like(String value) {
        if(!value.isEmpty() && whereExpressionAdded) {
            QueryBuilder.append("LIKE '"+value+"' \r\n");
        }
        return this;
    }

    public String getQuery() {
        return QueryBuilder.toString();
    }
}
