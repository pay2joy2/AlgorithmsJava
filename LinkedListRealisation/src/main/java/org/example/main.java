package org.example;

public class main {
    public static void main(String[] args) {
        LinkedTry x = new LinkedTry();
        LinkedTry y = new LinkedTry();
        for (int i = 0; i < 5; i++)
        {
            x.add(i);
        }
        for (int i = 0; i < 5; i++)
        {
            y.add((int) (Math.random()*100));
        }
        x.delete(0);
        x.print();
        System.out.println();
        y.print();
    }
}
