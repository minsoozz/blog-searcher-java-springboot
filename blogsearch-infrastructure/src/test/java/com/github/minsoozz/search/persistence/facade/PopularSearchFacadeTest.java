package com.github.minsoozz.search.persistence.facade;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.service.PopularSearchQuery;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PopularSearchFacadeTest {

    @Autowired
    private PopularSearchFacade popularSearchFacade;

    @Autowired
    private PopularSearchQuery popularSearchQuery;

    private final String query = "minsoo";

    @Test
    void 동시에_100명의_사용자가_같은_키워드로_검색한다() throws InterruptedException {
        // given
        final int peopleCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(peopleCount);
        final CountDownLatch countDownLatch = new CountDownLatch(peopleCount);

        // when
        for (int i = 0; i < peopleCount; i++) {
            executorService.execute(() -> {
                popularSearchFacade.savePopularSearch(query);
                countDownLatch.countDown();
            });
        }

        // then
        countDownLatch.await();
        PopularSearchJpaEntity popularSearchJpaEntity = popularSearchQuery.findByKeyword(query).orElseThrow();
        Assertions.assertEquals(peopleCount, popularSearchJpaEntity.getCount());
    }
}