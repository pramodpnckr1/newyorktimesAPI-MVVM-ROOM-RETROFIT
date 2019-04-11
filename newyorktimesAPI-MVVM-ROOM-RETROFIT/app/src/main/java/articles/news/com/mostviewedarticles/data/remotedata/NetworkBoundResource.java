package articles.news.com.mostviewedarticles.data.remotedata;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import articles.news.com.mostviewedarticles.NYTimesApp;
import articles.news.com.mostviewedarticles.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by USER on 4/3/2019.
 */

public abstract class NetworkBoundResource<T, V> {
    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();
@MainThread
protected NetworkBoundResource() {
    result.setValue(Resource.loading(null));

    // Always load the data from DB intially so that we have
    LiveData<T> dbSource = loadFromDataBase();

    // Fetch the data from network and add it to the resource
    result.addSource(dbSource, data -> {
        result.removeSource(dbSource);
        if (shouldFetchData()) {
            fetchFromNetwork(dbSource);
        } else {
            result.addSource(dbSource, newData -> {
                if(null != newData)
                    result.setValue(Resource.success(newData)) ;
            });
        }
    });
}

    /**
     * This method fetches the data from remoted service and save it to local data base
     * @param dbSource - Database source
     */
    private void fetchFromNetwork(final LiveData<T> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        createCall().enqueue(new Callback<V>() {
            @Override
            public void onResponse(@NonNull Call<V> call, @NonNull Response<V> response) {
                result.removeSource(dbSource);
                saveResultAndReLoad(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<V> call, @NonNull Throwable t) {
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(getCustomErrorMessages(t), newData)));
            }
        });
    }
    @SuppressLint("StaticFieldLeak")
    @MainThread
    private void saveResultAndReLoad(V response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadFromDataBase(), newData -> {
                    if (null != newData)
                        result.setValue(Resource.success(newData));
                });
            }
        }.execute();
    }

    private String getCustomErrorMessages(Throwable error){

        if (error instanceof SocketTimeoutException) {
          return NYTimesApp.getAppContext().getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  NYTimesApp.getAppContext().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return  NYTimesApp.getAppContext().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return NYTimesApp.getAppContext().getString(R.string.unknownError);
        }


    }
    @NonNull
    @MainThread
    protected abstract LiveData<T> loadFromDataBase();
    @MainThread
    private boolean shouldFetchData() {
        return true;
    }

    @NonNull
    @MainThread
    protected abstract Call<V> createCall();

    public final LiveData<Resource<T>> getAsLiveData() {
        return result;
    }

    @WorkerThread
    protected abstract void saveCallResult(V item);

}
