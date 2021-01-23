package stream;

import java.util.ArrayList;
import java.util.List;

public class FilterSample {
    public static void main(String[] args) {
        FilterSample sample = new FilterSample();

        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(new StudentDTO("Lora", 25, 90, 100));
        studentList.add(new StudentDTO("Sally", 25, 75, 87));
        studentList.add(new StudentDTO("Sunny", 25, 65, 76));
        sample.filterWithScoreForLoop(studentList, 80);
    }

    private void filterWithScoreForLoop(List<StudentDTO> studentList, int scoreCutLine) {
        studentList.stream().filter(x -> x.getScoreEnglish() > scoreCutLine).forEach(x -> System.out.println(x.getName()));
    }
}