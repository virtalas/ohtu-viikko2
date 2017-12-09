package statistics;

import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

class QueryBuilder {
    
    private Matcher matcher;

    public QueryBuilder() {
        this.matcher = new Matcher();
    }
    
    public Matcher build() {
        Matcher builtMatcher = this.matcher;
        this.matcher = new Matcher();
        return builtMatcher;
    }

    QueryBuilder playsIn(String nyr) {
        this.matcher = new PlaysIn(this.matcher, nyr);
        return this;
    }
    
    QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new HasAtLeast(this.matcher, value, category);
        return this;
    }
    
    QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new HasFewerThan(this.matcher, value, category);
        return this;
    }

    QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.matcher = new Or(m1, m2);
        return this;
    }
}
