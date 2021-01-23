package d.collection;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapSample {
    public static void main(String[] args) {
        TreeMapSample sample = new TreeMapSample();
        // sample.checkTreeMap();
        sample.checkKeys();
    }

    public void checkTreeMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("A", "a");
        map.put("가", "e");
        map.put("1", "f");
        map.put("a", "g");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> tempEntry:entries) {
            System.out.println(tempEntry.getKey()+" = "+tempEntry.getValue());
        }
    }

    public void checkKeys() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("A", "a");
        map.put("가", "e");
        map.put("1", "f");
        map.put("a", "g");

        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
        System.out.println(map.higherKey("A"));
        System.out.println(map.lowerKey("a"));
    }
}