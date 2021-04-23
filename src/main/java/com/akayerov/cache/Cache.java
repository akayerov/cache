package com.akayerov.cache;

import java.util.*;
//LRU and LFU
public class Cache {
    int size;
    String mode;
    Map<String, Object> store;
    Map<String, Integer> useCounter;
    int counter= 0;

    public Cache(int size, String mode) {
        this.size = size;
        this.mode = mode;
        this.store = new HashMap<>();
        this.useCounter = new HashMap<>();
    }

    boolean put(String key, Object data) {
       if( store.size() >= size ) {
           clearElement();
       }
       store.put(key, data);
       if( mode.equals("LFU"))
          useCounter.put(key, 0);
       else
          useCounter.put(key, counter++);

       return true;
    }

    // Очистка одного из элементов в соотвествии со стратегией
    private void clearElement() {

        List <Map.Entry< String, Integer>> valuesList = new ArrayList(useCounter.entrySet());
        Collections.sort(valuesList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        String key = valuesList.get(0).getKey();
        useCounter.remove(key);
        store.remove(key);
    }

    Object get(String key) {
        if( store.containsKey( key) ) {
            if( mode.equals("LFU"))
                useCounter.compute(key, (k, v) -> (v == null) ? 1 : v + 1);
            return store.get(key);
        }
        else
            return null;
    }

    public void prt() {
        store.forEach( (key, item) -> {
            System.out.println("key = " + key + " value = " + item );
        });
        System.out.println("------ useCounter ---------");
        useCounter.forEach( (key, counter) -> {
            System.out.println("key = " + key + " counter = " + counter );
        });
    }
}
