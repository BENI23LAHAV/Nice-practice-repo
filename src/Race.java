import java.util.*;

public class Race {


    public static void main(String[] args) {
        RaceMonitor rm = new RaceMonitor();
        List<RacerThread> racers = new ArrayList<>();
        final int TRACK_LENGTH = 100;
        final int RACERS_NUM = 5;
        for (int i = 1; i <= RACERS_NUM; i++) {
            racers.add(new RacerThread("Racer " + i, TRACK_LENGTH));
        }
        rm.start();
        for (RacerThread rt : racers) {
            rt.start();
        }
        for (RacerThread rt : racers) {
            try {
                rt.join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println();
        if(RaceResults.racers.size() == RACERS_NUM){
            RaceMonitor.finishTheRace();
            System.out.println(RaceResults.print());
        }
    }
}

class RaceMonitor extends Thread {

    static volatile boolean allowMove;
    static boolean raceIsActive;

    RaceMonitor() {
        raceIsActive = true;
    }

    synchronized static boolean moveIsAllowing(){
        return allowMove;
    }
    @Override
    public void run() {

        while (raceIsActive) {
            synchronized (RaceMonitor.class) {
                try {

                    Thread.sleep(1000);
                    allowMove = !allowMove;
                    RaceMonitor.class.notifyAll();


                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    static void finishTheRace() {
        raceIsActive = false;
    }
}

class RacerThread extends Thread {
    String name;
    int trackLength;

    public RacerThread(String name, int trackLength) {
        this.name = name;
        this.trackLength = trackLength;
    }

    @Override
    public void run() {

        for (int i = 0; i < this.trackLength; i += 10) {
            if(i > 0){
                System.out.println("Car " + this.name + " progressed to " + i + "/100 km");

            }
            try {
                Thread.sleep(new Random().nextInt(1000) + 500);
                synchronized (RaceMonitor.class) {
                    while (!RaceMonitor.moveIsAllowing()) {
                        RaceMonitor.class.wait();                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        RaceResults.addRacer(this);
    }
}

class RaceResults {

    static List<RacerThread> racers = Collections.synchronizedList(new ArrayList<>());


    synchronized static void addRacer(RacerThread rt) {
        racers.add(rt);
    }

    public static String print() {

        String response = "";

        for (int i = 0; i < racers.size(); i++) {

            response += racers.get(i).name + " finish at the " + (i+1) + " place \n";
        }
        return response;
    }
}