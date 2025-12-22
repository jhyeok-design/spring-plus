package org.example.expert.domain.search.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private String title;
    private String managerNickname;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
