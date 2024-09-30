public class Track {
    private String artist;
    private String title;
    private String filename;

    public Track(String artist, String title, String filename) {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }

    public String getDetails() {
        return artist + " - " + title;
    }
}
