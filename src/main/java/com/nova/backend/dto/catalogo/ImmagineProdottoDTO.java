package com.nova.backend.dto.catalogo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImmagineProdottoDTO {
    private Long id;
    private String urlImmagine;
    private Boolean principale;
}
