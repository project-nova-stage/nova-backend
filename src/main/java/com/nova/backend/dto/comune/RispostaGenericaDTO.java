package com.nova.backend.dto.comune;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RispostaGenericaDTO {
    private String message;
    private Object data;

    public RispostaGenericaDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
