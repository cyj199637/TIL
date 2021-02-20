package c.annotation;

public class AnnotationSample {
    @SuppressWarnings("deprecation")
    public void useDeprecated() {
        AnnotationDeprecated child = new AnnotationDeprecated();
        child.noMoreUse();
    }
}