package paul.host.androidnumbersapplicationlight.data.model;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public final class NumberItem {
    @NonNull
    private final String name, image, text;

    public NumberItem(@NonNull String name,
                      @NonNull String image,
                      @NonNull String text) {
        this.name = name;
        this.image = image;
        this.text = text;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        @Nullable
        private String name, image, text;

        private Builder() {
        }

        @NonNull
        public Builder name(@NonNull String name) {
            this.name = name;
            return this;
        }

        @NonNull
        public Builder image(@NonNull String image) {
            this.image = image;
            return this;
        }

        @NonNull
        public Builder text(@NonNull String text) {
            this.text = text;
            return this;
        }

        public NumberItem build() {
            return new NumberItem(
                    name == null ? "" : name,
                    image == null ? "" : image,
                    text == null ? "" : text
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberItem that = (NumberItem) o;
        return getName().equals(that.getName()) &&
                getImage().equals(that.getImage()) &&
                getText().equals(that.getText());
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int hashCode() {
        return Objects.hash(getName(), getImage(), getText());
    }
}
