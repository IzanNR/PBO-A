package src.main.java;
import java.util.ArrayList;
import java.util.Iterator;

public class SalesItem {
    private String name;
    private int price; // in cents
    private ArrayList<Comment> comments;

    public SalesItem(String name, int price) {
        this.name = name;
        this.price = price;
        comments = new ArrayList<Comment>();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOfComments() {
        return comments.size();
    }

    // Add a new comment if valid
    public boolean addComment(String author, String text, int rating) {
        if (ratingInvalid(rating) || findCommentByAuthor(author) != null) {
            return false;
        }
        comments.add(new Comment(author, text, rating));
        return true;
    }

    // Remove comment by index if valid
    public void removeComment(int index) {
        if (index >= 0 && index < comments.size()) {
            comments.remove(index);
        }
    }

    // Increase helpful votes for a comment by index
    public void upvoteComment(int index) {
        if (index >= 0 && index < comments.size()) {
            comments.get(index).upvote();
        }
    }

    // Decrease helpful votes for a comment by index
    public void downvoteComment(int index) {
        if (index >= 0 && index < comments.size()) {
            comments.get(index).downvote();
        }
    }

    // Display item information and comments
    public void showInfo() {
        System.out.println("*** " + name + " ***");
        System.out.println("Price: " + priceString(price));
        System.out.println();
        System.out.println("Customer comments:");
        for (Comment comment : comments) {
            System.out.println("-----------------------------------");
            System.out.println(comment.getFullDetails());
        }
        System.out.println("=====================================");
    }

    // Find the most helpful comment based on votes
    public Comment findMostHelpfulComment() {
        Iterator<Comment> it = comments.iterator();
        Comment best = it.next();
        while (it.hasNext()) {
            Comment current = it.next();
            if (current.getVoteCount() > best.getVoteCount()) {
                best = current;
            }
        }
        return best;
    }

    private boolean ratingInvalid(int rating) {
        return rating < 1 || rating > 5;
    }

    private Comment findCommentByAuthor(String author) {
        for (Comment comment : comments) {
            if (comment.getAuthor().equals(author)) {
                return comment;
            }
        }
        return null;
    }

    // Format price to string
    private String priceString(int price) {
        int dollars = price / 100;
        int cents = price - (dollars * 100);
        if (cents <= 9) {
            return "$" + dollars + ".0" + cents;
        } else {
            return "$" + dollars + "." + cents;
        }
    }
}
