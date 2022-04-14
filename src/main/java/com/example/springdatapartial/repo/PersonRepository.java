package com.example.springdatapartial.repo;

import com.example.springdatapartial.dto.PersonPartialDTO;
import com.example.springdatapartial.dto.PersonView;
import com.example.springdatapartial.entity.Person;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryDefault {

    List<PersonPartialDTO> findByFirstNameLike(String firstName);

    List<PersonPartialDTO> findByFirstNameContaining(String firstName);


    /**
     * If not following Spring data naming convention like used findByAges instead of findByAge the
     * required fully path of PersonPartialDTO as followed by filled constructor
     *
     */
    @Query("SELECT new com.example.springdatapartial.dto.PersonPartialDTO(p.firstName,p.lastName) FROM Person p")
    List<PersonPartialDTO> findByAges(int age);

    /**
     * not required fully path of PersonPartialDTO
     *
     */
    List<PersonPartialDTO> findByAge(int age);

    @Query("SELECT p.id,p.email FROM Person p")
    List<Object[]> getIdAndEmail();

    @Query("SELECT p.id,p.email FROM Person p")
    List<Object[]> getByIdAndEmail();

    Object[] getById(int id);

    PersonView findByFirstName(String firstName);

    Page<PersonPartialDTO> findByAge(int age, Pageable pageable);

    Slice<PersonPartialDTO> findByAgeLessThan(int age, Pageable pageable);

    /**
     * Named Param
     */
    @Query("SELECT u FROM Person u WHERE u.firstName = :firstName and u.age = :age")
    Person findPersonByNamedParams(
            @Param("firstName") String firstName,
            @Param("age") Integer age);

    //OR if param name same as in query
    @Query("SELECT u FROM Person u WHERE u.firstName = :firstName and u.age = :age")
    Person findPersonByNamedParams1(
            String firstName,
            Integer age);

    /**
     * index Param
     */
    @Query("SELECT u FROM Person u WHERE u.firstName = ?1 and u.age = ?2")
    Person findPersonByIndexedParams(String firstName, Integer age);

    @Query(value = "SELECT * FROM Person p WHERE p.first_name = :firstName", nativeQuery = true)
    Person findPersonByNativeQuery(String firstName);

    @Query("select p from Person p where p.id in ?1")
    List<Person> findPersonInId(List<Integer> ids);

    /** Madifying annatation is used to DDL operations
     */
    @Modifying
    @Query("update Person p set p.firstName = :firstName where p.id = :id")
    int updatePerson(String firstName, Integer id);

    @Override
    Page<Person> findAll(Pageable pageable);
}
