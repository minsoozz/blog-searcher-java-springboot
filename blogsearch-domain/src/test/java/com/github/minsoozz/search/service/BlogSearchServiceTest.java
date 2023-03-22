package com.github.minsoozz.search.service;

import static org.mockito.Mockito.when;

import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.external.caller.BlogApiCaller;
import com.github.minsoozz.search.external.dto.kakao.KakaoApiResponseDto;
import com.github.minsoozz.search.external.dto.kakao.KakaoApiResponseDto.Document;
import com.github.minsoozz.search.external.dto.kakao.KakaoApiResponseDto.Meta;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlogSearchServiceTest {

    @InjectMocks
    BlogSearchService searchService;

    @Mock
    BlogApiCaller blogApiCaller;

    @Test
    void 블로그_검색_결과를_반환한다() {
        // given
        final String query = "민수";
        final int page = 1;
        final String sort = "recency";
        final BlogSearchRequest blogSearchRequest = new BlogSearchRequest(query, page, sort);
        final String blogName = "민수의 자바";
        final String contents = "테스트 코드는 이렇게 작성합니다 ~~";
        final LocalDateTime dateTime = LocalDateTime.now();
        final String thumbnail = "null";
        final String title = "테스트 코드 작성 방법 가이드";
        final String url = "https://github.com/minsoozz";
        final boolean isEnd = true;
        final int pageableCount = 10;
        int totalCount = 100;

        Document document = new Document(blogName, contents, dateTime, thumbnail, title, url);
        Meta meta = new Meta(isEnd, pageableCount, totalCount);
        KakaoApiResponseDto kakaoApiResponseDto = new KakaoApiResponseDto(List.of(document), meta);
        // when
        when(blogApiCaller.searchBlogs(blogSearchRequest.query(), blogSearchRequest.page(), blogSearchRequest.sort()))
            .thenReturn(kakaoApiResponseDto);

        // then
        BlogSearch blogSearchResults = searchService.getBlogSearchResults(blogSearchRequest);
        Assertions.assertEquals(blogName, blogSearchResults.documents().get(0).blogname());
        Assertions.assertEquals(contents, blogSearchResults.documents().get(0).contents());
        Assertions.assertEquals(thumbnail, blogSearchResults.documents().get(0).thumbnail());
        Assertions.assertEquals(title, blogSearchResults.documents().get(0).title());
        Assertions.assertEquals(url, blogSearchResults.documents().get(0).url());
    }
}