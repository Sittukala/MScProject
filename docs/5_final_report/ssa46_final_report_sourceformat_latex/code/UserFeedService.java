package com.buzzfeed.project.Service;

import com.buzzfeed.project.Repository.UserFeedRepository;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserFeedService {

    private static final String[] HEADERS = {"Id", "Weight", "Category"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);
    @Autowired
    private UserService userService;

    @Autowired
    private UserFeedRepository userFeedRepository;

    @Autowired
    private DNewsService dNewsService;

    //Adds likes to users and updates that news category weight to 1.0
    public void addLikesToFeed(Integer uid, String categoryName) {

        Double weightByPreferences = 0.0;
        User user = userService.findUser(uid);
        HashMap<String, Double> categorymap = new HashMap<>();
        categorymap.putAll(user.getMap());

        List<String> likedList = new ArrayList<>();
        likedList.add(categoryName);
        for (Object category : likedList) {

            categorymap.put(category.toString(), user.getMap().get(category) + 1.0);
            user.setMap(categorymap);


        }

        userService.addUser(user);
    }

    //Removes likes from users and updates that news category weight
    public void removeUnlikes(Integer uid, String categoryName) {

        Double weightByPreferences = 0.0;
        User user = userService.findUser(uid);
        HashMap<String, Double> categorymap = new HashMap<>();
        categorymap.putAll(user.getMap());

        List<String> unlikeList = new ArrayList<>();
        unlikeList.add(categoryName);
        for (Object category : unlikeList) {
//
            categorymap.put(category.toString(), user.getMap().get(category) - 1.0);
            user.setMap(categorymap);


        }

        System.out.println("after setting map" + user.getMap());
        userService.addUser(user);
    }

    //Adds favorite to users and updates that news category weight to 3.0
    public void addFavToFeed(Integer uid, String categoryName) {

        Double weightByPreferences = 0.0;
        User user = userService.findUser(uid);
        HashMap<String, Double> categorymap = new HashMap<>();
        categorymap.putAll(user.getMap());

        List<String> favList = new ArrayList<>();
        favList.add(categoryName);
        for (Object category : favList) {

            categorymap.put(category.toString(), user.getMap().get(category) + 3.0);
            user.setMap(categorymap);


        }

        userService.addUser(user);

    }

    //Removes favorites from users and updates that news category weight
    public void removeFav(Integer uid, String categoryName) {

        Double weightByPreferences = 0.0;
        User user = userService.findUser(uid);
        HashMap<String, Double> categorymap = new HashMap<>();
        categorymap.putAll(user.getMap());

        List<String> unFav = new ArrayList<>();
        unFav.add(categoryName);
        for (Object category : unFav) {
//
            categorymap.put(category.toString(), user.getMap().get(category) - 3.0);
            user.setMap(categorymap);


        }

        userService.addUser(user);
    }


    //Add saved feeds to users and updates that news category weight to 2.0
    public void addSaveToFeed(Integer uid, String categoryName) {

        Double weightByPreferences = 0.0;
        User user = userService.findUser(uid);
        HashMap<String, Double> categorymap = new HashMap<>();
        categorymap.putAll(user.getMap());


        List<String> saveList = new ArrayList<>();
        saveList.add(categoryName);
        for (Object category : saveList) {
            categorymap.put(category.toString(), user.getMap().get(category) + 2.0);
            user.setMap(categorymap);
        }

        userService.addUser(user);
    }

    //Removes saved feed from users and updates that news category weight
    public void removeSave(Integer uid, String categoryName) {

        Double weightByPreferences = 0.0;
        User user = userService.findUser(uid);
        HashMap<String, Double> categorymap = new HashMap<>();
        categorymap.putAll(user.getMap());

        List<String> deleteList = new ArrayList<>();
        deleteList.add(categoryName);
        for (Object category : deleteList) {

            categorymap.put(category.toString(), user.getMap().get(category) - 2.0);
            user.setMap(categorymap);


        }

        userService.addUser(user);
    }

    //Fetched user feed personalized by category-weight map
    public List<DNews> fetchByUid(Integer uid) {
        List<DNews> news = new ArrayList<>();
        User user = userService.findUser(uid);
        HashMap<String, Double> categorymap = new HashMap<>();
        categorymap.putAll(user.getMap());
        //convert to sorted hashmap - code taken from online sample comparator
        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(categorymap.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Double>>() {
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {


                return o2.getValue().compareTo(o1.getValue());

            }
        });
//prints sorted map
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Entry<String, Double> entry : list) {
            //Also check not 0 to filter not interested
            if (entry.getValue() > 0.0) {
                sortedMap.put(entry.getKey(), entry.getValue());
            } else {
                continue;
            }
        }
        List<String> keys = new ArrayList<>(sortedMap.keySet());
        Collection<Double> values = sortedMap.values();


        //filter based on weight
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + "and" + entry.getValue());
            if (entry.getValue() > 50) {
                news.addAll(dNewsService.findNewsByCategoryWeight(entry.getKey(), 20));
            } else if (entry.getValue() > 10) {
                news.addAll(dNewsService.findNewsByCategoryWeight(entry.getKey(), 12));
            } else if (entry.getValue() > 5) {
                news.addAll(dNewsService.findNewsByCategoryWeight(entry.getKey(), 8));

            } else {
                news.addAll(dNewsService.findNewsByCategoryWeight(entry.getKey(), 5));

            }
        }


        return news;


    }

    //Service to format and write database objects to csv in required way for recommendation model
//Required format - userid, categoryid, category weight.
    public List<List<String>> recommendService() {

        List<List<String>> userlist = userFeedRepository.getUsermap();
        List<String> csvlist = new ArrayList<>();

        userlist.forEach(s -> {

            String categoryId = "0";

            if (s.get(1).equals("business")) {
                categoryId = "0";
                s.add(1, categoryId);
            } else if (s.get(1).equals("health")) {
                categoryId = "2";
                s.add(1, categoryId);
            }
            if (s.get(1).equals("science")) {
                categoryId = "1";
                s.add(1, categoryId);
            }
            if (s.get(1).equals("technology")) {
                categoryId = "3";
                s.add(1, categoryId);
            }
            if (s.get(1).equals("entertainment")) {
                categoryId = "4";
                s.add(1, categoryId);
            }
            if (s.get(1).equals("sports")) {
                categoryId = "5";
                s.add(1, categoryId);
            }
            s.remove(2);
            System.out.println("list" + s);
        });
        System.out.println("csvlist" + userlist);
//No need to do Arrays.asList now as we get individual list

        return userlist;
    }

    //load data into csv
    public ByteArrayInputStream load(final List<Object> userList) {
        return writeDataToCsv(userList);
    }

    //write data to csv
    private ByteArrayInputStream writeDataToCsv(final List<Object> csvlist) {

        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
             final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {
//            for (final String csvdata : csvlist) {
            final List<Object> data = csvlist;

            printer.printRecord(data);
//            }
            //    System.out.println("data" + data);
            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }


}
