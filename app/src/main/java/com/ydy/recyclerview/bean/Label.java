package com.ydy.recyclerview.bean;

/**
 * @author ydy
 */
public class Label {

    private String text;
    private Boolean isSelected;

    public Label() {

    }

    public Label(String text, Boolean isSelected) {
        this.text = text;
        this.isSelected = isSelected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
