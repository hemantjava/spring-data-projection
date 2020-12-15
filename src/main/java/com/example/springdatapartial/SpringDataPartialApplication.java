package com.example.springdatapartial;

import com.example.springdatapartial.config.DummyData;
import com.example.springdatapartial.entity.Person;
import com.example.springdatapartial.repo.PersonRepository;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class SpringDataPartialApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataPartialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Person> personList = DummyData.getPeople();
		final List<Person> list = personRepository.saveAll(personList);
		log.info("Record inserted : {}",list.size());
	}
}
