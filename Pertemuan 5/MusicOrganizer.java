import java.util.ArrayList;

public class MusicOrganizer {
    private ArrayList<Track> tracks;
    private MusicPlayer player;
    private TrackReader reader;

    public MusicOrganizer() {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void listAllTracks() {
        System.out.println("Track listing: ");
        for (Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }

    public void playTrack(int index) {
        if (indexValid(index)) {
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
        }
    }

    public void stopTrack() {
        player.stop();
    }

    public int getNumberOfTracks() {
        return tracks.size();
    }

    private boolean indexValid(int index) {
        return index >= 0 && index < tracks.size();
    }

    public void readLibrary(String folder) {
        ArrayList<Track> loadedTracks = reader.readTracks(folder, ".mp3");
        for (Track track : loadedTracks) {
            addTrack(track);
        }
    }
}
