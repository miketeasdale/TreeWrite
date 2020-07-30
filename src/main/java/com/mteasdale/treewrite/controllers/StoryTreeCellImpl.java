package com.mteasdale.treewrite.controllers;

import com.mteasdale.treewrite.model.StoryNode;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOError;
import java.util.Objects;

/**
 * Created by Michael Teasdale on 7/23/2020.
 */
public class StoryTreeCellImpl extends TreeCell<StoryNode> {

    private static final DataFormat JAVA_FORMAT = new DataFormat("application/x-java-serialized-object");

    private static final String PITCHICONPATH = "/images/study.png";
    private static final String PLOTICONPATH = "/images/analytic.png";
    private static final String SCENEICONPATH = "/images/clapperboard.png";
    private static final String DROP_HINT_STYLE = "-fx-border-color: #eea82f; -fx-border-width: 0 0 2 0; -fx-padding: 3 3 1 3";
    private final ContextMenu storyItemMenu = new ContextMenu();
    private static ImageView PITCH_ICON;
    private static ImageView PLOT_ICON;
    private static ImageView SCENE_ICON;
    private static TreeCell<StoryNode> dropZone;
    private static TreeItem<StoryNode> draggedItem;

    private final static Logger LOG = LoggerFactory.getLogger(StoryTreeCellImpl.class);

    public StoryTreeCellImpl() {
        //Item icons
        if (PITCH_ICON == null) PITCH_ICON = getImageView(PITCHICONPATH);
        if (PLOT_ICON == null) PLOT_ICON = getImageView(PLOTICONPATH);
        if (SCENE_ICON == null) SCENE_ICON = getImageView(SCENEICONPATH);

        //Context menu.
        MenuItem addMenuItem = new MenuItem("Add Child");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        storyItemMenu.getItems().addAll(addMenuItem, deleteMenuItem);
        addMenuItem.setOnAction((ActionEvent t) -> {
            StoryNode storyNode = new StoryNode();
            TreeItem<StoryNode> treeItem =
                    new TreeItem<>(storyNode);
            getTreeItem().getChildren().add(treeItem);
        });
        deleteMenuItem.setOnAction((ActionEvent t) -> {

        });

        // Drag and drop setup.
        setOnDragDetected(this::dragDetected);
        setOnDragOver(this::dragOver);
        setOnDragDropped(this::drop);
        setOnDragDone((DragEvent event) -> clearDropLocation());
    }

    private ImageView getImageView(String path) {
        ImageView iv = null;
        try {
            iv = new ImageView(
                    new Image(getClass().getResourceAsStream(path), 32, 0, true, true)
            );
        }
        catch (IOError ioe) {
            System.out.println("Unable to load image view " + path);
            ioe.printStackTrace();
        }
        return iv;
    }

    @Override
    protected void updateItem(StoryNode storyNode, boolean empty) {
        super.updateItem(storyNode, empty);
        if (storyNode == null || empty) {
            setGraphic(null);
            setText(null);
        } else {
            ImageView iv1 = null;
            if (storyNode.getClassifier().equals(StoryNode.classifiers[1])) iv1 = PITCH_ICON;
            if (storyNode.getClassifier().equals(StoryNode.classifiers[2])) iv1 = PLOT_ICON;
            if (storyNode.getClassifier().equals(StoryNode.classifiers[3])) iv1 = SCENE_ICON;
            setGraphic(iv1);
            setText(storyNode.getTitle());
            setContextMenu(storyItemMenu);
        }
    }

    private void dragDetected(MouseEvent event) {
        LOG.info("Starting drag.");
        draggedItem = getTreeItem();

        // root can't be dragged
        if (draggedItem.getParent() == null) return;
        Dragboard db = startDragAndDrop(TransferMode.MOVE);

        ClipboardContent content = new ClipboardContent();
        content.put(JAVA_FORMAT, draggedItem.getValue());
        db.setContent(content);
        db.setDragView(snapshot(null, null));
        event.consume();
    }

    private void dragOver(DragEvent event) {
        if (!event.getDragboard().hasContent(JAVA_FORMAT)) return;
        TreeItem<StoryNode> thisItem = getTreeItem();
        //LOG.info("Dragged over: {}", thisItem.getValue().getTitle());

        // can't drop on itself
        if (draggedItem == null || thisItem == null || thisItem == draggedItem) return;
        //LOG.info("Target is not itself.");
        // ignore if this is the root
        if (draggedItem.getParent() == null) {
            clearDropLocation();
            return;
        }
        //LOG.info("Target is not the root.");

        event.acceptTransferModes(TransferMode.MOVE);
        if (!Objects.equals(dropZone, this)) {
            clearDropLocation();
            dropZone = this;
            dropZone.setStyle(DROP_HINT_STYLE);
        }
    }

    private void drop(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (!db.hasContent(JAVA_FORMAT)) return;

        TreeItem<StoryNode> thisItem = getTreeItem();
        TreeItem<StoryNode> droppedItemParent = draggedItem.getParent();
        LOG.info("Dropping onto {}", thisItem.getValue().getTitle());

        // remove from previous location
        droppedItemParent.getChildren().remove(draggedItem);

        // dropping on parent node makes it the first child
        if (Objects.equals(droppedItemParent, thisItem)) {
            thisItem.getChildren().add(0, draggedItem);
            getTreeView().getSelectionModel().select(draggedItem);
        }
        else {
            // add to new location
            int indexInParent = thisItem.getParent().getChildren().indexOf(thisItem);
            thisItem.getParent().getChildren().add(indexInParent + 1, draggedItem);
        }
        getTreeView().getSelectionModel().select(draggedItem);
        event.setDropCompleted(true);
    }

    private void clearDropLocation() {
        if (dropZone != null) dropZone.setStyle("");
    }

}
