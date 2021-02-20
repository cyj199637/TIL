public class ManageStudent {
    public static void main(String []args) {
        ManageStudent manager = new ManageStudent();
        Student []students = null;
        students = manager.addStudent();
        manager.printStudents(students);
    }

    public Student[] addStudent() {
        Student []students = new Student[3];
        students[0] = new Student("Lora");
        students[1] = new Student("Sally");
        students[2] = new Student("Sunny", "Suwon", "01012341234", "ask@godofjava.com");
        return students;
    }

    public void printStudents(Student []students) {
        for (Student one:students) {
            System.out.println(one);
        }
    }
}