package com.mteasdale.treewrite.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Michael Teasdale on 9/20/2020.
 */
public class Story {
    private String title;
    private String author;
    private final LocalDateTime created = LocalDateTime.now() ;
    private LocalDateTime updated = LocalDateTime.now();
    private final StoryNode plotRoot = new StoryNode();
    private final ArrayList<Character> characterList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.plotRoot.setTitle(title);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public StoryNode getPlotRoot() {
        return plotRoot;
    }

    public ArrayList<Character> getCharacterList() {
        return characterList;
    }

    public void addCharacter(Character character) {
        characterList.add(character);
    }

    public void removeCharacter(Character character) {
        characterList.remove(character);
    }

    public Character getCharacter(int index) {
        return characterList.get(index);
    }
}
