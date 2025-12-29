package org.example.expert.domain.user.repository;

import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserBatchRepositoryTest {

    @Autowired
    private UserBatchRepository userBatchRepository;

    private static final int TOTAL_USERS = 5_000_000;
    private static final int BATCH_SIZE = 10_000;

    @Test
    void bulkInsert_Users_Test(){

        System.out.println("유저 생성 시작");

        int count = 0;

        for (int batchStart = 0; batchStart < TOTAL_USERS; batchStart += BATCH_SIZE) {
            int batchEnd = Math.min(batchStart + BATCH_SIZE, TOTAL_USERS);
            List<User> batch = new ArrayList<>(batchEnd - batchStart);

            for (int i = batchStart; i < batchEnd; i++) {

                String email = "user" + i + "@test.com";
                String nickname = "user" + (i + 1);
                String password = "password" + i;

                batch.add(new User(email, password, UserRole.USER, nickname));
                count++;
            }

            userBatchRepository.batchInsert(batch);
            System.out.println("Insert Batch User: " + count);
        }

        System.out.println("500만 유저 생성 완료");
    }

}