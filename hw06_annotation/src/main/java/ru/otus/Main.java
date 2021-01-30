package ru.otus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/*
Домашнее задание
Написать свой тестовый фреймворк.
Поддержать свои аннотации @Test, @Before, @After.
Запускать вызовом статического метода с именем класса с тестами.
	Т.е. надо сделать:
	1) создать три аннотации - @Test, @Before, @After.
	2) Создать класс-тест, в котором будут методы, отмеченные аннотациями.
	3) Создать "запускалку теста". На вход она должна получать имя класса с тестами,
	    в котором следует найти и запустить методы отмеченные аннотациями и пункта 1.
	4) Алгоритм запуска должен быть следующий::
		метод(ы) Before
		текущий метод Test
		метод(ы) After
	для каждой такой "тройки" надо создать СВОЙ объект класса-теста.
	5) Исключение в одном тесте не должно прерывать весь процесс тестирования.
	6) На основании возникших во время тестирования исключений вывести статистику выполнения тестов
		(сколько прошло успешно, сколько упало, сколько было всего)
*/

public class Main {
    public static void main(String[] args) throws Exception {
        runTest("ru.otus.ClassTest");
    }

    private static void runTest(String testClassName) throws Exception {
        Class<?> clazz = Class.forName(testClassName);
        ArrayList<Method> testMethods = new ArrayList<>();
        ArrayList<Method> beforeMethods = new ArrayList<>();
        ArrayList<Method> afterMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
        int testCount = 0;          // Общее число тестов
        int testSuccess = 0;        // Число успешных тестов
        int testNotSuccess = 0;     // Число упавших тестов тестов
        for (Method method : testMethods) {
            testCount++;
            if (classMethodTest(clazz, method, beforeMethods, afterMethods)) {
                testSuccess++;
            } else {
                testNotSuccess++;
            }
        }
        System.out.println("----------------------------------------------------");
        System.out.println("   Общее число тестов: " + testCount);
        System.out.println("Число успешных тестов: " + testSuccess);
        System.out.println(" Число упавших тестов: " + testNotSuccess);
        System.out.println("----------------------------------------------------");
    }

    private static boolean classMethodTest(Class<?> clazz,
                                           Method methodTest,
                                           ArrayList<Method> beforeMethods,
                                           ArrayList<Method> afterMethods) throws Exception {
        Object testObj = clazz.getDeclaredConstructor().newInstance();
        for (Method beforeMethod : beforeMethods) {
            if (!(callMethod(beforeMethod, testObj))) return false;
        }
        if (!(callMethod(methodTest, testObj))) return false;
        for (Method afterMethod : afterMethods) {
            if (!(callMethod(afterMethod, testObj))) return false;
        }
        return true;
    }

    private static boolean callMethod(Method method, Object testObj) throws Exception {
        if (Modifier.isPrivate(method.getModifiers())) {
            method.setAccessible(true); // каждый метод делаем public
        }
        try {
            method.invoke(testObj, getMethodParams(method));
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    private static Object[] getMethodParams(Method method) throws ClassNotFoundException {
        Object[] param = null;
        int lenParams = method.getParameterCount();
        if (lenParams > 0) {
            param = new Object[lenParams];
            Class<?> string = Class.forName("java.lang.String");
            for (int i = 0; i < lenParams; i++) {
                if (method.getParameterTypes()[i].isAssignableFrom(string)) {
                    param[i] = "KIRILL";
                } else {
                    switch (method.getParameterTypes()[i].toString()) {
                        case ("boolean"):
                            param[i] = false;
                            break;
                        case ("char"):
                            param[i] = ';';
                            break;
                        case ("byte"):
                            param[i] = (byte) 0;
                            break;
                        case ("short"):
                            param[i] = (short) 0;
                            break;
                        default:
                            param[i] = 0;
                    }
                }
            }
        }
        return param;
    }


}
