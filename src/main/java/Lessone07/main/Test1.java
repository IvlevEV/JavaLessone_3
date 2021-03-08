package Lessone07.main;

public class Test1 {

    @BeforeSuite
    public static void taskBefore() {
        System.out.println(Test1.class.getSimpleName() + " before");
    }

    @Test(priority = 1)
    public static void task1() {
        System.out.println(Test1.class.getSimpleName() + " task1");
    }

    @Test(priority = 3)
    public static void task2() {
        System.out.println(Test1.class.getSimpleName() + " task2");
    }

    @Test(priority = 10)
    public static void task3() {
        System.out.println(Test1.class.getSimpleName() + " task3");
    }

    @Test(priority = 3)
    public static void task4() {
        System.out.println(Test1.class.getSimpleName() + " task4");
    }

    @AfterSuite
    public static void taskAfter() {
        System.out.println(Test1.class.getSimpleName() + " after");
    }
}
