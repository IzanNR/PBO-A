package src.main.java;
public class Comment {
    private String author;
    private String text;
    private int rating;
    private int votes; // Vote balance

    // Constructor
    public Comment(String author, String text, int rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.votes = 0; // Initialize vote balance
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public int getVoteCount() {
        return votes;
    }

    // Increase votes by 1
    public void upvote() {
        votes++;
    }

    // Decrease votes by 1
    public void downvote() {
        votes--;
    }

    // Display detailed information of the comment
    public String getFullDetails() {
        return "Author: " + author + "\n" +
               "Rating: " + rating + "\n" +
               "Votes: " + votes + "\n" +
               "Comment: " + text;
    }
}
