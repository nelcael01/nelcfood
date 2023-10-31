package com.nelcfood.model.entities;

import com.nelcfood.api.GroupsValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = GroupsValidation.EstadoId.class)
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;
}
