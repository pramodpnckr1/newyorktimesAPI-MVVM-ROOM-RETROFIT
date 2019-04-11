package articles.news.com.mostviewedarticles.data.remotedata;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static articles.news.com.mostviewedarticles.data.remotedata.Status.ERROR;
import static articles.news.com.mostviewedarticles.data.remotedata.Status.LOADING;
import static articles.news.com.mostviewedarticles.data.remotedata.Status.SUCCESS;

/**
 * Created by USER on 4/3/2019.
 * a generic wrapper class that describes a data with a status
 */

public class Resource<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable private final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}
