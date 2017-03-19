package edu.ucsb.cs.cs185.group.a185;

import java.util.ArrayList;

/**
 * Created by andrewluo on 3/16/17.
 */

public class PostManager {

    private static PostManager postManager;
    private final ArrayList<Post> posts;
    private OnUpdateListener postListener, commentListener;

    public interface OnUpdateListener {
        public void onUpdate();
    }

    public void setCommentListener(OnUpdateListener listener){
        this.commentListener = listener;
    }

    public void setPostListener(OnUpdateListener listener){
        this.postListener = listener;
    }

    private PostManager(){
        posts = new ArrayList<Post>();
        //posts = new ArrayList<Post>(
        //        Post("title1","content1","wells",new ArrayList<String>(),new ArrayList<String>()),
        //        Post("title2","content2","wells",),
        //        Post("title3","content3","andrew",),
        //        Post("title4","content4","calvin",),
        //        Post("title5","content5","andrew",));
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
        postListener.onUpdate();
    }

    public void updateComments(){
        commentListener.onUpdate();
    }

    public void clearPosts(){
        posts.clear();
        if (postListener != null) {
            postListener.onUpdate();
        }
    }

    public ArrayList<Post> getPostArray(){
        return this.posts;
    }
}
