package com.example.springdatapartial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
public class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String firstName;
   private String lastName;
   private String email;
   private String gender;
   private Integer age;

}