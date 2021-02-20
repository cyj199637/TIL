package c.impl.list;

public abstract class AbstractList implements List {
    public void add() {
        System.out.println("Add List");
    }

    public void update(int index, Object obj) {
        System.out.println("Update List");
    }

    public void remove(int index) {
        System.out.println("Remove List");
    }

    public abstract void clear();
}