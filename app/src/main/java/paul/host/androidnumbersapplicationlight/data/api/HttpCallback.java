package paul.host.androidnumbersapplicationlight.data.api;

import android.support.annotation.NonNull;

public interface HttpCallback<T> {
    void onSuccess(@NonNull T t);

    void onError(@NonNull Exception e);
}
