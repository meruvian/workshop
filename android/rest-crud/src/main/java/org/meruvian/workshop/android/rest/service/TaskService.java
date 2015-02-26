package org.meruvian.workshop.android.rest.service;

/**
 * Created by ludviantoovandi on 03/09/14.
 */
public interface TaskService<R> {
    void onExecute(int code);

    void onSuccess(int code, R result);

    void onCancel(int code, String message);

    void onError(int code, String message);

}
