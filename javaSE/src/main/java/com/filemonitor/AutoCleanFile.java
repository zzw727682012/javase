package com.filemonitor;

import java.io.File;

public class AutoCleanFile {
    public static void main(String[] args) {
        File file1 = new File("D:\\apache-tomcat-6.0.39\\apache-tomcat-6.0.39\\logs");
        File file = new File("D:\\FTP");

        DatedFileMonitor.getInstance().addMonitorFile(file);
    }
}
