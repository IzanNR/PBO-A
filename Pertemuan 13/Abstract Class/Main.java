public class Main {
    public static void main(String[] args) {
        // Polimorfisme
        MakhlukHidup manusia = new Manusia();
        MakhlukHidup hewan = new Hewan();
        MakhlukHidup tumbuhan = new Tumbuhan();

        // Memanggil metode
        System.out.println("Manusia:");
        manusia.info();
        manusia.makan();
        manusia.bernafas();

        System.out.println("\nHewan:");
        hewan.info();
        hewan.makan();
        hewan.bernafas();

        System.out.println("\nTumbuhan:");
        tumbuhan.info();
        tumbuhan.makan();
        tumbuhan.bernafas();
    }
}
