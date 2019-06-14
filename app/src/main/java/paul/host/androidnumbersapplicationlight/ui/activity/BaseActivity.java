package paul.host.androidnumbersapplicationlight.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import paul.host.androidnumbersapplicationlight.ui.NavigationListener;

public abstract class BaseActivity extends AppCompatActivity implements NavigationListener {
    public void startFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragment.getClass().getCanonicalName())
                .replace(getContainer(), fragment)
                .commit();
    }

    public void replaceFragment(int container, @NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    @Override
    public void goBack() {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public abstract int getContainer();

    protected boolean isBackStackEmpty() {
        return getSupportFragmentManager().getBackStackEntryCount() == 0;
    }

    protected boolean isBackStackMoreThanOne() {
        return getSupportFragmentManager().getBackStackEntryCount() > 1;

    }
}
