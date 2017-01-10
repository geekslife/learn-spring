package tutorial;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by geekslife on 2017. 1. 10..
 */
public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<"+name+":"+age+">";
    }

    public int getAge() { return this.age; }

    public String getName() { return this.name; }

    public static void main(String[] args) {
        List<Person> roster = Arrays.asList( new Person("Bob", 19), new Person("Susan", 21),
                new Person("Derik",35));
        System.err.println("-------for");
        for ( Person p : roster ) {
            System.out.println("roster : " + p);
        }
        System.err.println("-------stream");
        roster.forEach(System.out::println);
        roster.stream().filter(p->{return p.age>30;}).forEach(System.err::println);
    }
}
