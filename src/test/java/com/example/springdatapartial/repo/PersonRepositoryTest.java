package com.example.springdatapartial.repo;

import com.example.springdatapartial.dto.PersonPartialDTO;
import com.example.springdatapartial.dto.PersonView;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

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
    byAge.stream().limit(20).forEach(System.out::println);
    System.out.println("--------------------------------------");
    Supplier<Stream<List<PersonPartialDTO>>> supplier = () -> Stream.of(byAge);
    final List<String> firstName = supplier.get().findFirst().get().stream()
        .map(PersonPartialDTO::getFirstName).distinct().collect(Collectors.toList());
    final List<String> lastName = supplier.get().findFirst().get().stream()
        .map(PersonPartialDTO::getLastName).distinct().collect(Collectors.toList());

    System.out.println(firstName.size()+" "+firstName);
    System.out.println(lastName.size()+"  "+lastName);
  }

  @Test
  void findViewByFirstName() {
    final PersonView view = personRepository.findByFirstName("Charley");
    System.out.println(view.getFirstName() + " " + view.getLastName());
  }

  @Test
  void findByAgePageable() {
    final PageRequest request = PageRequest.of(1, 5);
    final Page<PersonPartialDTO> byAge = personRepository.findByAge(55, request);
    byAge.getContent().forEach(System.out::println);
  }

  @Test
  void findByAgeSlice() {
    Pageable request = PageRequest.of(1, 5);

    while (true) {
      final Slice<PersonPartialDTO> byAge = personRepository.findByAgeLessThan(55, request);
      final int number = byAge.getNumber();
      final int numberOfElements = byAge.getNumberOfElements();
      final int size = byAge.getSize();
      System.out.println(number + " " + numberOfElements + "  " + size);
      final List<PersonPartialDTO> content = byAge.getContent();
      content.forEach(System.out::println);
      if (!byAge.hasNext()) {
        break;
      }
      request = byAge.nextPageable();


    }
  }

  @Test
  void findDistinctByAge() {
    final List<Integer> distinctAge = personRepository.findAllDistinctByAge();
    System.out.println(distinctAge);

  }
}