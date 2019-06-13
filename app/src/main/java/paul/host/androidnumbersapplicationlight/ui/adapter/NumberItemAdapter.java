package paul.host.androidnumbersapplicationlight.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import paul.host.androidnumbersapplicationlight.R;
import paul.host.androidnumbersapplicationlight.data.model.NumberItem;
import paul.host.androidnumbersapplicationlight.ui.NavigationListener;

public class NumberItemAdapter extends RecyclerView.Adapter<NumberItemAdapter.ViewHolder> {

    @NonNull
    private List<NumberItem> items;
    @NonNull
    private final NavigationListener listener;

    public NumberItemAdapter(@NonNull NavigationListener listener) {
        this(listener, new ArrayList<>());
    }

    public NumberItemAdapter(@NonNull NavigationListener listener,
                             @NonNull List<NumberItem> items) {
        this.listener = listener;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder holder, int position) {
        if (!items.isEmpty()) {
            NumberItem item = items.get(position);
            holder.textView.setText(item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.goToDetails(item.getName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(@NonNull List<NumberItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        ImageView imageView;
        @NonNull
        TextView textView;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.number_image);
            textView = view.findViewById(R.id.number_name);
        }
    }
}
