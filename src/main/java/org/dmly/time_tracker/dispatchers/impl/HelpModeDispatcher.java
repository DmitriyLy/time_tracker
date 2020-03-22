package org.dmly.time_tracker.dispatchers.impl;

import org.dmly.time_tracker.dispatchers.Dispatcher;

public class HelpModeDispatcher implements Dispatcher {
    @Override
    public void dispatch() {
        System.out.println("Time tracker manual:\n");
        System.out.println("Intervals example (without quotes):");
        System.out.println("'10m' - 10 minutes");
        System.out.println("'11s' - 11 seconds");
        System.out.println("'15m 12s' - 15 minutes and 12 seconds");
        System.out.println(" ");
        System.out.println("Interval list example:\n");
        System.out.println("[10m, 5m, 3s, 12m 10s]");
    }
}
