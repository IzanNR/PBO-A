public class Main {
    public static void main(String[] args) {
        MusicOrganizer organizer = new MusicOrganizer();
        organizer.listAllTracks();
        organizer.playTrack(0);
        organizer.playTrack(2);
        organizer.stopTrack();
        organizer.playTrack(5);
    }
}
