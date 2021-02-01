package ru.otus;

import com.google.common.collect.ImmutableList;
import com.google.common.base.Strings;


public class HelloOtus {
    public static void main(String[] args) {
        System.out.println("Hello teachers of OTUS'team.\n");
        System.out.println("The reaching for knowledge, salute You!\n");
        /*
        java-2020-12 (names = nic from Slack of course who have at least one message)
         */
        ImmutableList.of(
                "Anatolii",
                "Andrey Ilyusin",
                "Anastasia",
                "Dmitrii Berezuckii",
                "Fadeew Konstantin",
                "Kirill",
                "Sergey Piskov",
                "Roman Skidan",
                "Vitali Kuchynski",
                "Vladislav D",
                "Vladimit Golubkov",
                "YuryS",
                "Zhakarik")
                .stream()
                .map(name -> Strings.padEnd(name, name.length() + 1, '.'))
                .forEach(System.out::println);
    }

}
