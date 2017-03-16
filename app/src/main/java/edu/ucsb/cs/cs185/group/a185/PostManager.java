package edu.ucsb.cs.cs185.group.a185;

import java.util.ArrayList;

/**
 * Created by andrewluo on 3/16/17.
 */

public class PostManager {

    private static PostManager postManager;
    private final ArrayList<Post> posts;
    private OnUpdateListener listener;

    public interface OnUpdateListener {
        public void onUpdate();
    }

    public void setUpdateListener(OnUpdateListener listener){
        this.listener = listener;
    }

    private PostManager(){
        posts = new ArrayList<Post>();
    }

    public static PostManager getInstance(){
        if(postManager == null){
            postManager = new PostManager();
        }
        return postManager;
    }

    public int getCount(){
        return posts.size();
    }

    public Post getPost(int index){
        return posts.get(index);
    }

    public void addPost(Post p){
        posts.add(p);
        listener.onUpdate();
    }

    public void clearPosts(){
        posts.clear();
        if (listener != null) {
            listener.onUpdate();
        }
    }
}
