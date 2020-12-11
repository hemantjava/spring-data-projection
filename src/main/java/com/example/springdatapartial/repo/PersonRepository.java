package com.example.springdatapartial.repo;

import com.example.springdatapartial.dto.PersonPartialDTO;
import com.example.springdatapartial.dto.PersonView;
import com.example.springdatapartial.entity.Person;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> ,PersonRepositoryDefault{

  /**
   * If not following Spring data naming convention like used findByAges instead of findByAge the
   * required fully path of PersonPartialDTO as followed by filled constructor
   *
   * @param age
   * @return
   */
  @Query("SELECT new com.example.springdatapartial.dto.PersonPartialDTO(p.firstName,p.lastName) FROM Person p")
  List<PersonPartialDTO> findByAges(int age);

  /**
   * not required fully path of PersonPartialDTO
   *
   * @param age
   * @return
   */
  List<PersonPartialDTO> findByAge(int age);

  @Query("SELECT p.id,p.email FROM Person p")
  List<Object[]> getIdAndEmail();

  Object[] getById(int id);

  PersonView findByFirstName(String firstName);

  Page<PersonPartialDTO> findByAge(int age, Pageable pageable);

  Slice<PersonPartialDTO> findByAgeLessThan(int age, Pageable pageable);

}
