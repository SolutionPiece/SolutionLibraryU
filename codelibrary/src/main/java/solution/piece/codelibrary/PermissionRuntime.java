package solution.piece.codelibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class PermissionRuntime extends Activity
{

    private static final int MY_READ_EXTERNAL_STORAGE = 3;
    private static final int MY_WRITE_EXTERNAL_STORAGE = 4;
    private static final int Ext = 4;
    private Context context;
   // (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
    public PermissionRuntime(Context context) {
        this.context = context;
    }

    public boolean StoragePermission()
    {

        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_WRITE_EXTERNAL_STORAGE);
        }
        else
        {
            return true;
        }
        return false;
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case MY_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                   // FunctionHelper.ShowCustomToast(context,"PermissionSMS Granted",1,1);

                } else
                {
                    // FunctionHelper.ShowCustomToast(context,"PermissionSMS Denied",1,1);
                }
                return;

            }

            case MY_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // FunctionHelper.ShowCustomToast(context,"PermissionSMS Granted",1,1);

                } else
                {
                    // FunctionHelper.ShowCustomToast(context,"PermissionSMS Denied",1,1);
                }
                return;

            }

        }
    }
}
