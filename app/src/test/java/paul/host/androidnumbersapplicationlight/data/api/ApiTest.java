package paul.host.androidnumbersapplicationlight.data.api;

import android.support.annotation.NonNull;

import org.junit.Test;

public class ApiTest {

    @Test
    public void testNumbersInfo() {
        HttpClient.INCTANCE.get("http://dev.tapptic.com/test/json.php", new HttpCallback<String>() {
            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @Test
    public void testNumberInfo() {
        HttpClient.INCTANCE.get("http://dev.tapptic.com/test/json.php?name=10", new HttpCallback<String>() {
            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

}
