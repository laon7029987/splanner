package com.timeisall.mstudyplanner.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button dailystudyButton = (Button) findViewById(R.id.dailystudyButton);
        final Button monthstudyButton = (Button) findViewById(R.id.monthstudyButton);
        final Button studyanalysisButton = (Button) findViewById(R.id.studyanalysisButton);
        final LinearLayout dday = (LinearLayout) findViewById(R.id.dday);

        dailystudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dday.setVisibility(View.GONE);
                dailystudyButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                monthstudyButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                studyanalysisButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new DailyStudyFragment());
                fragmentTransaction.commit();
            }
        });

        monthstudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dday.setVisibility(View.GONE);
                dailystudyButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                monthstudyButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                studyanalysisButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new MonthStudyFragment());
                fragmentTransaction.commit();
            }
        });

        studyanalysisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dday.setVisibility(View.GONE);
                dailystudyButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                monthstudyButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                studyanalysisButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StudyAnalysisFragment());
                fragmentTransaction.commit();
            }
        });
    }
}
