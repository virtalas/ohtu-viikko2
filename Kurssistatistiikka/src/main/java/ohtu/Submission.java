package ohtu;

import java.util.List;

public class Submission {
    private int week;
    private int hours;
    private List<Integer> exercises;
    public int max = 0;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return "viikko "+week+":\n  "+"tehtyjä tehtäviä yhteensä: "+
                exercises.size()+" (maksimi "+max+"), aikaa kului "+hours+
                " tuntia, tehdyt tehtävät: "+exercises.toString();
    }
    
}