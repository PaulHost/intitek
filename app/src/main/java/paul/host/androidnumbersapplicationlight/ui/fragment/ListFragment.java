package paul.host.androidnumbersapplicationlight.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import paul.host.androidnumbersapplicationlight.R;
import paul.host.androidnumbersapplicationlight.data.api.ApiDataSource;
import paul.host.androidnumbersapplicationlight.data.api.HttpCallback;
import paul.host.androidnumbersapplicationlight.data.model.NumberItem;
import paul.host.androidnumbersapplicationlight.ui.adapter.NumberItemAdapter;

@SuppressLint("StaticFieldLeak")
public class ListFragment extends BaseFragment {

    @NonNull
    private NumberItemAdapter adapter;
    @NonNull
    private RecyclerView recyclerView;
    @Nullable
    private static ListFragment instance;

    @NonNull
    public static ListFragment getInstance() {
        if (instance == null) {
            instance = new ListFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert listener != null;
        adapter = new NumberItemAdapter(listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);

            ApiDataSource.INSTANCE.getNumbers(new HttpCallback<List<NumberItem>>() {
                @Override
                public void onSuccess(@NonNull final List<NumberItem> numberItems) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setItems(numberItems);
                        }
                    });
                }

                @Override
                public void onError(@NonNull Exception e) {
                    Log.e(getClass().getSimpleName(), e.getMessage(), e);
                }
            });
        }
        return view;
    }
}
