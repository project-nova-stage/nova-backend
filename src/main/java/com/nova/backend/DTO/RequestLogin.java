package com.nova.backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLogin {
    private String email;
    private String password;
}
