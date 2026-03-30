package com.nova.backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseOBJ {
    private String Response;
    private Object record;

    public ResponseOBJ(String Response, Object record) {
        this.Response = Response;
        this.record = record;
    }



}
