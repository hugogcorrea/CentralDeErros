package com.v1.CentralDeErros.models.DTOs;

import com.v1.CentralDeErros.Util.DateUtility;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.text.ParseException;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ApplicationInstanceDTO {

    @NonNull
    private String applicationName;

    @NonNull
    private String instantiationDate;
}
