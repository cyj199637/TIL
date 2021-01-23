package f;

public class TypeInference {
    public static void main(String[] args) {
        TypeInference type = new TypeInference();
        // type.makeObjects1();
        // type.makeObjects2();
        // type.makeObjects3();
        type.makeObjects4();
    }

    public void makeObjects1() {
        GenericClass<Integer> generic1 = new GenericClass<>("String");
        generic1.setValue(999);
    }

    public void makeObjects2() {
        GenericClass<Integer> generic1 = new GenericClass("String");
        generic1.setValue(999);
    }

    public void makeObjects3() {
        GenericClass<Integer> generic1 = new <String> GenericClass<Integer>("String");
        generic1.setValue(999);
    }

    public void makeObjects4() {
        GenericClass<Integer> generic1 = new <String> GenericClass<Integer>("String");
        generic1.setValue(999);
    }
}