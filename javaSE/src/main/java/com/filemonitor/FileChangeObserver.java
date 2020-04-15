package com.filemonitor;

import java.io.File;

public interface FileChangeObserver {
    public void handle(File file);
}
