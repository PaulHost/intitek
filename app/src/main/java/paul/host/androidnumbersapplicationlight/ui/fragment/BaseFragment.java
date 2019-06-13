package paul.host.androidnumbersapplicationlight.ui.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import paul.host.androidnumbersapplicationlight.ui.NavigationListener;

public class BaseFragment extends Fragment {
    @Nullable
    protected NavigationListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationListener) {
            listener = (NavigationListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
