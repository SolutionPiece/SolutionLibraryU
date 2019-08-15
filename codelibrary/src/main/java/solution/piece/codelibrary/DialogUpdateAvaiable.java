package solution.piece.codelibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DialogUpdateAvaiable extends Activity {

    TextView btnMayBeLater, btnRateNow;
    FunctionCollection functionCollection;
    LinearLayout layout_btn_later;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.dialog_update_avaiable);

        functionCollection = new FunctionCollection(DialogUpdateAvaiable.this);

        layout_btn_later = (LinearLayout) findViewById(R.id.layout_btn_later);
        btnMayBeLater = (TextView) findViewById(R.id.btnMayBeLater);
        btnRateNow = (TextView) findViewById(R.id.btnRateNow);

        String update_type = functionCollection.SharePrefGetValue("update_type","normal");
        if(update_type.matches("normal"))
        {
            layout_btn_later.setVisibility(View.VISIBLE);
        }
        else
        {
            layout_btn_later.setVisibility(View.GONE);
        }

        btnMayBeLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
            }
        });

        btnRateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateusClick();
            }
        });

        this.setFinishOnTouchOutside(false);

    }


    public void rateusClick() {

        if (functionCollection.isConnectingToInternet()) {
            Uri marketUri = Uri.parse("market://details?id=" + getPackageName());
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
            finish();
        }
        else
        {
            FunctionCollection.DisplayCustomizeToast(DialogUpdateAvaiable.this, "Sorry internet unavailable....", 0,7,true);
        }
    }

}