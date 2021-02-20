package stream;

import java.util.ArrayList;
import java.util.List;

public class StudentForEachSample {
    public static void main(String[] args) {
        StudentForEachSample sample = new StudentForEachSample();
        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(new StudentDTO("Lora", 25, 90, 100));
        studentList.add(new StudentDTO("Sally", 25, 75, 87));
        studentList.add(new StudentDTO("Sunny", 25, 65, 76));
        sample.printStudentNames(studentList);
        sample.printStudentAge(studentList);
        sample.printStudentScoreMath(studentList);
        sample.printStudentScoreEnglish(studentList);
    }

    public void printStudentNames(List<StudentDTO> students) {
        students.stream().forEach(student -> System.out.println(student.getName()));
    }

    public void printStudentAge(List<StudentDTO> students) {
        students.stream().map(student -> student.getAge()).forEach(x -> System.out.println(x));
    }

    public void printStudentScoreMath(List<StudentDTO> students) {
        students.stream().map(student -> student.getScoreMath()).forEach(x -> System.out.println(x));
    }

    public void printStudentScoreEnglish(List<StudentDTO> students) {
        students.stream().map(student -> student.getScoreEnglish()).forEach(x -> System.out.println(x));
    }
}