package solution.piece.codelibrary.WallpaperData;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import solution.piece.codelibrary.FunctionCollection;

public class DownloadTask extends AsyncTask<URL, Void, Bitmap> {


    Context context;
    ProgressDialog mProgressDialog;
    FunctionCollection functionCollection;

    public DownloadTask(Context context) {
        this.context = context;
        functionCollection = new FunctionCollection(context);

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Downloading...");
        // Progress dialog message
        mProgressDialog.setMessage("Please wait, we are downloading this image to gallery ...");


    }

    protected void onPreExecute(){
        mProgressDialog.show();
    }

    // Do the task in background/non UI thread
    protected Bitmap doInBackground(URL...urls){
        URL url = urls[0];
        HttpURLConnection connection = null;

        try{

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);

            // Return the downloaded bitmap
            return bmp;

        }catch(IOException e){
            e.printStackTrace();
        }finally{
            // Disconnect the http url connection
            connection.disconnect();
        }
        return null;
    }

    // When all async task done
    protected void onPostExecute(Bitmap result){
        // Hide the progress dialog
        mProgressDialog.dismiss();

        if(result!=null){

            functionCollection.saveImageToInternalStorage(result);

        }else {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }



}