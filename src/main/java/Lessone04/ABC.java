package Lessone04;

public class ABC {
    static volatile char c = 'A';
    static Object mon = new Object();

    static class WaitNotifyClass implements Runnable {
        private char currentLetter;
        private char nextLetter;

        public WaitNotifyClass(char currentLetter, char nextLetter) {
            this.currentLetter = currentLetter;
            this.nextLetter = nextLetter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                synchronized (mon) {
                    try {
                        while (c != currentLetter)
                            mon.wait();
                        System.out.print(currentLetter);
                        c = nextLetter;
                        mon.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Task1");
        new Thread(new WaitNotifyClass('A', 'B')).start();
        new Thread(new WaitNotifyClass('B', 'C')).start();
        new Thread(new WaitNotifyClass('C', 'A')).start();
    }
}

class Example_SB_1  {
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();
        new Thread(() -> e1.method3()).start();
    }

    public synchronized void method1() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("A");
    }

    public synchronized void method2() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("C");
    }
    public synchronized void method3() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("B");
    }
}


class RunnableDemo {
    static class RunnableClass implements Runnable {
        boolean suspended = false;

        public void run() {
            System.out.println("Запуск потока");
            try {
                for (int i = 10; i > 0; i--) {
                    System.out.println("ABC");
                    synchronized (this) {
                        while (suspended) {
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
            }
            System.out.println("Завершение потока");
        }

        public void mySuspend() {
            suspended = true;
        }

        public synchronized void myResume() {
            suspended = false;
            notify();
        }
    }



    public static void main(String[] args) {
        RunnableClass rc = new RunnableClass();
        new Thread(rc).start();
        try {
            Thread.sleep(8000);
            rc.mySuspend();
            Thread.sleep(10000);
            rc.myResume();
            Thread.sleep(9000);
            rc.mySuspend();
            Thread.sleep(20000);
            rc.myResume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Main {

    private static volatile char[] chars = {'A', 'B', 'C'};
    private static volatile int currentChar = 0;

    public synchronized char getCurrentChar() {
        return chars[currentChar];
    }

    public synchronized void nextChar() {
        if (++currentChar == chars.length) {
            currentChar = 0;
        }
    }

    public static void main(String[] args) {
        parralelThreads();
    }

    private static void parralelThreads() {
        Main waitObject = new Main();
        for (int i = 0; i < chars.length; i++) {
            final int j = i;
            new Thread(() -> {
                waitObject.printChar(chars[j]);
            }).start();
        }
    }

    private synchronized void printChar(char aChar) {
        for (int i = 0; i < 5; i++) {
            while (aChar != getCurrentChar()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(aChar);
            nextChar();
            notifyAll();
        }
    }
}}