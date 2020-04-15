package com.IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NMSLEclipse {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        BufferedReader reader = null;
        try {
            String path = "D:\\idea\\Misunderstood\\Colin\\src\\main\\resources\\aaaaaaaaaaaaaaaaaaaaaaaaaaaaa.txt";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
            while (reader.ready()) {
                String s = reader.readLine();
                strings.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        strings.stream().filter(s -> s != null && s.startsWith("D")).forEach(name -> {
            System.out.println(name);
            File oldFile = new File(name);

            String source = "D:\\idea\\Misunderstood\\Colin\\src\\main\\resources\\" + oldFile.getName();
            File newFile = new File(source);
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            if (oldFile.exists()){

                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(oldFile)));
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), "UTF-8"));
                    while (bufferedReader.ready()) {
                        bufferedWriter.write(bufferedReader.read());
                        bufferedWriter.flush();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                boolean flag = oldFile.delete();
                System.out.println(flag);
                if (flag){
                    System.out.println(newFile.renameTo(new File(name)));
                }
            }

        });


    }
}
