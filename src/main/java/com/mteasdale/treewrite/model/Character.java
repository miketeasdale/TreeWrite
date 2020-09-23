package com.mteasdale.treewrite.model;

import java.util.ArrayList;

/**
 * Created by Michael Teasdale on 9/20/2020.
 */
public class Character {
    private String name;
    private String aliases;
    private String tags;
    // First aspect is always high concept. Second is always character trouble.
    private final ArrayList<String> aspects = new ArrayList<>();
    private String notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addAspect(String aspect) {
        aspects.add(aspect);
    }

    public void removeAspect(String aspect) {
        aspects.remove(aspect);
    }

    public String getAspect(int index) {
        return aspects.get(index);
    }
}
