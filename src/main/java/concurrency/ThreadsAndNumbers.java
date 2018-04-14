package concurrency;

public class ThreadsAndNumbers {
    private static final int MAX_NUMBER = 100;

    private static class Counter {
        private int value = 1;

        public int getCurrentNumber() {
            synchronized (this) {
                return this.value;
            }
        }

        public void increment() {
            synchronized (this) {
                this.value++;
            }
        }
    }

    private static class NumberChecker implements Runnable {
        private int threadIx = 0;
        private int factor = 0;
        private Counter counter;

        public NumberChecker(int threadIx, int factor, Counter counter) {
            this.threadIx = threadIx;
            this.factor = factor;
            this.counter = counter;
        }

        private boolean isMyNumber(int v) {
            int modValue = v % factor;
            if (modValue == 0) {
                modValue = factor;
            }

            return (modValue == threadIx);
        }

        @Override
        public void run() {
            int v = 0;
            while (true) {
                v = counter.getCurrentNumber();
                if (v > MAX_NUMBER) {
                    break;
                }

                if (!isMyNumber(v)) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Thread " + threadIx + ": value = " + v);
                    counter.increment();
                }
            }
        }
    }

    public static void main(String[] arg) {
        {
            System.out.println("Odd and Even threads:");
            Counter counter = new Counter();

            NumberChecker odd = new NumberChecker(1, 2, counter);
            NumberChecker even = new NumberChecker(2, 2, counter);

            Thread ot = new Thread(odd);
            Thread et = new Thread(even);

            ot.start();
            et.start();

            try {
                ot.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                et.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        {
            System.out.println("Five threads: ");
            Counter counter = new Counter();

            for (int i = 1; i <= 5; i++) {
                NumberChecker nc = new NumberChecker(i, 5, counter);
                Thread t = new Thread(nc);
                t.start();
            }
        }
    }
}
