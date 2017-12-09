package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan extends Matcher {
    
    private int value;
    private String fieldName;
    private Matcher matcher;

    public HasFewerThan(Matcher matcher, int value, String category) {
        this.value = value;
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Player p) {
        try {                                    
            Method method = p.getClass().getMethod(fieldName);
            int playersValue = (Integer)method.invoke(p);
            return this.matcher.matches(p) && playersValue<value;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }       
        
    }    
}
