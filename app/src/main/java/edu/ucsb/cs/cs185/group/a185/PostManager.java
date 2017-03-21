package edu.ucsb.cs.cs185.group.a185;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andrewluo on 3/16/17.
 */

public class PostManager {

    private static PostManager postManager;
    private final ArrayList<Post> posts;
    private OnUpdateListener postListener, commentListener, filteredPostListener;

    public interface OnUpdateListener {
        public void onUpdate();
    }

    public void setCommentListener(OnUpdateListener listener){
        this.commentListener = listener;
    }

    public void setPostListener(OnUpdateListener listener){
        this.postListener = listener;
    }

    public void setFilteredPostListener(OnUpdateListener listener) {this.filteredPostListener = listener;}

    private PostManager(){
        posts = new ArrayList<Post>(Arrays.asList(
                new Post("Implementing TensorFlow in a Chemistry Project", "I'm looking to add deep learning to a chemistry experiment before, but I don't have any experience using TensorFlow, let alone coding. I've gone through the tutorials, but I've hit a wall trying to add my data to it. Can anyone show me the ropes?", "calvin",  new ArrayList<String>(Arrays.asList("Computer Science", "Machine Learning", "Chemistry")), new ArrayList<String>(Arrays.asList("Hey I'm a Computer Science major who has used TensorFlow before. I'm free to meet up this Friday.")), new ArrayList<String>(Arrays.asList("tricia"))),
                new Post("Running a pendulum experiment accounting for air friction and string elasticity", "I've found a couple of papers that explain how air friction and string elasticity affect the motion of a pendulum and I'm trying to combine both of them into an experiment. Some of the math they use to derive the final equations are confusing to me. Can anyone explain them? From what I know, they use a combination of partial differential equations and Fourier series.", "andrew",  new ArrayList<String>(Arrays.asList("Physics", "pendulum", "air friction", "elasticity")), new ArrayList<String>(Arrays.asList("I'm a grad student who's currently doing research on air friction. I think I could definitely help you out.", "I am doing research on elastic materials (not for strings), but I could also explain the physics.")), new ArrayList<String>(Arrays.asList("brian", "ian")))
        ));
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
        if(filteredPostListener != null){
            filteredPostListener.onUpdate();
        }
    }

    public void updateComments(){
        commentListener.onUpdate();
    }

    public void clearPosts(){
        posts.clear();
        if (postListener != null) {
            postListener.onUpdate();
        }
        if(filteredPostListener != null){
            filteredPostListener.onUpdate();
        }
    }

    public ArrayList<Post> getPostArray(){
        return this.posts;
    }
}
