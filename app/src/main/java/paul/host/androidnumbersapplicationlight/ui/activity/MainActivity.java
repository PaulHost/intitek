package paul.host.androidnumbersapplicationlight.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import paul.host.androidnumbersapplicationlight.R;
import paul.host.androidnumbersapplicationlight.ui.fragment.DetailsFragment;
import paul.host.androidnumbersapplicationlight.ui.fragment.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isBackStackEmpty()) {
            startFragment(ListFragment.getInstance());
        }
    }

    @Override
    public int getContainer() {
        return R.id.container;
    }

    @Override
    public void goToDetails(@NonNull String name) {
        startFragment(DetailsFragment.newInstance(name));
    }
}
