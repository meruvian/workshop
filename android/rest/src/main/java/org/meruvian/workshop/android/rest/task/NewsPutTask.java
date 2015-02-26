package org.meruvian.workshop.android.rest.task;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.meruvian.workshop.android.rest.R;
import org.meruvian.workshop.android.rest.SampleVariables;
import org.meruvian.workshop.android.rest.entity.News;
import org.meruvian.workshop.android.rest.service.ConnectionUtil;
import org.meruvian.workshop.android.rest.service.TaskService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ludviantoovandi on 25/02/15.
 */
public class NewsPutTask extends AsyncTask<News, Void, JSONObject> {
    private Context context;
    private TaskService service;

    public NewsPutTask(Context context, TaskService service) {
        this.context = context;
        this.service = service;
    }

    @Override
    protected void onPreExecute() {
        service.onExecute(SampleVariables.NEWS_PUT_TASK);
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
            HttpPut httpPut = new HttpPut(SampleVariables.SERVER_URL + "/" + params[0].getId());
            httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
            httpPut.setEntity(new StringEntity(json.toString()));

            HttpResponse response = httpClient.execute(httpPut);
            json = new JSONObject(ConnectionUtil.convertEntityToString(response.getEntity()));
        } catch (IOException e) {
            json = null;
            e.printStackTrace();
        } catch (JSONException e) {
            json = null;
            e.printStackTrace();
        } catch (Exception e) {
            json = null;
            e.printStackTrace();
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

                service.onSuccess(SampleVariables.NEWS_PUT_TASK, news);
            } else {
                service.onError(SampleVariables.NEWS_PUT_TASK, context.getString(R.string.failed_post_news));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            service.onError(SampleVariables.NEWS_PUT_TASK, context.getString(R.string.failed_post_news));
        }
    }
}
