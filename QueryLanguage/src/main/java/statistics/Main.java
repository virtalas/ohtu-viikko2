package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

//        Matcher m = new And( new HasAtLeast(10, "goals"),
//                             new HasAtLeast(10, "assists"),
//                             new PlaysIn("PHI")
//        );
//        
        QueryBuilder query = new QueryBuilder();

//        Matcher m = query.playsIn("NYR")
//                .hasAtLeast(10, "goals")
//                .hasFewerThan(25, "assists").build();
//        Matcher m = query.playsIn("NYR").build();
        Matcher m = query.oneOf(
                query.playsIn("PHI")
                        .hasAtLeast(10, "goals")
                        .hasFewerThan(20, "assists").build(),
                query.playsIn("EDM")
                        .hasAtLeast(60, "points").build()
        ).build();
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
