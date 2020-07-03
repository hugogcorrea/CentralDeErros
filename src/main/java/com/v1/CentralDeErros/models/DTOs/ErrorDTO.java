package com.v1.CentralDeErros.models.DTOs;


import com.v1.CentralDeErros.enums.ErrorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    @NonNull
    private String description;

    @NonNull
    private String registrationDate;

    @NonNull
    private String type;
}
