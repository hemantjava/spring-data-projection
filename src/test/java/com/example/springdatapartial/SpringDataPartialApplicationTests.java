package com.example.springdatapartial;

import com.example.springdatapartial.entity.Person;
import com.example.springdatapartial.repo.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
class SpringDataPartialApplicationTests {

	@Autowired
	private PersonRepository personRepository;

	@Test
	void contextLoads() {
		Person person = Person.builder()
				.firstName("John")
				.lastName("Doe")
				.gender("M")
				.email("jds@gmail.com")
				.age(32).build();
		Person save = personRepository.save(person);
		person.setAge(35);//update operation
		System.out.println(save);
	}

	@ParameterizedTest
	@ValueSource(ints = {200})
	void DynamicUpdate(int id) {
		personRepository.findById(id).ifPresentOrElse(person -> {
			person.setFirstName("Hemant");
			Person save = personRepository.save(person);
			System.out.println(save);
		}, ()-> {throw new RuntimeException("Person not found");});
	}

}
