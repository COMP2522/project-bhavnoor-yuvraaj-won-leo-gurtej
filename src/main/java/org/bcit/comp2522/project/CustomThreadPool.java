package org.bcit.comp2522.project;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
  private static BlockingQueue<Runnable> taskQueue;
  private static Thread[] workerThreads;

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

  public static CustomThreadPool getInstance(int poolSize){
    if (singleInstance == null) {
      singleInstance = new CustomThreadPool(poolSize);
    }

    return singleInstance;
  }

  public void submit(Runnable task) throws InterruptedException {
    taskQueue.put(task);
  }
}

