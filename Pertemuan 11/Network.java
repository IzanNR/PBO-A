import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<User> users;
    private List<Post> posts;

    public Network(){
        users = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public User addUser(String username){
        User user = new User(username);
        users.add(user);
        System.out.println("User added: " + username);
        return user;
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public void showAllPosts(){
        System.out.println("\nAll Posts:");

        for(Post post : posts){
            System.out.println(post.getAuthor().getUsername() + " posted: " +
            post.getContent() + " - Likes: " + post.getLikeCount());
        }
    }
}
