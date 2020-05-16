package com.v1.CentralDeErros.models.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class ApplicationInstanceDTO {

    @NonNull
    private String applicationName;

    @NonNull
    private String instantiationDate;
}
