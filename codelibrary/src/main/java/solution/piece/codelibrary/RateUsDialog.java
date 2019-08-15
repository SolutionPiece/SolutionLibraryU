package solution.piece.codelibrary;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class RateUsDialog extends Activity {

    TextView btnMayBeLater, btnRateNow;
    FunctionCollection functionCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.rate_us_dialog);

        functionCollection = new FunctionCollection(RateUsDialog.this);

        btnMayBeLater = (TextView) findViewById(R.id.btnMayBeLater);
        btnRateNow = (TextView) findViewById(R.id.btnRateNow);

        btnMayBeLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int backpress_count  = getResources().getInteger(R.integer.backpress_count);

                functionCollection.DialogRateSave(String.valueOf(backpress_count));
                functionCollection.RateUsApp(RateUsDialog.this);
            }
        });

        this.setFinishOnTouchOutside(false);

    }

}
