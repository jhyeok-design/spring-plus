package org.example.expert.domain.search.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.search.dto.request.SearchRequest;
import org.example.expert.domain.search.dto.response.SearchSummaryDto;
import org.example.expert.domain.search.dto.response.SearchUserResponse;
import org.example.expert.domain.search.repository.SearchRepository;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final SearchRepository searchRepository;
    private final UserRepository userRepository;

    public Page<SearchSummaryDto> searchTodo(Pageable pageable, SearchRequest request) {
        return searchRepository.searchSummaryTodo(pageable, request);
    }

    @Cacheable(value = "userCache", key = "'nickname:' + #nickname")
    public List<SearchUserResponse> searchUsers(String nickname) {
        return userRepository.findByNickname(nickname)
                .stream()
                .map(user -> new SearchUserResponse(user.getId(), user.getEmail(), user.getNickname()))
                .collect(Collectors.toList());
    }
}
