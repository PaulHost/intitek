package paul.host.androidnumbersapplicationlight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import paul.host.androidnumbersapplicationlight.R;
import paul.host.androidnumbersapplicationlight.data.api.ApiDataSource;
import paul.host.androidnumbersapplicationlight.data.api.HttpCallback;
import paul.host.androidnumbersapplicationlight.data.model.NumberItem;

public class DetailsFragment extends Fragment {
    private static final String NUMBER_NAME_PARAM = "number_name";

    @NonNull
    private TextView textView;

    @NonNull
    private TextView nameView;

    @NonNull
    private ImageView imageView;

    @Nullable
    private String numberName;

    @NonNull
    public static DetailsFragment newInstance(@NonNull String numberName) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(NUMBER_NAME_PARAM, numberName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numberName = getArguments().getString(NUMBER_NAME_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        textView = v.findViewById(R.id.details_number_text);
        nameView = v.findViewById(R.id.details_number_name);
        imageView = v.findViewById(R.id.details_number_image);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (numberName != null) {
            ApiDataSource.INSTANCE.getNumberInfo(numberName, new HttpCallback<NumberItem>() {
                @Override
                public void onSuccess(@NonNull NumberItem numberItem) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(numberItem.getText());
                            nameView.setText(numberItem.getName());
                            Picasso.get()
                                   .load(numberItem.getImage())
                                   .into(imageView);
                        }
                    });
                }

                @Override
                public void onError(@NonNull Exception e) {
                    Log.e(getClass().getSimpleName(), e.getMessage(), e);
                }
            });
        }
    }
}
