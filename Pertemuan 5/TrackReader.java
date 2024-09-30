import java.util.ArrayList;

public class TrackReader {

    public ArrayList<Track> readTracks(String folder, String extension) {
        ArrayList<Track> tracks = new ArrayList<>();

        tracks.add(new Track("Eve", "Nonsense Bungaku", folder + "/nonsense_bungaku" + extension));
        tracks.add(new Track("Arekun and Yuika", "Anone", folder + "/anone" + extension));
        tracks.add(new Track("Official HIGE DANdism", "Pretender", folder + "/pretender" + extension));
        tracks.add(new Track("RADWIMPS", "Your Name.", folder + "/your_name" + extension));
        tracks.add(new Track("RADWIMPS", "Nandemonaiya", folder + "/nandemonaiya" + extension));
        tracks.add(new Track("Yoh Kamiyama", "Laundry", folder + "/laundry" + extension));

        return tracks;
    }
}
