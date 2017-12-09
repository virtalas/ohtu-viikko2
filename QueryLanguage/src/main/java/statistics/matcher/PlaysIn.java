
package statistics.matcher;

import statistics.Player;

public class PlaysIn extends Matcher {
    private String team;
    private Matcher matcher;

    public PlaysIn(Matcher matcher, String team) {
        this.team = team;
        this.matcher = matcher;
    }        
    
    @Override
    public boolean matches(Player p) {
        return this.matcher.matches(p) && p.getTeam().contains(team);
    }
    
}
