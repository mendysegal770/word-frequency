package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileReaderThread extends Thread {


    private String fileName;


    public FileReaderThread(String fileName, boolean isNotFirst) {
        if (!isNotFirst) {
            Utils.map = new HashMap<>();
        }
        this.fileName = fileName;

    }

    public void run() {
        try (InputStream is = FileReaderThread.class.getResourceAsStream("/" + fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    AtomicThreadToMap(part);

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private synchronized void AtomicThreadToMap(String part) {
        Integer count = Utils.map.get(part);
        if (count == null) {
            count = 0;
        }
        Utils.map.put(part, count + 1);
        System.out.println(Utils.map);

    }


}

