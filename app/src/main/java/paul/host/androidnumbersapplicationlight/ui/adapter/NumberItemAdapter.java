package paul.host.androidnumbersapplicationlight.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        this(listener, new ArrayList<NumberItem>());
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
            final NumberItem item = items.get(position);
            holder.textView.setText(item.getName());
            Picasso.get()
                   .load(item.getImage())
                   .into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.clickedColor();
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnFocusChangeListener, View.OnTouchListener {
        @NonNull
        ImageView imageView;
        @NonNull
        TextView textView;

        final int transparent;
        final int green;
        final int blue;
        final int white;
        final int black;
        final int red;

        ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.number_image);
            textView = view.findViewById(R.id.number_name);

            transparent = view.getResources().getColor(R.color.transparent);
            green = view.getResources().getColor(R.color.colorGreen);
            blue = view.getResources().getColor(R.color.colorBlue);
            white = view.getResources().getColor(R.color.colorWhite);
            black = view.getResources().getColor(R.color.colorBlack);
            red = view.getResources().getColor(R.color.colorRed);

            defaultColor();

            view.setFocusable(true);
            view.setOnFocusChangeListener(this);
            view.setOnTouchListener(this);
        }

        @Override
        public void onFocusChange(@NonNull View v, boolean hasFocus) {
            if (hasFocus) {
                focusableColor();
            }
        }

        @Override
        public boolean onTouch(@NonNull View v, @NonNull MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                touchColor();
            }
            return false;
        }

        void setColor(int background, int text) {
            itemView.setBackgroundColor(background);
            textView.setTextColor(text);
        }

        void focusableColor() {
            setColor(green, white);
            returnColor();
        }

        void touchColor() {
            setColor(blue, white);
            returnColor();
        }

        void clickedColor() {
            setColor(red, white);
            returnColor();
        }

        void defaultColor() {
            setColor(transparent, black);
        }

        void returnColor() {
            itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    defaultColor();
                }
            }, 1000);
        }
    }
}
