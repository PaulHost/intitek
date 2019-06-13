package paul.host.androidnumbersapplicationlight.ui;


import android.support.annotation.NonNull;

public interface NavigationListener {
    void goBack();

    void goToDetails(@NonNull String name);
}
