package com.v1.CentralDeErros.models.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class ServerDTO {

    @NonNull
    private String serverName;

    @NonNull
    private String serverLocation;
}
