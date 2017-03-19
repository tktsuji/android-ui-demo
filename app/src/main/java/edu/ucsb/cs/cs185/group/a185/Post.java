package edu.ucsb.cs.cs185.group.a185;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by andrewluo on 3/16/17.
 */

public class Post {
    private String title, text, user;
    private ArrayList<String> tags, comments;

    public Post(String username){
        title = "";
        text = "";
        user = username;
        tags = new ArrayList<String>();
        comments = new ArrayList<String>();
    }

    public Post(String title, String text, String user, ArrayList<String> tags, ArrayList<String> comments) {
        this.title = title;
        this.text = text;
        this.user = user;
        this.tags = tags;
        this.comments = comments;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser(){
        return this.user;
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

    public boolean hasTag(String tag){
        for(int i=0; i<tags.size();i++){
            if(tags.get(i).equals(tag)){
                return true;
            }
        }
        return false;
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
