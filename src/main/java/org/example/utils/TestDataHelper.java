package org.example.utils;

import com.github.javafaker.Faker;

public class TestDataHelper {

    public static Faker getFaker(){
        return Faker.instance();
    }
}
