package src.test.java;

import org.junit.jupiter.api.Test;

import src.main.java.Comment;
import src.main.java.SalesItem;

import static org.junit.jupiter.api.Assertions.*;

public class SalesItemTest {

    @Test
    public void testAddCommentSuccess() {
        SalesItem item = new SalesItem("Laptop", 150000); // Create a SalesItem
        boolean result = item.addComment("Alice", "Great product!", 5);
        assertTrue(result); // Check if the comment is added successfully
        assertEquals(1, item.getNumberOfComments()); // Verify the comment count
    }

    @Test
    public void testAddCommentInvalidRating() {
        SalesItem item = new SalesItem("Phone", 80000);
        boolean result = item.addComment("Bob", "Not good!", 6); // Invalid rating
        assertFalse(result); // Comment should not be added
        assertEquals(0, item.getNumberOfComments()); // No comment should be present
    }

    @Test
    public void testAddCommentDuplicateAuthor() {
        SalesItem item = new SalesItem("Tablet", 120000);
        item.addComment("Charlie", "Pretty decent.", 4);
        boolean result = item.addComment("Charlie", "Changed my mind!", 3); // Same author
        assertFalse(result); // Should reject the second comment
        assertEquals(1, item.getNumberOfComments()); // Only one comment should be present
    }

    @Test
    public void testRemoveCommentValidIndex() {
        SalesItem item = new SalesItem("Headphones", 40000);
        item.addComment("Dana", "Excellent sound!", 5);
        assertEquals(1, item.getNumberOfComments()); // Check initial count
        item.removeComment(0); // Remove the comment
        assertEquals(0, item.getNumberOfComments()); // Ensure it's removed
    }

    @Test
    public void testRemoveCommentInvalidIndex() {
        SalesItem item = new SalesItem("Monitor", 95000);
        item.addComment("Eve", "Good display.", 4);
        assertEquals(1, item.getNumberOfComments()); // Initial count
        item.removeComment(1); // Invalid index
        assertEquals(1, item.getNumberOfComments()); // Count should remain the same
    }

    @Test
    public void testFindMostHelpfulComment() {
        SalesItem item = new SalesItem("Keyboard", 30000);
        item.addComment("Frank", "Nice feel.", 4);
        item.addComment("Grace", "Too noisy for me.", 3);
        item.upvoteComment(0); // Upvote first comment
        item.upvoteComment(0); // Upvote again to make it most helpful
        Comment mostHelpful = item.findMostHelpfulComment();
        assertEquals("Frank", mostHelpful.getAuthor()); // Verify the most helpful comment
    }
}
