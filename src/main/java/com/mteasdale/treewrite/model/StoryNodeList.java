package com.mteasdale.treewrite.model;

import java.util.ArrayList;

/**
 * Created by Michael Teasdale on 7/16/2020.
 */
public class StoryNodeList {
    private final ArrayList<StoryNode> childList;

    private StoryNodeList() {
        childList = new ArrayList<>();
    }

    public static StoryNodeList getNewStoryNodeList() {
        return new StoryNodeList();
    }

    public StoryNode get(int index) {
        return childList.get(index);
    }

    public void add(StoryNode node, int index) {
        childList.add(index, node);
    }

    public void add(StoryNode node) {
        childList.add(node);
    }

    public void remove(StoryNode node) {
        childList.remove(node);
    }

    public void remove(int index) {
        childList.remove(index);
    }

    public void insertBefore(StoryNode beforeNode, StoryNode node) {
        int beforeIndex = childList.indexOf(beforeNode);
        add(node, beforeIndex);
    }

    public void insertAfter(StoryNode afterNode, StoryNode node) {
        int afterIndex = childList.indexOf(afterNode);
        add(node, afterIndex + 1);
    }

    public void moveAfter(StoryNode afterNode, StoryNode node) {
        remove(node);
        insertAfter(afterNode, node);
    }

    public void moveBefore(StoryNode beforeNode, StoryNode node) {
        remove(node);
        insertBefore(beforeNode, node);
    }

}
