package d.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class MapSample {
    public static void main(String[] args) {
        MapSample sample = new MapSample();
        // sample.checkHashMap();
        // sample.checkKeySet();
        // sample.checkValues();
        // sample.checkEntrySet();
        // sample.checkContains();
        sample.checkRemove();
    }

    public void checkHashMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "a");
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
        
        map.put("A", "1");
        System.out.println(map.get("A"));
    }

    public void checkKeySet() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "a");
        map.put("C", "c");
        map.put("D", "d");
        Set<String> keySet = map.keySet();
        
        for (String tempKey:keySet) {
            System.out.println(tempKey+" = "+map.get(tempKey));
        }
    }

    public void checkValues() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "a");
        map.put("C", "c");
        map.put("D", "d");

        Collection<String> values = map.values();
        for (String tempValue:values) {
            System.out.println(tempValue);
        }
    }

    public void checkEntrySet() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> tempEntry:entries) {
            System.out.println(tempEntry.getKey()+" = "+tempEntry.getValue());
        }
    }

    public void checkContains() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");

        System.out.println(map.containsKey("A"));
        System.out.println(map.containsKey("Z"));
        System.out.println(map.containsValue("a"));
        System.out.println(map.containsValue("z"));
    }

    public void checkRemove() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "a");
        map.remove("A");
        System.out.println(map.size());
    }
}