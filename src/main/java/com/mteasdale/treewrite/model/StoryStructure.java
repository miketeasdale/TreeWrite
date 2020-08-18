package com.mteasdale.treewrite.model;

import javafx.scene.control.TreeItem;

/**
 * Created by Michael Teasdale on 8/10/2020.
 */
public class StoryStructure {
    TreeItem<StoryNode> root;

    public static final String[] THREE_ACT_CLASSIFIERS = {"None", "Pitch", "Act", "Plot Point", "Scene", "Other"};

    public TreeItem<StoryNode> get3ActStoryStructure() {
        TreeItem<StoryNode> act1 = new TreeItem<>(new StoryNode("Act", "Act 1"));
        act1.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","Introduction")));
        act1.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","Inciting Incident")));
        act1.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","Setup")));
        act1.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","TP1(10%): Opportunity")));

        TreeItem<StoryNode> act2 = new TreeItem<>(new StoryNode("Act", "Act 2"));
        act2.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","New Situation")));
        act2.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","TP2(25%): Change of Plans")));
        act2.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","Progress")));
        act2.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","TP3(50%): Point of No Return")));
        act2.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","Complications and Higher Stakes")));
        act2.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","TP4(75%): Major Setback")));

        TreeItem<StoryNode> act3 = new TreeItem<>(new StoryNode("Act", "Act 1"));
        act3.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","Final Push")));
        act3.getChildren().add(new TreeItem<>(new StoryNode("Plot Point","TP5(90-99%): Climax")));
        act3.getChildren().add(new TreeItem<>(new StoryNode("Plot Point", "Denouement")));

        root = new TreeItem<>(new StoryNode("Pitch", "None"));
        root.getChildren().add(act1);
        root.getChildren().add(act2);
        root.getChildren().add(act3);
        return root;
    }
}
