package org.example;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MFU {
    private int index;
    private double time;

    public MFU(int index, double time) {
        this.index = index;
        this.time = time;
    }
    public int getIndex()
    {
        return index;
    }
    public double gettime()
    {
        return time;
    }
}
