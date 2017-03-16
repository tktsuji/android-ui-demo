package edu.ucsb.cs.cs185.group.a185;

import java.util.ArrayList;

/**
 * Created by andrewluo on 3/16/17.
 */

public class Post {
    private String title, text;
    private ArrayList<String> tags, comments;

    public Post(){
        title = null;
        text = null;
        tags = new ArrayList<String>();
        comments = new ArrayList<String>();
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public String getTag(int index){
        return tags.get(index);
    }

    public int getTagCount(){
        return tags.size();
    }

    public void addComment(String comment){
        this.comments.add(comment);
    }

    public String getComment(int index){
        return comments.get(index);
    }

    public int getCommentCount(){
        return comments.size();
    }
}
