package c.annotation;

public class AnnotationOverride extends Parent{
    @Override
    public void printName() {
        System.out.println("AnnotationOverride");
    }
}