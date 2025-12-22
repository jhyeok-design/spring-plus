package org.example.expert.domain.search.repository;

import org.example.expert.domain.search.dto.request.SearchRequest;
import org.example.expert.domain.search.dto.response.SearchSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchCustomRepository {

    Page<SearchSummaryDto> searchSummaryTodo(Pageable pageable, SearchRequest request);

}
