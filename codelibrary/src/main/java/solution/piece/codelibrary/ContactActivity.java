package solution.piece.codelibrary;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;


public class ContactActivity extends Activity {

    private static EditText txtMessage;
    Button btnContactUsSubmit;
    FunctionCollection helperMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_contact);

        helperMethod = new FunctionCollection(ContactActivity.this);

        btnContactUsSubmit = (Button)findViewById(R.id.btnContactUsSubmit);

        final Animation anim_scale = AnimationUtils.loadAnimation(ContactActivity.this, R.anim.anim_scale);
        btnContactUsSubmit.startAnimation(anim_scale);

        txtMessage = (EditText)findViewById(R.id.txtMessage);
    }


    public void SubmitContact(View view) {

        final FunctionCollection ef = new FunctionCollection(this);

        if (ef.isConnectingToInternet()) {

            final String message = txtMessage.getText().toString();

            if (message.length() < 20)
            {
                FunctionCollection.DisplayCustomizeToast(this, "Miniumum 20 character message required to send mail... Thanks",0,7,true);
                txtMessage.requestFocus();
                return;
            }

            else
            {
                helperMethod.ShareFileGmail(message);
            }
        }
        else
        {
            FunctionCollection.DisplayCustomizeToast(ContactActivity.this, "Sorry Internet Unavailable....",0,7,true);
            return;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left_one, R.anim.slide_out_right_one);

    }

}