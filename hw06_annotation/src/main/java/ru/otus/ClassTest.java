package ru.otus;

public class ClassTest {
    private int c;

    @Before
    private void before1() {
        c = 100;
        System.out.println("before1");
    }

    @Before
    private void before2(String val) {
        System.out.println("before2");
    }

    @Before
    public void before3(String val, int i, boolean b) {
        System.out.println("before3");
    }


    @Test()
    public void publicTest1(Integer a) {
        System.out.println("publicTest1");
    }

    @Test()
    public void publicTest2(boolean b, char c, byte bt, short sh, int i, long lon, float fl, double d, String v) {
        System.out.println("publicTes2");
    }

    @Test()
    private void privateTest3(int a) {
        System.out.println("privateTest3");
        c = 10 / a;
    }

    public void method1() {
        System.out.println("method1");
    }

    public void method2() {
        System.out.println("method2");
    }


    @After
    public void after1() {
        System.out.println("after1");
    }

    @After
    private void after2(String a, String b) {
        System.out.println("after2");
    }

    @After
    public void after3(boolean bl) {
        System.out.println("after3");
    }

}
