package Lessone5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author asatyukova
 * @version v1.0
 * Организуем гонки:
 * Все участники должны стартовать одновременно, несмотря на то что на подготовку у каждого их них уходит разное время
 * В туннель не может заехать одновременно больше половины участников (условность)
 * Попробуйте всё это синхронизировать.
 * Только после того как все завершат гонку нужно выдать объявление об окончании
 * Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent
 *
 * Начало гонки - CyclicBarrier
 * Тунель - Semaphore
 * Финал - CyclicBarrier
 *
 */
public class MainClass {

    public static final int CARS_COUNT = 4;
    public static Thread[] threads = new Thread[CARS_COUNT];
    static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        Car.setCyclicBarrier(new CyclicBarrier(CARS_COUNT));
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            threads[i] = new Thread(cars[i]);
            threads[i].start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

