package hse.sheduleteachers;

import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class CustomSnackbar
        extends BaseTransientBottomBar<CustomSnackbar> {


    protected CustomSnackbar(@NonNull ViewGroup parent, @NonNull View content, @NonNull ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    public static CustomSnackbar make(ViewGroup parent, int duration) {
        // inflate custom layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.snackbar_layout, parent, false);

        // create with custom view
        ContentViewCallback callback= new ContentViewCallback(view);
        CustomSnackbar customSnackbar = new CustomSnackbar(parent, view, callback);

        customSnackbar.setDuration(duration);

        return customSnackbar;
    }


    private static class ContentViewCallback implements BaseTransientBottomBar.ContentViewCallback {

        // view inflated from custom layout
        private View view;

        public ContentViewCallback(View view) {
            this.view = view;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            // TODO: handle enter animation
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            // TODO: handle exit animation
        }
    }

}
