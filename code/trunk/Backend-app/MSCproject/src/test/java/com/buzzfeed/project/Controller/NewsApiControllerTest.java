package com.buzzfeed.project.Controller;

import com.buzzfeed.project.Service.DNewsService;
import com.buzzfeed.project.config.SecurityConfig;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
class NewsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DNewsService dNewsService;

    @Test
    public void findNewsTest() throws Exception {

        Source source = new Source();
        source.setId("111");
        source.setName("Raguram Source");
        DNews news = new DNews();
        news.setId(1001);
        news.setAuthor("Raguram");
        news.setCategory("Health");
        news.setCountry("NZ");
        news.setTitle("Healthy atmosphere");
        news.setSource(source);

        when(dNewsService.findNews(1001)).thenReturn(news);

        assertEquals("Health", dNewsService.findNews(1001).getCategory());
    }

    @Test
    public void findNewsFail() throws Exception {
        when(dNewsService.findNews(1001)).thenReturn(null);

        assertEquals(null, dNewsService.findNews(1001));
    }

    @Test
    public void getLocNewsTest() throws Exception {

        Source source = new Source();
        source.setId("111");
        source.setName("Raguram Source");
        DNews news = new DNews();
        news.setId(1001);
        news.setAuthor("Raguram");
        news.setCategory("Health");
        news.setCountry("NZ");
        news.setTitle("Healthy atmosphere");
        news.setSource(source);

        List<DNews> list = new ArrayList<>();
        list.add(news);

        when(dNewsService.findNewsByCountry("NZ")).thenReturn((list));

        assertEquals(list, dNewsService.findNewsByCountry("NZ"));
    }

    @Test
    public void getLocNewsTestFail() throws Exception {
        when(dNewsService.findNewsByCountry("NZ")).thenReturn(null);

        assertEquals(null, dNewsService.findNewsByCountry("NZ"));
    }

    @Test
    public void getNewsTest() throws Exception {

        Source source = new Source();
        source.setId("111");
        source.setName("Raguram Source");
        DNews news = new DNews();
        news.setId(1001);
        news.setAuthor("Raguram");
        news.setCategory("Health");
        news.setCountry("NZ");
        news.setTitle("Healthy atmosphere");
        news.setSource(source);

        List<DNews> list = new ArrayList<>();
        list.add(news);

        when(dNewsService.findNewsByCountryCategory("NZ", "Health")).thenReturn((list));

        assertEquals(list, dNewsService.findNewsByCountryCategory("NZ", "Health"));
    }

    @Test
    public void getNewsTestFail() throws Exception {
        when(dNewsService.findNewsByCountryCategory("NZ", "Health")).thenReturn(null);

        assertEquals(null, dNewsService.findNewsByCountryCategory("NZ", "Health"));
    }


}