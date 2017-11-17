package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String urlStudent = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String urlCourse = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String urlStats = "https://studies.cs.helsinki.fi/ohtustats/stats";

        String bodyText = Request.Get(urlStudent).execute().returnContent().asString();
        String courseBody = Request.Get(urlCourse).execute().returnContent().asString();
        String statsBody = Request.Get(urlStats).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course course = mapper.fromJson(courseBody, Course.class);
        
        JsonParser parser = new JsonParser();
        JsonObject stats = parser.parse(statsBody).getAsJsonObject();
        int totalSubmissions = 0;
        int totalExercises = 0;
        
        for (String key : stats.keySet()) {
            totalSubmissions += stats.getAsJsonObject(key).get("students").getAsInt();
            totalExercises += stats.getAsJsonObject(key).get("exercise_total").getAsInt();
        }
        
        System.out.println("Kurssi: "+course.getName()+", "+course.getTerm()+"\n");
        System.out.println("Opiskelijanumero: "+studentNr+"\n");
        int exerciseCount = 0;
        int hoursCount = 0;
        
        for (Submission submission : subs) {
            submission.max = course.getExercises().get(submission.getWeek()-1);
            exerciseCount += submission.getExercises().size();
            hoursCount += submission.getHours();
            System.out.println(submission);
        }
        
        System.out.println("\nYhteensä: "+exerciseCount+" tehtävää "+hoursCount+" tuntia");
        System.out.println("\nkurssilla yhteensä "+totalSubmissions+
                " palautusta, palautettuja tehtäviä "+totalExercises+" kpl");
    }
}