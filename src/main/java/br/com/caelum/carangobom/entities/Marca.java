package br.com.caelum.carangobom.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    @Basic
    @Column
    @NotBlank
    private String nome;
}
