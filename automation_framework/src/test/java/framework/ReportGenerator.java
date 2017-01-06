package framework;

import com.google.gson.Gson;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReportGenerator {
    static void buildMasterthoughtReport() {
        List<String> jsonFiles = generateJSONFileList();

        String jenkinsBasePath = "";
        String buildNumber = "1";
        String projectName = "cucumber-jvm";
        boolean skippedFails = true;
        boolean pendingFails = false;
        boolean undefinedFails = true;
        File reportOutputDirectory = new File("target");

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setStatusFlags(skippedFails, pendingFails, undefinedFails);
        configuration.setParallelTesting(false);
        configuration.setJenkinsBasePath(jenkinsBasePath);
        configuration.setRunWithJenkins(false);
        configuration.setBuildNumber(buildNumber);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    private static List<String> generateJSONFileList() {
        List<String> jsonFiles = new ArrayList<>();
        //add feature created jsonfiles

        Pattern pattern = Pattern.compile("target\\?json-report-(\\w+)\\.json");
        Iterator<String> it = jsonFiles.iterator();
        while(it.hasNext()){
            String reportFile = it.next();
            File f = new File(reportFile);
            if(!f.isFile()){
                it.remove();
            }
            else{
                Matcher m = pattern.matcher(reportFile);
                if(m.matches()){
                    addBrowserNameToTestResults(f, m.group(1));
                }
            }
        }
        return jsonFiles;
    }

    private static void addBrowserNameToTestResults(File jsonFile, String browserType){
        Gson gson = new Gson();
        try{
            BufferedReader br = new BufferedReader(new FileReader(jsonFile));
            List json = gson.fromJson(br, List.class);

            for(Object feature : json) {
                //noinspection unchecked
                Map<String, Object> feature_name = (Map<String, Object>) feature;
                feature_name.put("name", feature_name.get("name") + " - " + browserType);
            }
            String new_json = gson.toJson(json);
            br.close();
            FileWriter writer = new FileWriter(jsonFile);
            writer.write(new_json);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
