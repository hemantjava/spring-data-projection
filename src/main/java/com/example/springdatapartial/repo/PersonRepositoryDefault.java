package com.example.springdatapartial.repo;

import com.example.springdatapartial.dto.PersonPartialDTO;
import com.example.springdatapartial.entity.Person;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
//Used for code separation to defined custom queries
public interface PersonRepositoryDefault {

  @Query("SELECT DISTINCT(per.age) from Person per")
  List<Integer> findAllDistinctByAge();

  Person readTopByOrderByIdDesc();

  Person getTopByOrderByIdAsc();

  List<Person> readTop3ByOrderByIdDesc();

  List<Person> getTop5ByOrderByIdAsc();

  /**
   * dynamice projection
   * @param lastName
   * @param type
   * @param <T>
   * @return
   */
  <T> T findByLastName(String lastName, Class<T> type);
}
