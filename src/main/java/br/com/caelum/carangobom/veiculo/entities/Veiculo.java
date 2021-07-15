package br.com.caelum.carangobom.veiculo.entities;

import br.com.caelum.carangobom.marca.entities.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Year;

import static javax.persistence.GenerationType.IDENTITY;

@Data
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
    @NotBlank
    private Year ano;

    @Basic
    @Column
    @NotBlank
    private Long valor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Marca marca;
}
