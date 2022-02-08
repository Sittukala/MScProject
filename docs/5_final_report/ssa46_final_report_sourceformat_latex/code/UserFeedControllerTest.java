package com.buzzfeed.project.Controller;

import com.buzzfeed.project.Service.DNewsService;
import com.buzzfeed.project.Service.UserFeedService;
import com.buzzfeed.project.Service.UserService;
import com.buzzfeed.project.config.SecurityConfig;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.Source;
import com.buzzfeed.project.domain.User;
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
class UserFeedControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private UserFeedService userFeedService;


    @Test
    public void addLikesTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
//        List<String> likedList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.addLikesToFeed(1, "Health");
//        likedList.add("Health");


        //Update user after liketo set his likedlist
        user1.setLikedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(newsList, userService.findUser(1).getLikedList());

    }

    @Test
    public void addLikesFailTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        // List<String> likedList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.addLikesToFeed(1, "Health");
        // likedList.add("Health");


        //Failed to Update user after liketo set his likedlist
        //user1.setLikedList(newsList);
        //userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(null, userService.findUser(1).getLikedList());

    }


    //Favorite List
    @Test
    public void addFavoritesTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        List<String> favoritelist = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.addFavToFeed(1, "Health");
        favoritelist.add("Health");

        //Update user after liketo set his likedlist
        user1.setFavoriteList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(newsList, userService.findUser(1).getFavoriteList());

    }


    //Favorite Fail List
    @Test
    public void addFavoritesFailTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        List<String> favoritelist = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.addFavToFeed(1, "Health");
        favoritelist.add("Health");

        //Failed to Update user after liketo set his likedlist

        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(null, userService.findUser(1).getFavoriteList());

    }


    //Save News List
    @Test
    public void addSaveTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        List<String> savedList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.addSaveToFeed(1, "Health");
        savedList.add("Health");

        //Update user after liketo set his likedlist
        user1.setSavedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(newsList, userService.findUser(1).getSavedList());

    }

    //Save News Fail List
    @Test
    public void addSaveFailTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        List<String> savedList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.addSaveToFeed(1, "Health");
        savedList.add("Health");

        //Fail to Update user after liketo set his likedlist

        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(null, userService.findUser(1).getSavedList());

    }

    @Test
    public void removeLikesTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        //  List<String> likedList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        //When liked it would have been added
        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.removeUnlikes(1, "Health");
//        likedList.remove("Health");


        //Update user after liketo set his likedlist
        //On  unlike it would be removed on update user call
        newsList.remove(news);

        user1.setLikedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(0, userService.findUser(1).getLikedList().size());

    }

    @Test
    public void removeLikesFailTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();


        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        DNews news1 = new DNews();
        news1.setId(1002);
        news1.setAuthor("Ram");
        news1.setCategory("Sports");
        news1.setCountry("AR");
        news1.setTitle("Virat and team wins for India");
        news1.setSource(source);


        newsList.add(news);
        newsList.add(news1);

        //User Existence Test
        user1.setLikedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.removeUnlikes(1, "Health");
//        likedList.remove("Health");


        //failed to remove or update user after unlike news


        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(2, userService.findUser(1).getLikedList().size());


    }


    @Test
    public void removeFavoritesTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        //  List<String> likedList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        //When liked it would have been added
        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.removeUnlikes(1, "Health");
//        likedList.remove("Health");


        //Update user after liketo set his likedlist
        //On  unlike it would be removed on update user call
        newsList.remove(news);

        user1.setLikedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(0, userService.findUser(1).getLikedList().size());

    }

    @Test
    public void removeFavoritesFailTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();


        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        DNews news1 = new DNews();
        news1.setId(1002);
        news1.setAuthor("Ram");
        news1.setCategory("Sports");
        news1.setCountry("AR");
        news1.setTitle("Virat and team wins for India");
        news1.setSource(source);


        newsList.add(news);
        newsList.add(news1);

        //User Existence Test
        user1.setLikedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.removeUnlikes(1, "Health");
//        likedList.remove("Health");


        //failed to remove or update user after unlike news


        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(2, userService.findUser(1).getLikedList().size());


    }

    @Test
    public void deleteTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();
        //  List<String> likedList = new ArrayList<String>();

        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        //When liked it would have been added
        newsList.add(news);

        //User Existence Test
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.removeUnlikes(1, "Health");
//        likedList.remove("Health");


        //Update user after liketo set his likedlist
        //On  unlike it would be removed on update user call
        newsList.remove(news);

        user1.setLikedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(0, userService.findUser(1).getLikedList().size());

    }

    @Test
    public void deleteFailTest() throws Exception {

        User user1 = new User();
        user1.setUid(1);
        user1.setEmailId("user1@gmail.com");
        user1.setPhoneNumber("0993004922");
        user1.setPassword("hry12@S");
        user1.setUserName("user");
        List<String> preferredCategoryList = new ArrayList<String>();


        preferredCategoryList.add("Health");
        user1.setPreferredCategory(preferredCategoryList);
        user1.setGender("Male");
        user1.setLocation(("NZ"));
        user1.setFavoriteList(null);
        user1.setLikedList(null);
        user1.setSavedList(null);

        List<DNews> newsList = new ArrayList<>();
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

        DNews news1 = new DNews();
        news1.setId(1002);
        news1.setAuthor("Ram");
        news1.setCategory("Sports");
        news1.setCountry("AR");
        news1.setTitle("Virat and team wins for India");
        news1.setSource(source);


        newsList.add(news);
        newsList.add(news1);

        //User Existence Test
        user1.setLikedList(newsList);
        userService.addUser(user1);
        when(userService.findUser(1)).thenReturn(user1);
        assertEquals("user", userService.findUser(1).getUserName());

        //Algorithm feed service test
        userFeedService.removeUnlikes(1, "Health");
//        likedList.remove("Health");


        //failed to remove or update user after unlike news


        when(userService.findUser(1)).thenReturn(user1);
        assertEquals(2, userService.findUser(1).getLikedList().size());


    }


}