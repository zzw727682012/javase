package com.filemonitor;

import java.io.File;

public interface FileMonitor {


    public void attach(FileChangeObserver observer);

    public void detach(FileChangeObserver observer);
    public void notifyObserver(File file);

}
