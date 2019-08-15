package solution.piece.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import solution.piece.codelibrary.ContactActivity;
import solution.piece.codelibrary.DialogUpdateAvaiable;
import solution.piece.codelibrary.RateUsDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnContactUS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
            }
        });

        findViewById(R.id.btnUpdateAvailable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DialogUpdateAvaiable.class));
            }
        });

        findViewById(R.id.btnRateUS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RateUsDialog.class));
            }
        });


    }
}
