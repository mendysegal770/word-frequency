package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FileReaderThread fileReaderThread=new FileReaderThread("file2",false);
        fileReaderThread.start();
       fileReaderThread.join();
        System.out.println("---------------");
        FileReaderThread fileReaderThread1=new FileReaderThread("file1",true);
        fileReaderThread1.start();
        fileReaderThread1.join();
        System.out.println("---------------");
        FileReaderThread fileReaderThread2 =new FileReaderThread("file3",true);
        fileReaderThread2.start();
       fileReaderThread2.join();
        LinkedHashMap<String, Integer> sortedMap = Utils.map.entrySet()
                .stream()
                .sorted(HashMap.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        HashMap.Entry::getKey,
                        HashMap.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(sortedMap);




    }
}