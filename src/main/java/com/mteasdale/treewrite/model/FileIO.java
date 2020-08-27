package com.mteasdale.treewrite.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.TreeItem;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Michael Teasdale on 8/25/2020.
 */
public class FileIO {

    private static final Gson GSON = new Gson();
    private static Path filePath;

    public Path getFilePath() {
        return filePath;
    }

    public TreeItem<StoryNode> open(Path path) {
        TreeItem<StoryNode> root = null;
        if (path != null) {
            filePath = path;
            try {
                String result = Files.readString(filePath);
                root = getStoryNodeTree(result);
            } catch (IOException ioe) {
                System.out.println("File read error.");
            }
        }
        return root;
    }

    public TreeItem<StoryNode> openResource(String resource) {
        TreeItem<StoryNode> root = null;
        if (resource != null) {
            InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(resource));
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                String result = bufferedReader.lines().collect(Collectors.joining("\n"));
                root = getStoryNodeTree(result);
            } catch (IOException ioe) {
                System.out.println("Error reading resource " + resource);
                ioe.printStackTrace();
            }
        }
        return root;
    }

    private TreeItem<StoryNode> getStoryNodeTree(String jsonString) {
        Type listType = new TypeToken<ArrayList<StoryNode>>() {
        }.getType();
        ArrayList<StoryNode> storyNodeList = GSON.fromJson(jsonString, listType);
        HashMap<Integer, TreeItem<StoryNode>> treeItemMap = new HashMap<>();
        for (StoryNode s : storyNodeList) treeItemMap.put(s.getId(), new TreeItem<>(s));
        TreeItem<StoryNode> root = null;
        for (Map.Entry<Integer, TreeItem<StoryNode>> current : treeItemMap.entrySet()) {
            TreeItem<StoryNode> treeItem = current.getValue();
            if (treeItem.getValue().getParent() == null ||
                    treeItem.getValue().getParent() == 0) root = treeItem;
            else treeItemMap.get(treeItem.getValue().getParent()).getChildren().add(treeItem);
        }
        return root;
    }

    /**
     * Saves the story tree to a json file.
     * @param path If path is null, save falls back to the current value of filePath.
     * @param root The root of the storyTreeView.
     */
    public void save(Path path, TreeItem<StoryNode> root) {
        if (path != null)
            filePath = path;
        String output = GSON.toJson(getStoryNodeList(root));
        try {
            Files.writeString(filePath, output, StandardOpenOption.WRITE, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ioe) {
            System.out.println("File save error.");
        }
    }

    private ArrayList<StoryNode> getStoryNodeList(TreeItem<StoryNode> root) {
        ArrayList<StoryNode> storyNodeArrayList = new ArrayList<>();
        treeToList(storyNodeArrayList, root);
        return storyNodeArrayList;
    }

    // Recursive tree traversal. Hard on the stack, but it should be a shallow list.
    private void treeToList(ArrayList<StoryNode> list, TreeItem<StoryNode> item) {
        if (item != null) {
            StoryNode storyNode = item.getValue();
            list.add(storyNode);
            storyNode.setId(list.size());
            if (item.getParent() != null)
                storyNode.setParent(item.getParent().getValue().getId());
            if (item.getChildren() != null) {
                for (TreeItem<StoryNode> child : item.getChildren())
                    treeToList(list, child);
            }
        }
    }
}
