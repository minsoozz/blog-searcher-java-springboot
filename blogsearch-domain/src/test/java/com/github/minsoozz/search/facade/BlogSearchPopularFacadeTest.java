package com.github.minsoozz.search.facade;

import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.domain.blog.BlogSearch.Document;
import com.github.minsoozz.search.domain.blog.BlogSearch.Meta;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.service.BlogSearchService;
import com.github.minsoozz.search.service.PopularSearchService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlogSearchPopularFacadeTest {

    @InjectMocks
    BlogSearchPopularFacade blogSearchPopularFacade;

    @Mock
    BlogSearchService blogSearchService;

    @Mock
    PopularSearchService popularSearchService;

    @Test
    void 블로그_도메인을_반환한다() {
        // given
        final String query = "minsoo";
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

        // when
        Mockito.when(blogSearchService.getBlogSearchResults(blogSearchRequest))
            .thenReturn(BlogSearch.of(List.of(document), meta));

        // then
        BlogSearch blogSearch = blogSearchPopularFacade.searchBlog(blogSearchRequest);
        Assertions.assertEquals(blogName, blogSearch.documents().get(0).blogName());
        Assertions.assertEquals(contents, blogSearch.documents().get(0).contents());
        Assertions.assertEquals(thumbnail, blogSearch.documents().get(0).thumbnail());
        Assertions.assertEquals(title, blogSearch.documents().get(0).title());
        Assertions.assertEquals(url, blogSearch.documents().get(0).url());
    }
}