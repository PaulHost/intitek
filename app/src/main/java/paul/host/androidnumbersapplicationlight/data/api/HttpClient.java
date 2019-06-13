package paul.host.androidnumbersapplicationlight.data.api;

import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final class HttpClient {
    protected static final HttpClient INCTANCE = new HttpClient();

    @NonNull
    private HttpURLConnection conn;

    private HttpClient() {
    }

    synchronized void get(final @NonNull String url, @NonNull final HttpCallback<String> callback) {
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                String response = readInputStream(conn.getInputStream());
                callback.onSuccess(response);
            } else {
                String error = readInputStream(conn.getErrorStream());
                callback.onError(new Exception(error));
            }

        } catch (IOException e) {
            callback.onError(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @NonNull
    private String readInputStream(@NonNull InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }


}
