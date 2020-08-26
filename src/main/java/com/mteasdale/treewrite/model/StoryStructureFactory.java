package com.mteasdale.treewrite.model;

/**
 * Created by Michael Teasdale on 8/18/2020.
 */
public class StoryStructureFactory {
    public enum Structures {
        EMPTY,
        THREE_ACT
    }

    public static StoryStructure getStoryStructure(Structures structure) {
        StoryStructure aStructure = null;
        if (structure == Structures.EMPTY)
            aStructure = new EmptyStoryStructure();
        if (structure == Structures.THREE_ACT)
            aStructure = new ThreeActStoryStructure();
        return aStructure;
    }
}
