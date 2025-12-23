package org.example.expert.domain.log.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogRequestDto {

    private String requestType;
    private String requestContent;
    }




