package utils;

public class Util {
    public static void delay(int secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }
}