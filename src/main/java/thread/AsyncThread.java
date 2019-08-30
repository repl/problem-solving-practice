package thread;

import java.util.*;

class Request {
    String payload;
    long delay;

    public Request(String payload) {
        this(payload, 0);
    }

    public Request(String payload, long delay) {
        this.payload = payload;
        this.delay = delay;
    }

    public String execute() {
        try {
            Thread.sleep(delay);
            return process(payload);
        } catch (InterruptedException e) {
            interruptedError();
        }
        return null;
    }

    public String process(String payload) {
        return "processed " + payload;
    }

    public void interruptedError() {
        System.out.println("Request with payload " + payload + " was interrupted. Didn't complete.");
    }
}

public class AsyncThread {
    public static final long THREAD_TIMEOUT = 100L;

    public static void main(String args[]) {
        dispatch(new Request("a"));
        dispatch(new Request("b", 10));
        dispatch(new Request("c", 200));
    }

    public static void dispatch(Request req) {
        Runnable task = new Runnable() {
            public void run() {
                Runnable actualTask = new Runnable() {
                    public void run() {
                        String response = req.execute();
                        System.out.println("Task output: " + response);
                    }
                };
                Thread innerThread = new Thread(actualTask);
                innerThread.start();
                try {
                    Thread.sleep(THREAD_TIMEOUT);
                    innerThread.interrupt();
                } catch (InterruptedException e) {
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
