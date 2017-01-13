package tv;

import java.io.Closeable;
import java.io.Serializable;
import java.util.*;

/**
 * Created by geekslife on 2017. 1. 12..
 */
public class TobyLee004Application<XXX> {
    static class Hello<T> { // T : Type Parameter
        T method( T arg ) { return arg; } // Method Type Parameter
    }

    public <X> TobyLee004Application(X foo) { // 생성자에도 타입 파라미터 쓸 수 있다.

    }

    <T> void instanceMethod(T hello) { // 인스턴스 메서드 타입 파라미터
        System.err.println(hello+"world");
    }

//    static void errXXX(XXX foo) {} // static 메서드 레벨에서는 class level type parameter 를 쓸 수 없다

    static class Hello2<U extends Number> { // U : Bounded Type Parameter

    }

    static <T extends Serializable & Comparable & Closeable> void print() {} // Intersection Type Parameter


    static <T extends Comparable<T>> long countGreaterThan(T[] arr, T elem) {
        // T가 comaparable 을 상속하는 Bounded Type Parameter 이므로 compareTo를 사용할 수 있다.
        return Arrays.stream(arr).filter(s -> s.compareTo(elem) > 0 ).count();
    }

    static class MyList<E, P> implements List<E> {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(E e) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public E get(int index) {
            return null;
        }

        @Override
        public E set(int index, E element) {
            return null;
        }

        @Override
        public void add(int index, E element) {

        }

        @Override
        public E remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<E> listIterator() {
            return null;
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return null;
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return null;
        }
    }

    static void 멀티플타입상속메서드() {
        List<String> i1 = new MyList<String, Integer>() {};
        List<String> i2 = new MyList<String, String>() {};
    }

    static void 타입추론_대신_명시적으로() {
        List<String> c = Collections.<String>emptyList();
    }

    static void 와일드카드() {

        List<?> list ; // 내가 이 타입을 모른다. 굳이 관심도 없다.
        List<? extends Object> list2 ; // Object 에 있는 기능만 사용하겠어
    }

    static void printList(List<Object> list) {
        list.forEach(s-> System.out.println(s));
    }

    static void printList2(List<?> list) {
        list.forEach(s-> System.out.println(s));
    }

    static void 위_두_코드의_차이점() {
        printList(Arrays.asList(1,2,3));
        printList2(Arrays.asList(1,2,3));

        List<Integer> list = Arrays.asList(1,2,3);
//        printList(list); // ==> 컴파일 에러. list 는 List<Object> 의 서브타입이 아니다.
        printList2(list);
    }

    public static void main(String[] args) {
//        new Hello<String>().method(3); // compile error
        new Hello<String>().method("!"); // String : Type Argument


        List list = new ArrayList<Integer>(); // List : Raw Type

        List<Integer> ints = Arrays.asList(1,2,3);
        List ints2 = ints;
        List<String> ints3 = ints2;
//        System.err.println(ints3.get(0)); // runtime error

        Integer i = 3;
        Number n = i;
        System.err.println("i="+i+",n="+n);

        List<Integer> is = Arrays.asList(1,2,3);
//        List<Number> ns = is; // compile err : incompatible types
        //즉 Type Parameter 의 상속 관계는 성립하지 않는다.

        List<Integer> is2 = Arrays.asList(1,2,3);
        System.err.println("=>"+is2.get(0));
    }
}
