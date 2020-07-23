package com.mteasdale.treewrite.model;

import com.mteasdale.treewrite.controllers.StoryNodeCellFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOError;

/**
 * Created by Michael Teasdale on 7/22/2020.
 */
public class StoryNodeImageViews {
    private static ImageView PITCH_ICON;
    private static ImageView PLOT_ICON;
    private static ImageView SCENE_ICON;

    public static ImageView getPitchIcon() {
        if (PITCH_ICON == null) {
            PITCH_ICON = new StoryNodeImageViews().getImageView("/images/study.png");
        }
        return PITCH_ICON;
    }

    public static ImageView getPlotIcon() {
        if (PLOT_ICON == null) {
            PLOT_ICON = new StoryNodeImageViews().getImageView("/images/analytic.png");
        }
        return PLOT_ICON;
    }

    public static ImageView getSceneIcon() {
        if (SCENE_ICON == null) {
            SCENE_ICON = new StoryNodeImageViews().getImageView("/images/clapperboard.png");
        }
        return SCENE_ICON;
    }

    private ImageView getImageView(String path) {
        ImageView iv = null;
        try {
            iv = new ImageView(
                    new Image(StoryNodeImageViews.class.getResourceAsStream(path), 40, 0, true, true)
            );
        }
        catch (IOError ioe) {
            System.out.println("Unable to load image view " + path);
            ioe.printStackTrace();
        }
        return iv;
    }
}
