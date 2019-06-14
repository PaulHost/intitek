package paul.host.androidnumbersapplicationlight.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import paul.host.androidnumbersapplicationlight.R;
import paul.host.androidnumbersapplicationlight.ui.fragment.DetailsFragment;
import paul.host.androidnumbersapplicationlight.ui.fragment.ListFragment;

public class MainActivity extends BaseActivity {
    boolean isLayoutLargeLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isBackStackEmpty()) {
            startFragment(ListFragment.getInstance());
        } else if (isBackStackMoreThanOne() && isLayoutLargeLandscape) {
            goBack();
        }
    }

    @Override
    public int getContainer() {
        return R.id.container;
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layoutResID, null);
        String resourceType = (String) view.getTag();
        isLayoutLargeLandscape = getResources().getString(R.string.layout_large_land_tag).equals(resourceType);
        super.setContentView(view);
    }

    @Override
    public void goToDetails(@NonNull String name) {
        if (isLayoutLargeLandscape) {
            replaceFragment(R.id.details, DetailsFragment.newInstance(name));
        } else {
            startFragment(DetailsFragment.newInstance(name));
        }
    }

}
