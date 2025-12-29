package org.example.expert.domain.user.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expert.domain.user.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserBatchRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void batchInsert(List<User> userList) {

        log.info("batchInsert start");

        String sql = "insert into users (email,  password, user_role, nickname) values (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, userList, 10000,
                (ps, user) -> {
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getUserRole().name());
                    ps.setString(4, user.getNickname());
                });

        log.info("BatchInsert end");
    }
}
