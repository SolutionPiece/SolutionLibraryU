package solution.piece.codelibrary;


import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import solution.piece.codelibrary.WallpaperData.StatusMasterSingleton;


public class FunctionCollection {

    private static Context context;
    SimpleDateFormat dateFormat;

    static String color_status_master, color_orange, color_blue, color_green, color_dark_blue, color_red, color_white;

    public FunctionCollection(Context context) {
        this.context = context;

        dateFormat = new SimpleDateFormat("HH:mm");
        color_status_master = context.getResources().getString(R.string.color_status_master);
        color_orange = context.getResources().getString(R.string.color_orange);
        color_blue = context.getResources().getString(R.string.color_blue);
        color_green = context.getResources().getString(R.string.color_green);
        color_dark_blue = context.getResources().getString(R.string.color_dark_blue);
        color_red = context.getResources().getString(R.string.color_red);
        color_white = context.getResources().getString(R.string.color_white);
    }



    public void setWallpaper(Context context2, Bitmap image) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context2);
        try {
            if (image != null) {
                wallpaperManager.setBitmap(image);
                image.recycle();
                image = null;
            } else {
                DisplayCustomizeToast(context2, "Failed to set wallpaper",0,6,true);
            }
        } catch (IOException e) {

        } catch (Exception es) {

        }
    }

    public void ShareAppFunction() {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=" + context.getPackageName());
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            context.startActivity(shareIntent);
        } else {
            context.startActivity(Intent.createChooser(shareIntent, "share"));
        }
    }

    public void MoreAppsClick()
    {
        try {
            Uri marketUri = Uri.parse("market://search?q=pub:Solution Piece");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=Solution Piece")));
        }
    }

    public boolean isConnectingToInternet() {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED &&   info[i].getState() != NetworkInfo.State.SUSPENDED) {

                        return true;
                    }
        }

        return false;
    }



    public boolean DialogRateGet()
    {
        SharedPreferences prefs  = context.getSharedPreferences("DialogRateGet", Context.MODE_PRIVATE);
        int count  = Integer.parseInt(prefs.getString("DialogRate" , "0"));
        int backpress_count  = context.getResources().getInteger(R.integer.backpress_count);

        Log.d("DashboardActivityLog","count : "+count);
        Log.d("DashboardActivityLog","Backpress : "+backpress_count);
        if(count == backpress_count)
        {
            DialogRateSave(String.valueOf(backpress_count));
            return false;
        }
        else if(count == (backpress_count-1))
        {
            if(isConnectingToInternet())
            {
                DialogRateSave("0");
                return true;
            }
            return false;
        }
        else
        {
            DialogRateSave(String.valueOf(count+1));
            return false;
        }
    }

    public void CityCurrentDate_Save()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formatter.format(c.getTime());
        SharePrefSaveValue("DailyGift", currentDate);

        String saveDate = SharePrefGetValue("DailyGift","5");
        Log.d("ServiceProcess33", "RequestDate saveDate after changes" + saveDate);
    }


    public boolean CurrentDate_Check()
    {
        boolean isTrue = false;
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = formatter.format(c.getTime());

            String saveDate = SharePrefGetValue("DailyGift","5");
            Log.d("ServiceProcess33", currentDate+" = " + saveDate);

            if (!currentDate.matches(saveDate)) {
                isTrue = true;
            }
            else
            {
                isTrue = false;
            }

        }
        catch (Exception ex)
        {
            DisplayCustomizeToast(context,"Date Error : "+ex.toString(),0,6,true);
        }
        return isTrue;
    }

    public String DateDBUpdateGet(){
        SharedPreferences prefs  = context.getSharedPreferences("CurrentDate", Context.MODE_PRIVATE);
        return prefs.getString("CurrentDateKey" , "01/10/2018");
    }

    public void DateDBUpdateSave(String value){
        SharedPreferences prefs = context.getSharedPreferences("CurrentDate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  prefs.edit();
        editor.putString("CurrentDateKey" , value);
        editor.commit();
    }


    public void DialogRateSave(String value){
        SharedPreferences prefs = context.getSharedPreferences("DialogRateGet", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  prefs.edit();
        editor.putString("DialogRate" , value);
        editor.commit();
    }

    public void RateUsApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, " unable to find market app", Toast.LENGTH_LONG).show();

        }
    }

    public String SharePrefGetValue(String key, String default_value){
        SharedPreferences prefs  = context.getSharedPreferences("SolutionPieceApp", Context.MODE_PRIVATE);
        return prefs.getString(key , default_value);
    }

    public void SharePrefSaveValue(String key, String value){
        SharedPreferences prefs = context.getSharedPreferences("SolutionPieceApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  prefs.edit();
        editor.putString(key , value);
        editor.commit();
    }




    public static void DisplayCustomizeToast(Context context, String message, int colour_back_pos, int colour_text_pos, boolean isLong) {


        String[] arrayColors = {color_status_master, color_orange, color_blue, color_green, color_dark_blue, color_red, color_white};
        View layoutValue = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        CardView background = (CardView) layoutValue.findViewById(R.id.custom_toast_layout_id);
        TextView text = (TextView) layoutValue.findViewById(R.id.message);

        Toast custom_toast = new Toast(context);
        if(isLong)
            custom_toast.setDuration(Toast.LENGTH_SHORT);
        else
            custom_toast.setDuration(Toast.LENGTH_LONG);

        text.setTextSize(18);
        text.setText(message);
        custom_toast.setGravity(Gravity.BOTTOM, 0, 200);
        text.setTextColor(Color.parseColor(arrayColors[colour_text_pos]));
        background.setCardBackgroundColor(Color.parseColor(arrayColors[colour_back_pos]));

        custom_toast.setView(layoutValue);
        if(message.length() > 0)
        {
            custom_toast.show();
        }
    }



    public void CopyFileToDownloadFolder(Context context, String resource_url, String file_name)
    {
        try {
            File sourceLocation= new File (resource_url);
            File targetLocation= new File (StatusMasterSingleton.getInstance(context).GetDownloadFolder()+File.separator+file_name);

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            scanner(resource_url);
            addImageGallery(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
        catch (Exception e)
        {
            Log.d("PathLogs","Exception : "+e.toString());
            Toast.makeText(context, "Exception : "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public void ShareFunctionFinal(String resource_url) {
        Uri URI = Uri.parse(resource_url);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("image/jpeg");
        emailIntent.setType("image/jpg");
        emailIntent.setType("image/png");
        emailIntent.setType("image/gif");
        emailIntent.setType("videos/*");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {""});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        // emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+URI));
        context.startActivity(Intent.createChooser(emailIntent, "Sharing Options"));
    }




    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Bitmap convertSrcToBitmap(String imageSrc) {
        Bitmap myBitmap = null;
        File imgFile = new File(imageSrc);
        if (imgFile.exists()) {
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return myBitmap;
    }




    public void Sharedfile1(String resource_url)
    {
        File f = new File(resource_url);

        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(resource_url);

        if (fileWithinMyDir.exists()) {
            intentShareFile.setType("text/*");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + resource_url));
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "MyApp File Share: " + f.getName());
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "MyApp File Share: " + f.getName());
            context.startActivity(Intent.createChooser(intentShareFile, f.getName()));
        }

    }

    public void ShareFileGmail(String message)
    {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setType("text/plain");
            Uri data = Uri.parse("mailto:?subject=" + "WhatsApp Master App" + "&body=" + message + "&to=" + "solutionpieceteam@gmail.com");
            intent.setData(data);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(Intent.createChooser(intent, "Choose Gmail"));


        } catch(Exception e)  {
            System.out.println("is exception raises during sending mail"+e);
        }
    }
    public void Sharedfile3(String resource_urls) throws IOException
    {
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(resource_urls);

        if(fileWithinMyDir.exists()) {
            intentShareFile.setType("application/jpg");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse(resource_urls));
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File...");
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");

            context.startActivity(Intent.createChooser(intentShareFile, "Share File"));
        }
    }



    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }





    public Uri saveImageToInternalStorage(Bitmap bitmap){
        File file = new File(StatusMasterSingleton.getInstance(context).GetDownloadFolder());

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Wallpaper_"+ timeStamp +".PNG";

        file = new File(file, fname);

        try{

            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();

            scanner(fname);
            addImageGallery(file);

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        return savedImageURI;
    }




    // Custom method to convert string to url
    public URL stringToURL(String urlString){
        try{
            URL url = new URL(urlString);
            return url;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    private void scanner(String path) {

        MediaScannerConnection.scanFile(context, new String[] { path }, null, new MediaScannerConnection.OnScanCompletedListener() {

            public void onScanCompleted(String path, Uri uri) {
                Log.i("UpdateLogsCheck", "Finished scanning " + path);
            }
        });
    }

    private void addImageGallery( File file ) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/PNG"); // or image/png
        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }




}
