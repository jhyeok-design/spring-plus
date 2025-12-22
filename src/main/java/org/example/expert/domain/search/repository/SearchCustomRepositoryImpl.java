package org.example.expert.domain.search.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.search.dto.request.SearchRequest;
import org.example.expert.domain.search.dto.response.SearchSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.comment.entity.QComment.comment;
import static org.example.expert.domain.manager.entity.QManager.manager;

@RequiredArgsConstructor
public class SearchCustomRepositoryImpl implements SearchCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SearchSummaryDto> searchSummaryTodo(Pageable pageable, SearchRequest request){
        List<SearchSummaryDto> result = queryFactory
                .select(Projections.constructor(SearchSummaryDto.class,
                        todo.title,
                        manager.id.countDistinct(),
                        comment.id.countDistinct()
                ))
                .from(todo)
                .leftJoin(todo.managers, manager)
                .leftJoin(todo.comments, comment)
                .groupBy(todo.id)
                .where(
                        titleContains(request.getTitle()),
                        managerNicknameContains(request.getManagerNickname()),
                        createdAtDateCheck(request.getStartDateTime(), request.getEndDateTime())
                )
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(todo.id.countDistinct())
                .from(todo)
                .leftJoin(todo.managers, manager)
                .where(
                        titleContains(request.getTitle()),
                        managerNicknameContains(request.getManagerNickname()),
                        createdAtDateCheck(request.getStartDateTime(), request.getEndDateTime())
                )
                .fetchOne();

        return new PageImpl<>(result, pageable, total == null ? 0 : total);
    }

    private BooleanExpression createdAtDateCheck(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime != null && endDateTime!= null ? todo.createdAt.between(startDateTime, endDateTime) : null;
    }

    private BooleanExpression titleContains(String title) {
        return title != null ? todo.title.contains(title) : null;
    }

    private BooleanExpression managerNicknameContains(String nickname) {
        return nickname != null ? manager.user.nickname.contains(nickname) : null;
    }

}
