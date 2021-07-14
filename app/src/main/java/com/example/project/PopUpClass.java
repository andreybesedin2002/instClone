package com.example.project;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;

public class PopUpClass {

    //PopupWindow display method

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void showPopupWindow(final View view) {

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popupwindow, null);
        PopupWindow optionspu = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        optionspu.setAnimationStyle(R.style.popwindow_anim_style);
        optionspu.setFocusable(true);
        optionspu.setOutsideTouchable(true);
        optionspu.update(0, 0, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        int heigth_tobottom =100;
        optionspu.showAtLocation(layout, Gravity.BOTTOM,0,heigth_tobottom);
        ViewGroup root = (ViewGroup) view.getRootView();
        PopUpClass.applyDim(root, 0.5f);
        //Create a View object yourself through inflater
              //Handler for clicking on the inactive zone of the window
        optionspu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                clearDim(root);
                Log.i("TAG", "onDismiss: ");
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void applyDim(ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay(); overlay.clear();
    }
}
