package paul.host.androidnumbersapplicationlight.data.api;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import paul.host.androidnumbersapplicationlight.data.model.NumberItem;

public class ApiDataSource {
    private static final String TEST_URL = "http://dev.tapptic.com/test/json.php";
    private static final String NAME_URL = TEST_URL + "?name=%s";

    @NonNull
    public void getNumbers(final @NonNull HttpCallback<List<NumberItem>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient.INCTANCE.get(TEST_URL, new HttpCallback<String>() {
                    @Override
                    public void onSuccess(@NonNull String s) {
                        try {
                            callback.onSuccess(getNumbers(s));
                        } catch (JSONException e) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                        callback.onError(e);
                    }
                });
            }
        }).start();
    }

    @NonNull
    public void getNumberInfo(final @NonNull String numberName, final @NonNull HttpCallback<NumberItem> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient.INCTANCE.get(String.format(NAME_URL, numberName), new HttpCallback<String>() {
                    @Override
                    public void onSuccess(@NonNull String s) {
                        try {
                            callback.onSuccess(getNumber(s));
                        } catch (JSONException e) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                        callback.onError(e);
                    }
                });
            }
        }).start();
    }

    @NonNull
    private List<NumberItem> getNumbers(@NonNull String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        jsonArray.length();
        return getNumbers(new JSONArray(json));
    }

    @NonNull
    private List<NumberItem> getNumbers(@NonNull JSONArray jsonArray) throws JSONException {
        List<NumberItem> numbers = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            numbers.add(getNumber(jsonArray.getJSONObject(i)));
        }
        return numbers;
    }

    @NonNull
    private NumberItem getNumber(@NonNull String json) throws JSONException {
        return getNumber(new JSONObject(json));
    }

    @NonNull
    private NumberItem getNumber(@NonNull JSONObject json) throws JSONException {
        return NumberItem.builder().name(json.getString("name"))
                .image(json.getString("image"))
                .text(json.getString("text"))
                .build();
    }
}
