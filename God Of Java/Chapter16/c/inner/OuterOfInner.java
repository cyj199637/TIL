package c.inner;

public class OuterOfInner {
    class Inner {
        private int value = 0;
        public int getValue() {
            return value;
        }
        public void serValue(int value) {
            this.value = value;
        }
    }
}