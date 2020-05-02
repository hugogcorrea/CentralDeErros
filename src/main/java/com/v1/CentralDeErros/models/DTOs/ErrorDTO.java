package com.v1.CentralDeErros.models.DTOs;

import com.v1.CentralDeErros.Util.DateUtility;
import com.v1.CentralDeErros.enums.ErrorType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.text.ParseException;
import java.util.Date;


@Getter
@RequiredArgsConstructor
public class ErrorDTO {

    @NonNull
    private String description;

    @NonNull
    private String registrationDate;

    private ErrorType type;

    public void setType(ErrorType type) {
        this.type = type;
    }
}
