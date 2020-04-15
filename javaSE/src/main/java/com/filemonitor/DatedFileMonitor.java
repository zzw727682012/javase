package com.filemonitor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DatedFileMonitor implements FileMonitor {

    private ConcurrentHashMap<String, File> fileMap = new ConcurrentHashMap<>();

    private List<FileChangeObserver> observerList = new ArrayList<>();

    private ScheduledExecutorService executorService;
    public volatile static DatedFileMonitor instance = new DatedFileMonitor();

    private DatedFileMonitor(){

        executorService = Executors.newSingleThreadScheduledExecutor();
        attach(new DatedFileDeleteObserver());
        monitor();
    }

    public static DatedFileMonitor getInstance(){
        return instance;
    }
    public void addMonitorFile(File file){
        if (fileMap.containsKey(file.getName())){
            return;
        }
        fileMap.put(file.getName(), file);
    }
    @Override
    public void attach(FileChangeObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(FileChangeObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(File file) {
        for (FileChangeObserver observer:observerList) {
            observer.handle(file);
        }
    }

    private void monitor(){
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!fileMap.isEmpty()) {
                    for (File f : fileMap.values()) {
                        if (f.listFiles().length != 0) {
                            notifyObserver(f);
                        }
                    }
                }
            }
        }, 0, 15, TimeUnit.SECONDS);

    }

}
