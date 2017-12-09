package statistics.matcher;

import statistics.Player;

public class Or extends Matcher {
    
    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        boolean match = false;
        for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                match = true;
            }
        }

        return match;
    }
}
