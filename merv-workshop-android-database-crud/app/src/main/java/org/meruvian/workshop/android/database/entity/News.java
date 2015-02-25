package org.meruvian.workshop.android.database.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meruvian on 22/01/15.
 */
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

    public static List<News> data() {
        List<News> newses = new ArrayList<>();

        News news = new News();
        news.setId(1);
        news.setTitle("Lorem ipsum dolor sit amet");
        news.setContent("consectetur adipiscing elit. Curabitur non tincidunt libero, lobortis rhoncus augue. Maecenas commodo nulla quis orci fringilla elementum");
        news.setCreateDate(new Date().getTime());
        newses.add(news);

        News news2 = new News();
        news2.setId(2);
        news2.setTitle("Ut id magna id ligula efficitur tincidunt");
        news2.setContent("Ut convallis elit vitae lacinia ullamcorper. Cras elementum purus nec consectetur egestas");
        news2.setCreateDate(new Date().getTime());
        newses.add(news2);

        News news3 = new News();
        news3.setId(3);
        news3.setTitle("Nam at suscipit mauris");
        news3.setContent("Aenean maximus faucibus eros sit amet eleifend");
        news3.setCreateDate(new Date().getTime());
        newses.add(news3);

        News news4 = new News();
        news4.setId(5);
        news4.setTitle("Sed a purus a felis rutrum maximus eu in augue");
        news4.setContent("Mauris a dui sed erat lobortis placerat. Interdum et malesuada fames ac ante ipsum primis in faucibus");
        news4.setCreateDate(new Date().getTime());
        newses.add(news4);

        News news5 = new News();
        news5.setId(5);
        news5.setTitle("Nulla dignissim, mi sed maximus pellentesque");
        news5.setContent("nulla ante accumsan velit, vitae posuere diam lacus a diam");
        news5.setCreateDate(new Date().getTime());
        newses.add(news5);

        News news6 = new News();
        news6.setId(6);
        news6.setTitle("Nunc luctus tortor id nibh ultricies tempus");
        news6.setContent("Fusce scelerisque lorem ac mauris dapibus facilisis. Proin elementum vel neque at cursus");
        news6.setCreateDate(new Date().getTime());
        newses.add(news6);

        News news7 = new News();
        news7.setId(7);
        news7.setTitle("Nulla facilisi. In et consectetur dui");
        news7.setContent("Integer accumsan tempus turpis a rutrum. Ut laoreet porttitor nisi lobortis maximus");
        news7.setCreateDate(new Date().getTime());
        newses.add(news7);

        News news8 = new News();
        news8.setId(8);
        news8.setTitle("Suspendisse eget ligula felis");
        news8.setContent("Aenean et magna id dui efficitur mattis vitae id leo. In hac habitasse platea dictumst.");
        news8.setCreateDate(new Date().getTime());
        newses.add(news8);

        News news9 = new News();
        news9.setId(9);
        news9.setTitle("Vestibulum a felis ornare, molestie tortor at, convallis ante");
        news9.setContent("Quisque dictum blandit risus, non vehicula ex. Curabitur semper urna nec massa egestas porttitor. Vestibulum gravida felis enim, egestas dignissim purus aliquam eget");
        news9.setCreateDate(new Date().getTime());
        newses.add(news9);

        News news10 = new News();
        news10.setId(10);
        news10.setTitle("Etiam lacinia lectus quis nibh dictum euismod");
        news10.setContent("Vestibulum laoreet feugiat lobortis. Donec blandit fringilla tellus, eu malesuada nulla dignissim id");
        news10.setCreateDate(new Date().getTime());
        newses.add(news10);

        return newses;
    }
}
