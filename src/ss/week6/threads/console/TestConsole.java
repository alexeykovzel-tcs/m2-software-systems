package ss.week6.threads.console;

public class TestConsole implements Runnable {
    private void sum() {
        String threadName = Thread.currentThread().getName();
        int a = Console.readInt(threadName + ": get number 1? ");
        int b = Console.readInt(threadName + ": get number 2? ");
        Console.println(String.format("%s: %d + %d = %d", threadName, a, b, a + b));
    }

    @Override
    public void run() {
        sum();
    }

    public static void main(String[] args) {
        new Thread(new TestConsole(), "Thread A").start();
    }
}
