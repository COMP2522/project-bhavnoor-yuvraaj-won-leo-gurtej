package org.bcit.comp2522.project;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Custom thread pool class, uses another genius implementation with a blocking queue, so we loop
 * through the threads, and when a task becomes available, it grabs it from the queue, and runs it.
 * This is something I'm very proud of lol.
 *
 * @author Bhavnoor Saroya
 */
public class CustomThreadPool {
  private static BlockingQueue<Runnable> taskQueue;
  private static Thread[] workerThreads;

  /**
   * The Single instance.
   */
  static CustomThreadPool singleInstance;

  private CustomThreadPool(int poolSize) {
    taskQueue = new LinkedBlockingQueue<>();
    workerThreads = new Thread[poolSize];

    for (int i = 0; i < poolSize; i++) {
      workerThreads[i] = new Thread(() -> {
        while (true) {
          try {
            Runnable task = taskQueue.take();
            task.run();
          } catch (InterruptedException e) {
            break;
          }
        }
      });
      workerThreads[i].start();
    }
  }

  /**
   * Get instance custom thread pool.
   *
   * @param poolSize the pool size
   * @return the custom thread pool
   */
  public static CustomThreadPool getInstance(int poolSize){
    if (singleInstance == null) {
      singleInstance = new CustomThreadPool(poolSize);
    }

    return singleInstance;
  }

  /**
   * Submit.
   *
   * @param task the task
   * @throws InterruptedException the interrupted exception
   */
  public void submit(Runnable task) throws InterruptedException {
    taskQueue.put(task);
  }
}

