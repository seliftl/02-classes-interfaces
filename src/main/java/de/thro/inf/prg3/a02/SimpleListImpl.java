package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {
    private static class Element {
        Object item;
        Element next;

        public Element(Object item) {
            this.item = item;
        }
    }

    Element head;
    int size = 0;

    @Override
    public void add(Object o) {
        size++;
        if (head == null) {
            head = new Element(o);
            return;
        }

        Element current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Element(o);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleListImpl sl = new SimpleListImpl();
        for (Object o : this)
            if (filter.include(o)) sl.add(o);
        return sl;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl();
    }


    class SimpleIteratorImpl implements Iterator{
        Element current = head;

        @Override
        public boolean hasNext(){
           return current!=null;
        }

        @Override
        public Object next(){
            Object o = current.item;
            return o;
        }
    }
}
