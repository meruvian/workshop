package org.meruvian.workshop.sample.rest.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.meruvian.workshop.sample.rest.R;
import org.meruvian.workshop.sample.rest.SampleVariables;
import org.meruvian.workshop.sample.rest.entity.News;
import org.meruvian.workshop.sample.rest.service.ConnectionUtil;
import org.meruvian.workshop.sample.rest.service.TaskService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Created by ludviantoovandi on 24/07/14.
 */
public class NewsPostTask extends AsyncTask<News, Void, JSONObject> {
    private TaskService service;
    private Context context;

    public NewsPostTask(TaskService service, Context context) {
        this.service = service;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        service.onExecute(SampleVariables.NEWS_POST_TASK);
    }

    @Override
    protected JSONObject doInBackground(News... params) {
        JSONObject json = new JSONObject();

        try {
            json.put("id", 0);
            json.put("title", params[0].getTitle());
            json.put("content", params[0].getContent());
            json.put("createDate", 0);

            HttpClient httpClient = new DefaultHttpClient(ConnectionUtil.getHttpParams(15000, 15000));
            HttpPost httpPost = new HttpPost(SampleVariables.SERVER_URL);

            httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(new StringEntity(json.toString()));

            HttpResponse response = httpClient.execute(httpPost);
            json = new JSONObject(ConnectionUtil.convertEntityToString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
            json = null;
        } catch (JSONException e) {
            e.printStackTrace();
            json = null;
        } catch (Exception e) {
            e.printStackTrace();
            json = null;
        }

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            if (jsonObject != null) {
                News news = new News();
                news.setId(jsonObject.getInt("id"));
                news.setContent(jsonObject.getString("content"));
                news.setTitle(jsonObject.getString("title"));
                news.setCreateDate(jsonObject.getLong("createDate"));

                service.onSuccess(SampleVariables.NEWS_POST_TASK, news);
            } else {
                service.onError(SampleVariables.NEWS_POST_TASK, context.getString(R.string.failed_post_news));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            service.onError(SampleVariables.NEWS_POST_TASK, context.getString(R.string.failed_post_news));
        }
    }
}
