package com.example.project;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.subjects.PublishSubject;


/**
 * Created by amitshekhar on 17/12/16.
 */

public class PublishSubjectExampleActivity extends AppCompatActivity {

    private static final String TAG = PublishSubjectExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    PublishSubject<Integer> scrollLoadingChannel = PublishSubject.create();
    private void doSomeWork() {
    }
}