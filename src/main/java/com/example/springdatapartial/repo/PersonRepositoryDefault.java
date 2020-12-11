package com.example.springdatapartial.repo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
//Used for code separation to defined custom queries
public interface PersonRepositoryDefault {

  @Query("SELECT DISTINCT(per.age) from Person per")
  List<Integer> findAllDistinctByAge();
}
