package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MapSample {
    public static void main(String[] args) {
        MapSample sample = new MapSample();
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        sample.multiplyWithMap(intList);

        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(new StudentDTO("Lora", 25, 90, 100));
        studentList.add(new StudentDTO("Sally", 25, 75, 87));
        studentList.add(new StudentDTO("Sunny", 25, 65, 76));
        sample.getNameWithMap(studentList);
    }

    private void multiplyWithMap(List<Integer> intList) {
        intList.stream().map(x -> x*3).forEach(System.out::println);
    }

    private void getNameWithMap(List<StudentDTO> studentList) {
        List<String> nameList = new ArrayList<>();
        studentList.stream().map(x -> x.getName()).forEach(nameList::add);
        System.out.println(nameList);

        // Answer
        List<String> nameList2 = studentList.stream().map(x -> x.getName()).collect(Collectors.toList());
        System.out.println(nameList2);
    }
}