package com.wallace.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
// Quando executar o delete ele muda o curso para Inactive
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' WHERE id = ?")
// Durante a execução do Where, busca apenas os cursos com status Active
@Where(clause = "status = 'Active'")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("_id")
  private Long id;

  @NotBlank
  @NotNull
  @Length(min = 5, max = 100)
  @Column(length = 100, nullable = false)
  private String name;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Back-end|Front-end|Big-data")
  @Column(length = 10, nullable = false)
  private String category;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Active|Inactive")
  @Column(length = 10, nullable = false)
  private String status = "Active";
}
