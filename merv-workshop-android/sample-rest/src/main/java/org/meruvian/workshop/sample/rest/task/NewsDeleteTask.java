package org.meruvian.workshop.sample.rest.task;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import org.meruvian.workshop.sample.rest.R;
import org.meruvian.workshop.sample.rest.SampleVariables;
import org.meruvian.workshop.sample.rest.entity.News;
import org.meruvian.workshop.sample.rest.service.ConnectionUtil;
import org.meruvian.workshop.sample.rest.service.TaskService;

import java.io.IOException;

/**
 * Created by ludviantoovandi on 24/07/14.
 */
public class NewsDeleteTask extends AsyncTask<String, Void, Boolean> {
    private TaskService service;
    private Context context;

    public NewsDeleteTask(TaskService service, Context context) {
        this.service = service;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        service.onExecute(SampleVariables.NEWS_DELETE_TASK);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            HttpClient httpClient = new DefaultHttpClient(ConnectionUtil.getHttpParams(15000, 15000));
            HttpDelete httpDelete = new HttpDelete(SampleVariables.SERVER_URL + "/" + params[0]);

            HttpResponse response = httpClient.execute(httpDelete);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean check) {
        if (check) {
            service.onSuccess(SampleVariables.NEWS_DELETE_TASK, true);
        } else {
            service.onError(SampleVariables.NEWS_DELETE_TASK, context.getString(R.string.failed_delete_news));
        }
    }
}
