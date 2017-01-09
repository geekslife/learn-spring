package tutorial;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.Documented;

/**
 * Created by geekslife on 2017. 1. 9..
 */
@Generation3List.ClassPreamble(
    author = "Haeyoung",
    reviewers = {"Alice","Bob"}
)
public  class Generation3List {
    @Documented
    @interface ClassPreamble {
        String author();
        int currentRevision() default 1;
        String[] reviewers();
    }

    static class A {
        @NotNull
        String notNull;
        public A() {
            notNull = null;
        }
    }

    public static void main(String[] args) {
        A a = new A();
        System.err.println("a="+a.notNull);
    }
}
