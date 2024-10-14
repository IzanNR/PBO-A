import java.util.Random;
import java.util.HashMap;

public class Responder {
    private Random answerNum;
    private String[] defaultAnswers;
    private HashMap<String, String> keywordResponses;

    public Responder() {
        initializeDefaultAnswers();
        initializeKeywordResponses();
    }

    public String generateResponse(String input) {
        answerNum = new Random();
        boolean match = false;

        String[] splitInput = input.split("[^a-zA-Z]+");
        for (String word : splitInput) {
            if (keywordResponses.containsKey(word)) {
                match = true;
                return keywordResponses.get(word);
            }
        }

        if (!match) {
            int randomIndex = answerNum.nextInt(defaultAnswers.length);
            return defaultAnswers[randomIndex];
        }

        return "I'm not sure how to respond to that.";
    }

    private void initializeDefaultAnswers() {
        defaultAnswers = new String[]{
            "Can you please provide more details about the issue?",
            "Have you tried restarting the device?",
            "Let me check that for you.",
            "That sounds tricky. Can you explain a bit more?",
            "I'm here to help! Tell me more about the problem.",
            "Have you checked if all the cables are connected properly?",
            "I'll need more information to assist you better."
        };
    }

    private void initializeKeywordResponses() {
        keywordResponses = new HashMap<>();

        keywordResponses.put("java", "Java issues can often be solved by checking for proper installations or environment variables.");
        keywordResponses.put("computer", "Is the computer showing any error messages?");
        keywordResponses.put("ios", "For iOS, try checking the software updates or restarting the device.");
        keywordResponses.put("windows", "Have you tried running a system diagnostic on your Windows machine?");
        keywordResponses.put("mac", "Make sure your macOS is up to date. Have you tried rebooting?");
        keywordResponses.put("crashed", "It sounds like your system crashed. Could you describe what happened right before?");
        keywordResponses.put("print", "Check if the printer is connected and the drivers are up to date.");
        keywordResponses.put("linux", "What Linux distribution are you using? Try checking the system logs for more details.");
        keywordResponses.put("android", "For Android, ensure the latest OS version is installed or try restarting the phone.");
        keywordResponses.put("problem", "I'm sorry you're experiencing a problem. Could you give more details?");
        keywordResponses.put("help", "Help is on the way... not really, but I'm here!");
        keywordResponses.put("robot", "Beep boop... I'm a support bot, not a robot overlord... yet.");
        keywordResponses.put("error", "Did something go boom? Tell me more, I love a good mystery!");
        keywordResponses.put("slow", "Slow? Maybe your device needs a coffee. Try restarting!");
        keywordResponses.put("update", "Updates are like veggies: good for you, but hard to love.");
        keywordResponses.put("internet", "Is your internet powered by a potato? Check the connection!");
        keywordResponses.put("game", "Are you losing? Maybe try blaming lag. Works every time!");
        keywordResponses.put("password", "Forgot your password? Try 'password123'... Just kidding, don't do that!");
        keywordResponses.put("coffee", "Take a break! Get a coffee, it fixes everything... almost.");
        keywordResponses.put("fun", "You want fun? Well, technical support is serious business... but I'll try!");
    }
}
