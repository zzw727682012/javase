package com.filemonitor;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class DatedFileDeleteObserver implements FileChangeObserver {
    private final static Logger log = LoggerFactory.getLogger(DatedFileDeleteObserver.class);
    @Override
    public void handle(File file) {
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i=0;i<files.length;i++){
                handle(files[i]);
            }
        } else {
            long days = (System.currentTimeMillis() - file.lastModified())/(60*60*24*1000);
            log.info(file.getName() + "time:" + days);
            System.out.println(file.getName() + "time:" + days);
            if (days > 7) {
                file.delete();

            }
        }

    }


}
