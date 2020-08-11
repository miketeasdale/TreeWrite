package com.mteasdale.treewrite.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael Teasdale on 8/10/2020.
 */
public class StoryStructure {
    private static HashMap<String, List<String>> classifierMap;
    private static final String path = "/ActStructure.txt";

    public StoryStructure() {
        if (classifierMap.isEmpty()) {
            URL url = getClass().getResource(path);
            if (url != null) {
                try {
                    String result = (String) url.getContent();
                    Type mapType = new TypeToken<HashMap<String, List<String>>>() {}.getType();
                    classifierMap = new Gson().fromJson(result, mapType);
                } catch (IOException ioe) {
                    System.out.println("File read error: " + url.toString());
                }
            }
        }
    }

    public HashMap<String, List<String>> getMap() {
        return classifierMap;
    }

    public ArrayList<String> getClassifiers() {
        return new ArrayList<>(classifierMap.keySet());
    }

    public ArrayList<String> getSubclassifiers(String classifier) {
        return new ArrayList<>(classifierMap.get(classifier));
    }
}
