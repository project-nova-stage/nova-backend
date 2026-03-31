package com.nova.backend.dto.catalogo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO standard per la gestione delle categorie dei prodotti.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO implements Serializable {
    
    private Long id;

    @NotBlank(message = "Il nome della categoria è obbligatorio")
    private String nome;
}
