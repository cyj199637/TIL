package c.inner;

public class NestedSample {
    public static void main(String []args) {
        NestedSample sample = new NestedSample();
        sample.makeStaticNestedObject();
        sample.makeInnerObject();
    }

    public void makeStaticNestedObject() {
        OuterOfStatic.StaticNested staticNested = new OuterOfStatic.StaticNested();
        staticNested.setValue(3);
        System.out.println(staticNested.getValue());
    }

    public void makeInnerObject() {
        OuterOfInner outer = new OuterOfInner();
        OuterOfInner.Inner inner = outer.new Inner();
        inner.serValue(3);
        System.out.println(inner.getValue());
    }
}