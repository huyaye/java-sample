package annotation.graph;/*
 *  MIT License
 *
 *  Copyright (c) 2020 Michael Pogrebinsky - Java Reflection - Master Class
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import java.util.List;

import annotation.graph.annotations.Annotations;

public class SqlQueryBuilder {
    @Annotations.Input("ids")
    private List<String> ids;

    @Annotations.Input("limit")
    private Integer limit;

    @Annotations.Input("table")
    private String tableName;

    @Annotations.Input("columns")
    private List<String> columnNames;

    public SqlQueryBuilder(List<String> ids, Integer limit, String tableName, List<String> columnNames) {
        this.ids = ids;
        this.limit = limit;
        this.tableName = tableName;
        this.columnNames = columnNames;
    }

    @Annotations.Operation("SelectBuilder")
    public String selectStatementBuilder(@Annotations.Input("table") String tableName, @Annotations.Input("columns") List<String> columnNames) {
        String columnsString = columnNames.isEmpty() ? "*" : String.join(",", columnNames);
        return String.format("SELECT %s FROM %s", columnsString, tableName);
    }

    @Annotations.Operation("WhereClauseBuilder")
    public String addWhereClause(@Annotations.DependsOn("SelectBuilder") String query, @Annotations.Input("ids") List<String> ids) {
        if (ids.isEmpty()) {
            return query;
        }
        return String.format("%s WHERE id IN (%s)", query, String.join(",", ids));
    }

    @Annotations.FinalResult
    public String addLimit(@Annotations.DependsOn("WhereClauseBuilder") String query, @Annotations.Input("limit") Integer limit) {
        if (limit == null || limit == 0) {
            return query;
        }
        if (limit < 0) {
            throw new RuntimeException("limit cannot be negative");
        }
        return String.format("%s LIMIT %d", query, limit.intValue());
    }
}
