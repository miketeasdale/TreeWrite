package com.mteasdale.treewrite.model;

import javafx.scene.control.TreeItem;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Michael Teasdale on 8/10/2020.
 */
public class ThreeActStoryStructure implements StoryStructure {

    private static final FileIO FILE_IO = new FileIO();
    private static final String STRUCTUREFILE = "/structures/ThreeAct.twr";

    public static final HashMap<String, String[]> CLASSIFIER_MAP = new HashMap<>();

    public ThreeActStoryStructure() {
        if (CLASSIFIER_MAP.isEmpty()) {
            CLASSIFIER_MAP.put("Pitch", new String[]{"None"});
            CLASSIFIER_MAP.put("Act", new String[]{"Act 1", "Act 2", "Act 3"});
            CLASSIFIER_MAP.put("Scene", new String[]{"Action", "Reaction"});
            CLASSIFIER_MAP.put("Act 1", new String[]{"Introduction", "Inciting Incident", "Setup",
                    "TP1(10%): Opportunity"});
            CLASSIFIER_MAP.put("Act 2", new String[]{"New Situation", "TP2(25%): Change of Plans", "Progress",
                    "TP3(50%): Point of No Return", "Complications and Higher Stakes", "TP4(75%): Major Setback"});
            CLASSIFIER_MAP.put("Act 3", new String[]{"Final Push", "TP5(90-99%): Climax", "Denouement"});
        }
    }

    public TreeItem<StoryNode> getStoryTree() {
        TreeItem<StoryNode> root = null;
        try {
            root = FILE_IO.openResource(STRUCTUREFILE);
        } catch (Exception use) {
            System.out.println("Cannot read 3 Act Structure.");
            use.printStackTrace();
        }
        return root;
    }
}
