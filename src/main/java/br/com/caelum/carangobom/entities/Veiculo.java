package br.com.caelum.carangobom.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    @Basic
    @Column
    @NotBlank
    private String modelo;

    @Basic
    @Column
    @NotNull
    private int ano;

    @Basic
    @Column
    @NotNull
    private Long valor;

    @ManyToOne
    private Marca marca;
}
