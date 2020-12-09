package com.example.springdatapartial.config;

import com.example.springdatapartial.entity.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DummyData {

    public static List<Person> getPeople() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = DummyData.class.getClassLoader();
        //using file
        File file = new File(classLoader.getResource("people.json").getFile());
        List<Person> personList = objectMapper.readValue(file, new TypeReference<List<Person>>() {
        });
        return personList;
    }
}