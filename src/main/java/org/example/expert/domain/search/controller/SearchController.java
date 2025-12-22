package org.example.expert.domain.search.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.search.dto.request.SearchRequest;
import org.example.expert.domain.search.dto.response.SearchSummaryDto;
import org.example.expert.domain.search.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/api/search")
    public ResponseEntity<Page<SearchSummaryDto>> searchTodo(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @ModelAttribute SearchRequest request
            ){
        Pageable pageable =  PageRequest.of(page, size);
        return ResponseEntity.ok(searchService.searchTodo(pageable, request));
    }
}
