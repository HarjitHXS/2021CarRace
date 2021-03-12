public class BoardEntry {
    private Car car;
    private int time;

    public BoardEntry(Car car, int time) {
        this.car = car;
        this.time = time;
    }


    public Car getRacer() {
        return car;
    }

    public int getTime() {
        return time;
    }
}
