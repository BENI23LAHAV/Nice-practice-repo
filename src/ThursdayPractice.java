import java.util.*;

public class ThursdayPractice {
    public static void main(String[] args) {
        /*Exercise one*/
//        new HelloThread().start();
        /*Exercise two*/

//        BankCounter bc = new BankCounter();
//        new CustomerThread(bc,70).start();
//        new CustomerThread(bc,50).start();

        /*Exercise two & three*/
//        startRace();
        /*Exercise four*/
//        Thread st = new SleepyThread();
//        st.start();
//
//        try {
//            Thread.sleep(1000);
//            st.interrupt();
//        } catch (Exception e) {
//            System.out.println("here");
//        }
        /*Exercise four.2*/
//CounterThread ct = new CounterThread();
//Thread t1 = new Thread(ct);
//t1.start();
//try {
//    Thread.sleep(1000);
//    t1.interrupt();
//}catch (Exception e){
//
//}
        /*Exercise four.3*/

//        List<Thread> threadsList = new ArrayList<>();
//        System.out.println("here");
//        for (int i = 1; i <= 5; i++) {
//            threadsList.add(new Thread(new CounterThread(), i + ""));
//        }
//        for (Thread t : threadsList){
//            t.start();
//        }
//        try {
//            Thread.sleep(4000);
//            for (Thread t : threadsList){
//                t.interrupt();
//            }
//        }catch (Exception e){}

        /*Exercise fifth.1 */
//        SignalBox sb = new SignalBox();
//        Thread t1 = new Thread(() -> sb.waitForSignal(), "first");
//        Thread t2 = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//                sb.sendSignal();
//            } catch (Exception e) {
//
//            }
//        }, "second");
//
//        t1.start();
//        t2.start();

        /*Exercise fifth.2 */
//        Counter c = new Counter();
//        Thread t1 = new Thread(() -> c.waitForValue(3));
//        Thread t2 = new Thread(() -> {
//            int i = 0;
//            while (i < 3) {
//
//                try {
//                    c.increase();
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                }finally {
//                    i++;
//                }
//            }
//        });
//        t1.start();
//        t2.start();

        /*Exercise fifth.3 */
//        MessageQueue mq = new MessageQueue();
//        Thread t1 = new Thread(() -> {
//            for (int i = 1; i <= 5; i++) {
//                try {
//                    Thread.sleep(1000);
//                    mq.produce("message " + i);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//
//            }
//        });
//        Thread t2 = new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                System.out.println(mq.consume());
//            }
//        });
//t1.start();
//t2.start();
        /*Exercise fifth.4 */
//        msgQueueDemo();


    }

    public static void startRace() {
        Track track = new Track();
        for (int i = 1; i <= 10; i++) {
            track.addCar(new CarThread("Car " + i, track.trackLength, track));
        }
        for (int i = 0; i < track.cars.size(); i++) {
            track.cars.get(i).start();

        }
        for (CarThread car : track.cars) {
            try {
                car.join();
            } catch (Exception e) {
            }
        }
        System.out.println();
        for (int i = 1; i <= 3; i++) {
            System.out.println(track.carsFinishTime.get(i).carName + " Finished at the " + i + "place");
        }
    }
    public static void msgQueueDemo(){
        MessageQueue mq = new MessageQueue();
        List<Thread> texting = new ArrayList<>();
        List<Thread> consumers = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            texting.add(new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    mq.produce("message " + j + " from thread" + Thread.currentThread().getName());
                    try {
                        Thread.sleep((new Random().nextInt(4) + 1) * 1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }, "Sender " + i));
        }
        for (int i = 1; i <= 3 ; i++) {
            consumers.add(new Thread(()->{
                for (int j = 1; j <= 3; j++) {
                    System.out.println(Thread.currentThread().getName()+" is reading... \n"+mq.consume());
                    System.out.println();
                }
            },"Consumer "+i));
        }

        for (Thread t:texting){
            t.start();
        }
        for (Thread t: consumers){
            t.start();
        }
        for (Thread t:texting){
            try {
                t.join();

            }catch (Exception e){}
        }
        for (Thread t: consumers){
            try {
                t.join();

            }catch (Exception e){}

        }
        if(!mq.msgQueue.isEmpty()){
            new Thread(()->{
                while (!mq.msgQueue.isEmpty()){
                    System.out.println(Thread.currentThread().getName()+" is reading... \n"+mq.consume());
                    System.out.println();
                }
            }, "Final thread").start();
        }
    }
}


/*Exercise one*/

class HelloThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello from thread - message " + i);
            try {
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

/*Exercise two*/

class BankCounter {

    int balance = 100;

    void withdraw(int amount) {
        synchronized (this) {
            if (this.balance >= amount) {
                try {
                    Thread.sleep(1000);
                    this.balance -= amount;
                    System.out.println("Your current balance is: " + this.balance);
                } catch (Exception e) {
                }
            } else {
                System.out.println("The requested amount is higher than your balance");
            }
        }

    }
}

class CustomerThread extends Thread {
    BankCounter bankCounter;
    int withdrawalAmount;

    CustomerThread(BankCounter bankCounter, int withdrawalAmount) {
        this.bankCounter = bankCounter;
        this.withdrawalAmount = withdrawalAmount;
    }

    @Override
    public void run() {
        bankCounter.withdraw(withdrawalAmount);
    }
}

/*Exercise two & three*/

class Track {
    int trackLength;

    List<CarThread> cars;
    List<CarThread> carsFinishTime;

    Track() {
        this.setTrackLength();
        this.cars = new ArrayList<>();
        this.carsFinishTime = new ArrayList<>();
    }

    void setTrackLength() {
        System.out.println("Enter the track length");
        this.trackLength = (new Scanner(System.in).nextInt()) * 10;
    }

    void addCar(CarThread car) {
        this.cars.add(car);
    }

    void addFinishedCar(CarThread car) {
        synchronized (this) {
            this.carsFinishTime.add(car);

        }
    }


}

class CarThread extends Thread {

    String carName;
    int sleepTime;

    int trackLength;

    Track addFinishedCar;

    CarThread(String carName, int trackLength, Track addFinishedCar) {
        this.carName = carName;
        this.sleepTime = new Random().nextInt(1500) + 500;
        this.trackLength = trackLength;
        this.addFinishedCar = addFinishedCar;
    }


    @Override
    public void run() {
        int i;
        for (i = 0; i < trackLength; i++) {
            if ((i % 10 == 0) && i > 0) {
                System.out.println(this.carName + " progressed " + i + "km from / " + trackLength + " km");
                try {
                    Thread.sleep(this.sleepTime);
                } catch (Exception e) {
                }
            }
        }

        System.out.println(this.carName + " progressed " + i + "km from / " + trackLength + " km");

        this.addFinishedCar.addFinishedCar(this);
    }
}

/*Exercise four*/
class SleepyThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Thread going to sleep");
            Thread.sleep(5000);
            System.out.println("Thread woke up successfully");
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted");

        }

    }

}

class CounterThread implements Runnable {

    int counter = 0;

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                counter++;
                System.out.println("Working in thread " + Thread.currentThread().getName());
                Thread.sleep(0);
            } catch (InterruptedException e) {
                System.out.println("Thread " + Thread.currentThread().getName() + " Counter interrupted in " + this.counter);
                break;
            }
        }
    }
}

/*Exercise fifth */

class SignalBox {
    synchronized void waitForSignal() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting");
            wait();
            System.out.println(Thread.currentThread().getName() + " finish to waiting");
        } catch (Exception e) {

        }

    }

    synchronized void sendSignal() {
        System.out.println(Thread.currentThread().getName() + " is notifying the other thread");
        notify();
    }
}

/*Exercise fifth.2 */


class Counter {
    int value = 0;

    synchronized void waitForValue(int expected) {
        while (expected > value) {
            try {
                System.out.println("I am waiting. value is " + this.value + " expected is " + expected);
                wait();

            } catch (Exception e) {
            }
        }
        System.out.println("I finished to wait. value is " + this.value);

    }

    synchronized void increase() {
        this.value++;
        System.out.println("Everybody, pay attention value is " + this.value);
        notifyAll();
    }
}

/*Exercise fifth.3 & 4*/

class MessageQueue {

    Queue<String> msgQueue = new LinkedList<>();

    synchronized void produce(String msg) {
        msgQueue.add(msg);
        notifyAll();
    }

    synchronized String consume() {

        while (this.msgQueue.isEmpty()) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
        return msgQueue.poll();

    }
}
