package org.meruvian.workshop.fragment.entity;

/**
 * Created by merv on 6/3/15.
 */


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class News {

    private int id = -1;
    private String title;
    private String content;
    private long createDate;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static List<News> data(){
        List<News> newses = new ArrayList<>();

        News news = new News();
        news.setId(1);
        news.setTitle("Lorem ipsum dolor sit amet");
        news.setContent("consectetur adipiscing elit. Currabitur non tincidunt libero, lobortis rhoncus augue.");
        news.setCreateDate(new Date().getTime());
        newses.add(news);

        News news1 = new News();
        news1.setId(1);
        news1.setTitle("Lorem ipsum 1 dolor sit amet");
        news.setContent("consectetur adipiscing elit. Currabitur non tincidunt libero, lobortis rhoncus augue.");
        news1.setCreateDate(new Date().getTime());
        newses.add(news1);

        News news2 = new News();
        news2.setId(1);
        news2.setTitle("Lorem ipsum 2 dolor sit amet");
        news2.setContent("consectetur adipiscing elit. Currabitur non tincidunt libero, lobortis rhoncus augue.");
        news2.setCreateDate(new Date().getTime());
        newses.add(news2);

        News news3 = new News();
        news3.setId(1);
        news3.setTitle("Lorem ipsum 3 dolor sit amet");
        news3.setContent("consectetur adipiscing elit. Currabitur non tincidunt libero, lobortis rhoncus augue.");
        news3.setCreateDate(new Date().getTime());
        newses.add(news3);

        News news4 = new News();
        news4.setId(1);
        news4.setTitle("Lorem ipsum 4 dolor sit amet");
        news4.setContent("consectetur adipiscing elit. Currabitur non tincidunt libero, lobortis rhoncus augue.");
        news4.setCreateDate(new Date().getTime());
        newses.add(news4);

        News news5 = new News();
        news5.setId(1);
        news5.setTitle("Lorem ipsum 5 dolor sit amet ");
        news5.setContent("consectetur adipiscing elit. Currabitur non tincidunt libero, lobortis rhoncus augue.");
        news5.setCreateDate(new Date().getTime());
        newses.add(news5);

        return newses;
    }
}
