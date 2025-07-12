// ***************************************************************************
// 
//   Joel John Thomas 
//   Z2004783
//   CSCI 470 - PE1 
// 
//   I certify that this is my own work and where appropriate an extension 
//   of the starter code provided for the assignment. 
// ***************************************************************************

import java.util.LinkedList;

// Shared buffer with synchronized produce and consume methods
class Buffer {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int capacity = 10;

    // Producer adds item to buffer if space is available
    public synchronized void produce(int item, int producerId) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait(); // Wait if buffer is full
        }
        buffer.add(item);
        System.out.println("Producer " + producerId + " produced item " + item);
        notifyAll(); // Notify consumers
    }

    // Consumer removes item from buffer if available
    public synchronized int consume(int consumerId) throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // Wait if buffer is empty
        }
        int item = buffer.removeFirst();
        System.out.println("Consumer " + consumerId + " consumed item " + item);
        notifyAll(); // Notify producers
        return item;
    }
}

// Producer thread class
class Producer extends Thread {
    private final Buffer buffer;
    private final int id;
    private final int itemsToProduce;

    public Producer(Buffer buffer, int id, int itemsToProduce) {
        this.buffer = buffer;
        this.id = id;
        this.itemsToProduce = itemsToProduce;
    }

    public void run() {
        try {
            for (int i = 0; i < itemsToProduce; i++) {
                buffer.produce(i, id); // Produce item
                Thread.sleep(50); // Simulate production time
            }
        } catch (InterruptedException ignored) {}
    }
}

// Consumer thread class
class Consumer extends Thread {
    private final Buffer buffer;
    private final int id;
    private final int itemsToConsume;

    public Consumer(Buffer buffer, int id, int itemsToConsume) {
        this.buffer = buffer;
        this.id = id;
        this.itemsToConsume = itemsToConsume;
    }

    public void run() {
        try {
            for (int i = 0; i < itemsToConsume; i++) {
                buffer.consume(id); // Consume item
                Thread.sleep(70); // Simulate consumption time
            }
        } catch (InterruptedException ignored) {}
    }
}

// Main class to launch producer and consumer threads
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        int totalItems = 20;

        // One producer and one consumer
        Producer p1 = new Producer(buffer, 1, totalItems);
        Consumer c1 = new Consumer(buffer, 1, totalItems);

        p1.start();
        c1.start();
    }
}