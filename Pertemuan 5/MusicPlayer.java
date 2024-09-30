public class MusicPlayer {
    private boolean isPlaying;

    public MusicPlayer() {
        isPlaying = false;
    }

    public void startPlaying(String filename) {
        System.out.println("Playing file: " + filename);
        isPlaying = true;
    }

    public void stop() {
        if(isPlaying) {
            System.out.println("Music stopped.");
            isPlaying = false;
        } else {
            System.out.println("No music is playing.");
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
