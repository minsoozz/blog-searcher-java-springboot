package com.github.minsoozz.search.blogsearch.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.domain.blog.BlogSearch.Document;
import com.github.minsoozz.search.domain.blog.BlogSearch.Meta;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.external.enums.ApiQueryParameterKey;
import com.github.minsoozz.search.facade.BlogSearchPopularFacade;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(BlogSearchRestController.class)
class BlogSearchRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogSearchPopularFacade blogSearchPopularFacade;

    @Test
    void 블로그를_검색한다() throws Exception {
        //given
        final String uri = "/api/v1/blog/search";
        final String query = "minsoo";
        final int page = 1;
        final String sort = "recency";
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
        BlogSearch blogSearch = new BlogSearch(List.of(document), meta);

        MultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<>();
        parameterMap.add(ApiQueryParameterKey.KAKAO.getQuery(), query);
        parameterMap.add(ApiQueryParameterKey.KAKAO.getPage(), String.valueOf(page));
        parameterMap.add(ApiQueryParameterKey.KAKAO.getSort(), sort);

        final BlogSearchRequest blogSearchRequest = new BlogSearchRequest(query, page, sort);
        given(blogSearchPopularFacade.searchBlog(blogSearchRequest)).willReturn(blogSearch);
        mockMvc.perform(get(uri).params(parameterMap))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message").value("success"))
            .andExpect(jsonPath("$.data.documents[0].blogname").value("민수의 자바"))
            .andExpect(jsonPath("$.data.documents[0].contents").value("테스트 코드는 이렇게 작성합니다 ~~"))
            .andExpect(jsonPath("$.data.documents[0].thumbnail").value("null"))
            .andExpect(jsonPath("$.data.documents[0].title").value("테스트 코드 작성 방법 가이드"))
            .andExpect(jsonPath("$.data.documents[0].url").value("https://github.com/minsoozz"))
            .andExpect(jsonPath("$.data.meta.isEnd").value(true))
            .andExpect(jsonPath("$.data.meta.pageableCount").value(10))
            .andExpect(jsonPath("$.data.meta.totalCount").value(100))
            .andDo(print());
    }
}