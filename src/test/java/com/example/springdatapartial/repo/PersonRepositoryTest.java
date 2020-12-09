package com.example.springdatapartial.repo;

import com.example.springdatapartial.dto.PersonPartialDTO;
import com.example.springdatapartial.dto.PersonView;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Test
  void getIdAndEmail() {
    final List<Object[]> andEmail = personRepository.getIdAndEmail();
    andEmail.stream().limit(10).forEach(x -> {
      System.out.println(x[0] + " " + x[1]);
    });
  }

  @Test
  void findByAges() {
    final List<PersonPartialDTO> byAge = personRepository.findByAges(55);
    for (PersonPartialDTO dto : byAge) {
      System.out.println(dto);
    }

  }

  @Test
  void findByAge() {
    final List<PersonPartialDTO> byAge = personRepository.findByAges(55);
    for (PersonPartialDTO dto : byAge) {
      System.out.println(dto);
    }

  }

  @Test
  void findViewByFirstName(){
    final PersonView view = personRepository.findByFirstName("Charley");
    System.out.println(view.getFirstName()+" "+view.getLastName());
  }
}