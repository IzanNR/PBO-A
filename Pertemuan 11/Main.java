public class Main {
    public static void main(String[] args) {
        Network network = new Network();

        User alice = network.addUser("Alice");
        User bob = network.addUser("Bob");
        
        alice.addPost("Hello, this is Alice's first post!");
        bob.addPost("Hi everyone, Bob here!");

        network.addPost(alice.getPosts().get(0));
        network.addPost(bob.getPosts().get(0));

        bob.likePost(alice.getPosts().get(0));
        alice.likePost(bob.getPosts().get(0));

        network.showAllPosts();
    }    
}
