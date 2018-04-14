package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProducerConsumerTests {

    private static final int MAX_ITEMS = 100;

    private static class BoundedQueue<T> extends LinkedList<T> {
        private int maxItems = 1;

        public BoundedQueue(int maxItems) {
            this.maxItems = maxItems;
        }

        @Override
        public boolean add(T element) {
            synchronized (this) {
                boolean added = false;
                try {
                    int nrItems = size();
                    if (nrItems >= maxItems) {
                        wait();
                    }
                    added = super.add(element);
                    notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return added;
            }
        }

        @Override
        public T remove() {
            T element = null;
            synchronized (this) {
                while (isEmpty()) {
                    try {
                        wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                element = super.remove();

                notifyAll();
            }

            return element;
        }
    }

    private static class BoundedProducer implements Runnable {
        private BoundedQueue<Integer> queue = null;
        private Semaphore semaphore = null;
        private int nrItems = 0;

        public BoundedProducer(BoundedQueue<Integer> queue, Semaphore semaphore) {
            this.queue = queue;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while (nrItems++ < MAX_ITEMS) {
                Random r = new Random();

                Integer item = r.nextInt(100);
                System.out.println("Produced: " + item);

                queue.add(item);
            }
        }
    }

    private static class BoundedConsumer implements Runnable {
        private BoundedQueue<Integer> queue = null;
        private Semaphore semaphore = null;
        private int nrItems = 0;

        public BoundedConsumer(BoundedQueue<Integer> queue, Semaphore semaphore) {
            this.queue = queue;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while (nrItems++ < MAX_ITEMS) {
                Integer item = this.queue.remove();
                System.out.println("Consumed: " + item);
            }
        }
    }

    private static class Producer implements Runnable {
        private Queue<Integer> queue = null;
        private Semaphore producerSemaphore = null;
        private Semaphore consumerSemaphore = null;
        private int nrItems = 0;

        public Producer(Queue<Integer> queue, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
            this.queue = queue;
            this.producerSemaphore = producerSemaphore;
            this.consumerSemaphore = consumerSemaphore;
        }

        @Override
        public void run() {
            while (nrItems++ < MAX_ITEMS) {
                Random r = new Random();

                try {
                    producerSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Integer item = r.nextInt(100);
                System.out.println("Produced: " + item);

                queue.add(item);
                consumerSemaphore.release();
            }
        }
    }

    private static class Consumer implements Runnable {
        private Queue<Integer> queue = null;
        private Semaphore producerSemaphore = null;
        private Semaphore consumerSemaphore = null;
        private int nrItems = 0;

        public Consumer(Queue<Integer> queue, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
            this.queue = queue;
            this.producerSemaphore = producerSemaphore;
            this.consumerSemaphore = consumerSemaphore;
        }

        @Override
        public void run() {
            while (nrItems++ < MAX_ITEMS) {
                try {
                    consumerSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Integer item = this.queue.remove();
                System.out.println("Consumed: " + item);

                producerSemaphore.release();
            }
        }
    }

    private static class UnitProducer implements Runnable {
        private Queue<Integer> queue = null;
        private Semaphore semaphore = null;
        private int nrItems = 0;

        public UnitProducer(Queue<Integer> queue, Semaphore semaphore) {
            this.queue = queue;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while (nrItems++ < MAX_ITEMS) {
                synchronized (queue) {
                    Random r = new Random();

                    Integer item = r.nextInt(100);
                    System.out.println("Produced: " + item);

                    queue.add(item);

                    queue.notifyAll();
                }

                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class UnitConsumer implements Runnable {
        private Queue<Integer> queue = null;
        private Semaphore semaphore = null;
        private int nrItems = 0;

        public UnitConsumer(Queue<Integer> queue, Semaphore semaphore) {
            this.queue = queue;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while (nrItems++ < MAX_ITEMS) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Integer item = this.queue.remove();
                    System.out.println("Consumed: " + item);

                    semaphore.release();
                }
            }
        }
    }

    public static void main(String[] args) {

        {
            /*
             * Producer produces one item, and waits until consumer consumes it.
             * Consumer consumes one item, and waits until next item is available.
             */
            System.out.println("Unit Producer and Consumer:");
            Queue<Integer> sharedQueue = new LinkedList<>();
            Semaphore semaphore = new Semaphore(0);

            UnitConsumer uc = new UnitConsumer(sharedQueue, semaphore);
            UnitProducer up = new UnitProducer(sharedQueue, semaphore);

            Thread pt = new Thread(up);
            Thread ct = new Thread(uc);

            pt.start();
            ct.start();
            
            try {
                pt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                ct.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        {
            /*
             * Producer and Consumer purely via semaphores.
             * Producer semaphore has 10 permits to start with, hence able to produce.
             * Consumer semaphore has 0 permits to start with, hence waits until produced.
             */
            System.out.println("Producer and Consumer:");
            Queue<Integer> sharedQueue = new LinkedList<>();
            Semaphore consumerSemaphore = new Semaphore(0);
            Semaphore producerSemaphore = new Semaphore(10);

            Consumer c = new Consumer(sharedQueue, producerSemaphore, consumerSemaphore);
            Producer p = new Producer(sharedQueue, producerSemaphore, consumerSemaphore);

            Thread pt = new Thread(p);
            Thread ct = new Thread(c);

            ct.start();
            pt.start();

            try {
                pt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                ct.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        {
            /*
             * Producer consumer problem without semaphores, but using only
             * synchronized lock and notify mechanism.
             * Bounded queue has the synchronization logic. Adding content to the queue waits
             * when queue becomes full, and removing content from the queue waits (with timeout
             * and checks) if queue is empty.
             */
            System.out.println("Bounded Producer and Consumer:");
            BoundedQueue<Integer> sharedQueue = new BoundedQueue<>(10);

            BoundedConsumer c = new BoundedConsumer(sharedQueue, null);
            BoundedProducer p = new BoundedProducer(sharedQueue, null);

            Thread pt = new Thread(p);
            Thread ct = new Thread(c);

            pt.start();
            ct.start();

            try {
                pt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                ct.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
