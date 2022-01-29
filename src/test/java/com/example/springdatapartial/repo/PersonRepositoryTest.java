package com.example.springdatapartial.repo;

import com.example.springdatapartial.dto.PersonPartialDTO;
import com.example.springdatapartial.dto.PersonView;
import com.example.springdatapartial.entity.Person;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;

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
        final List<PersonPartialDTO> byAge = personRepository.findByAge(55);
        for (PersonPartialDTO dto : byAge) {
            System.out.println(dto);
        }
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

    @Test
    void findTop() {
        System.out.println(personRepository.readTopByOrderByIdDesc());
        System.out.println(personRepository.getTopByOrderByIdAsc());
    }

    /**
     * exact match
     */
    @Test
    void findByFirstNameLike() {
        System.out.println(personRepository.findByFirstNameLike("Carly"));
    }

    /**
     * containing  match
     */
    @Test
    void findByFirstNameContaining() {
        System.out.println(personRepository.findByFirstNameContaining("Carly"));
    }


    @Test
    void findByLastName() {
        final PersonPartialDTO byLastName = personRepository.findByLastName("Marling", PersonPartialDTO.class);
        System.out.println(byLastName);
    }

    @Test
    void readTop3ByOrderByIdDesc() {
        personRepository.readTop3ByOrderByIdDesc().forEach(System.out::println);

    }

    @Test
    void getTop5ByOrderByIdAsc() {
        personRepository.getTop5ByOrderByIdAsc().forEach(System.out::println);
    }

    @Test
    void findByExample() {
        Person person = Person.builder().firstName("Harmon").lastName("Marling").age(38).build();
        Example example = Example.of(person);
        System.out.println(personRepository.findOne(example));
    }

    @Test
    void findPersonByNamedParams() {
        System.out.println(personRepository.findPersonByNamedParams("Dallas", 61));
    }

    @Test
    void findPersonByNamedParams1() {
        System.out.println(personRepository.findPersonByNamedParams1("Dallas", 61));
    }

    @Test
    void findPersonByIndexedParams() {
        System.out.println(personRepository.findPersonByIndexedParams("Dallas", 61));
    }

    @Test
    void findPersonByNativeQuery(){
        System.out.println(personRepository.findPersonByNativeQuery("Dallas"));
    }

    @Test
    void findPersonInId(){
        System.out.println(personRepository.findPersonInId(Arrays.asList(1,2)));
    }

    @Test
    void getByIdAndEmail(){
        List<Object[]> andEmail = personRepository.getByIdAndEmail();
        andEmail.forEach(val->{
            System.out.print(val[0]);
            System.out.println(" "+val[1]);
        });
    }
    @Test
    void getById(){
        Object[] andEmail = personRepository.getById(1);
        System.out.print(andEmail[0]);
    }

    @Test
    void updatePerson(){;
        System.out.println(personRepository.updatePerson("Hemant", 1));
        System.out.println(personRepository.findById(1));
    }
}