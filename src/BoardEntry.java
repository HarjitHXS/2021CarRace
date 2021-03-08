public class BoardEntry {
    private Racer racer;
    private int time;

    public BoardEntry(Racer racer, int time) {
        this.racer = racer;
        this.time = time;
    }

    public Racer getRacer() {
        return racer;
    }

    public int getTime() {
        return time;
    }
}
