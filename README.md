# Java Multithreading Demos

This project contains two Java programs that demonstrate key concepts in multithreading:
1. **Multithreaded Array Summation**
2. **Producer-Consumer Simulation**

These programs were developed as part of a university-level assignment to explore thread management, synchronization, and performance comparisons between single-threaded and multithreaded approaches.

---

## 📌 Features

### 1. Multithreaded Array Summation
- Divides a large array of random integers into equal segments.
- Launches multiple threads to compute partial sums in parallel.
- Combines the results to get a final total sum.
- Compares performance and correctness with a single-threaded summation.

### 2. Producer-Consumer Simulation
- Implements a classic concurrency problem.
- Uses multiple producer and consumer threads.
- Shared buffer has limited capacity and uses synchronization (`wait()` and `notifyAll()`).
- Logs real-time production and consumption actions to illustrate threading.

---

## 🧠 Concepts Demonstrated
- Thread creation and management (`Runnable`, `Thread`)
- Thread synchronization (`synchronized`, `wait()`, `notifyAll()`)
- Shared resource management and race condition prevention
- Java concurrency utilities (`AtomicLong`, `Object monitor`)
- Performance benchmarking of multithreaded vs single-threaded computation
