package org.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.concurrent.*;

public class Main {
    public static CountDownLatch countdown = new CountDownLatch(10);
    public static CountDownLatch countdownfinish = new CountDownLatch(10);
    public static Semaphore semaphore = new Semaphore(3);

    public static CopyOnWriteArrayList<MFU> mas = new CopyOnWriteArrayList<>();
    public  static ConcurrentHashMap<Integer, Long> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for(int i = 0; i< 10; i++)
        {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long timestart = System.currentTimeMillis();
                    try {
                        int podg = (int)(Math.random()*1000+1);
                        Thread.sleep(podg);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    countdown.countDown();
                    try {
                        countdown.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Машина " + index + " стартовала");
                    try {
                        int gonka = (int)(Math.random()*1000+1);
                        Thread.sleep(gonka);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        semaphore.acquire();
                        System.out.println("Машина " + index + " в тонеле");
                        try {
                            int vtonele = (int)(Math.random()*5000+1);
                            Thread.sleep(vtonele);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Машина " + index + " выехала");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                    try {
                        int gonka2 = (int)(Math.random()*10000+1);
                        Thread.sleep(gonka2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    long timeafter = System.currentTimeMillis();
                    double time = (double) ((timeafter - timestart)/1000.0);
                    System.out.println("Машина " + index + " фишинировала");
                    MFU test = new MFU(index, time);
                    mas.add(test);
                    //map.put(index, time);
                    countdownfinish.countDown();
                }
            }).start();
        }
        try {
            countdownfinish.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double min = mas.get(0).gettime();
        int car = mas.get(0).getIndex();
        for(int i = 0; i < mas.size(); i++)
        {
            System.out.println("Машина " + mas.get(i).getIndex() + " проехала за " + mas.get(i).gettime());
            if (mas.get(i).gettime() < min)
            {
                min = mas.get(i).gettime();
                car = mas.get(i).getIndex();
            }
        }
        System.out.println("Машина с индексом " + car + " победила за " + min);
    }
}