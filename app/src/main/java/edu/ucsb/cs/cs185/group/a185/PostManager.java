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
                new Post("Running a pendulum experiment accounting for air friction and string elasticity", "I've found a couple of papers that explain how air friction and string elasticity affect the motion of a pendulum and I'm trying to combine both of them into an experiment. Some of the math they use to derive the final equations are confusing to me. Can anyone explain them? From what I know, they use a combination of partial differential equations and Fourier series.", "andrew",  new ArrayList<String>(Arrays.asList("Physics", "pendulum", "air friction", "elasticity")), new ArrayList<String>(Arrays.asList("I'm a grad student who's currently doing research on air friction. I think I could definitely help you out.", "I am doing research on elastic materials (not for strings), but I could also explain the physics.")), new ArrayList<String>(Arrays.asList("brian", "ian"))),
                new Post("Controlling a Roomba using the Robot Operating System (ROS) in Gazebo", "In the Gazebo simulator, I'm currently able to send basic commands to the Roomba and receive data back, but I can't seem to figure out a way to control it properly. Any ideas?", "jacob",  new ArrayList<String>(Arrays.asList("Computer Science", "Electrical Engineering", "ROS", "Gazebo", "Control Theory")), new ArrayList<String>(Arrays.asList("From the description of your problem, I would suggest a state-based controller like PID. I can take a look at what you're trying to implement and give you some suggestions about other control systems you can try.")), new ArrayList<String>(Arrays.asList("wesley"))),
                new Post("How do you use the Processing Real-time Unit (PRU) on a Beaglebone", "I need to use the PRU for a precise timing algorithm I built, but I'm having a hard time understanding how to translate this to the Beaglebone.", "thomas",  new ArrayList<String>(Arrays.asList("Computer Science", "Embedded Systems", "Beaglebone", "Processing Real-time Unit")), new ArrayList<String>(Arrays.asList("To talk to the PRU, you need to write assembly code. However, I do not recommend only using assembly and instead using a C wrapper so you can debug and store data conveniently. Also to even talk to the PRU, you need to use a device-tree overlay.", "I actually made a device-tree overlay for the Beaglebone PRU. I can show you the bash script and file I used!")), new ArrayList<String>(Arrays.asList("calvin", "justin")))
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
