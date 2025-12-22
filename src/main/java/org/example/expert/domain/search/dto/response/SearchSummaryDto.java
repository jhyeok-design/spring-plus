package org.example.expert.domain.search.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchSummaryDto {

    public final String title;
    public final long managerCount;
    public final long commentCount;

}
