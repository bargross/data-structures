package com.dictionary.Util;

public enum KeywordSelector {
    SELECT("select \r\n"),
    FROM("from "),
    WHERE("where "),
    AND("and "),
    JOIN("join "),
    ON("on "),
    LIKE("like ");


    String sqlKeyword;

    KeywordSelector(String sqlKeyword) {
        this.sqlKeyword = sqlKeyword;
    }

    public String getValue() {
        return this.sqlKeyword.toUpperCase();
    }

    public KeywordSelector replace(String oldV, String newV) {
        this.sqlKeyword.replace(oldV, newV);
        return this;
    }
}
