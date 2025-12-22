package org.example.expert.domain.search.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.search.dto.request.SearchRequest;
import org.example.expert.domain.search.dto.response.SearchSummaryDto;
import org.example.expert.domain.search.repository.SearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    @Transactional(readOnly = true)
    public Page<SearchSummaryDto> searchTodo(Pageable pageable, SearchRequest request) {
        return searchRepository.searchSummaryTodo(pageable, request);
    }
}
