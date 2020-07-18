package com.mteasdale.treewrite.model;

/**
 * Created by Michael Teasdale on 7/17/2020.
 */
public class PlotNodeSubclassifier implements NodeSubclassifier {
    public enum SubClassifiers {
        INTRO,
        INCITING_ACTION,
        SETUP,
        OPPORTUNITY,
        NEW_SITUATION,
        CHANGE_OF_PLANS,
        PROGRESS,
        POINT_OF_NO_RETURN,
        COMPLICATIONS,
        MAJOR_SETBACK,
        FINAL_PUSH,
        CLIMAX,
        DENOUMENT
    }
}
