package solution.piece.codelibrary.WallpaperData;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import solution.piece.codelibrary.ModalClass.ModalClub;

public class StatusMasterSingleton {


    public static int position;
    private static StatusMasterSingleton mInstance;
    private static Context ctx;

    private static String DIRECTORY_TO_SAVE_MEDIA_NOW = "/WhatsApp Master/";
    private static String WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses/";

    private static String WHATSAPP_Images = "/WhatsApp/Media/WhatsApp Images/";
    private static String WHATSAPP_Images_Sent = "/WhatsApp/Media/WhatsApp Images/Sent/";

    private static String WHATSAPP_Videos = "/WhatsApp/Media/WhatsApp Video/";
    private static String WHATSAPP_Videos_Sent = "/WhatsApp/Media/WhatsApp Video/Sent/";

    private static String WHATSAPP_Audio = "/WhatsApp/Media/WhatsApp Audio/";
    private static String WHATSAPP_Audio_Sent = "/WhatsApp/Media/WhatsApp Audio/Sent/";

    public List<ModalClub> club_image_list = new ArrayList<>();


    public String resource_urls = "";
    public String resource_name = "";

    public int ads_audio_count = 0;
    public int ads_video_count = 0;
    public int ads_image_count = 0;

    public static boolean isDownload = false;

    public String status_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION;

    public String FolderToGet = "Status";  // by default
    public String Status = "Status";
    public String Sent = "Sent";
    public String Inbox = "Recieved";
    public String Download = "Download";


    public StatusMasterSingleton(Context context) {
        ctx = context;
    }



    public static synchronized StatusMasterSingleton getInstance(Context context) {
        if (mInstance == null)
        {
            mInstance = new StatusMasterSingleton(context);
        }
        return mInstance;
    }

    public String GetImages()
    {
        String Images_path = "";
        isDownload = false;
        if(FolderToGet.matches(Status))
        {
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION;
        }
        else if(FolderToGet.matches(Sent))
        {
            isDownload = true;
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_Images_Sent;
        }
        else if(FolderToGet.matches(Inbox))
        {
            isDownload = true;
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_Images;
        }
        else if(FolderToGet.matches(Download))
        {
            isDownload = true;
            Images_path = GetDownloadFolder();
        }
        Log.d("SifatFirst", "Path Images : "+Images_path);
        return Images_path;
    }

    public String GetVideos()
    {

        String Images_path = "";
        isDownload = false;
        if(FolderToGet.matches(Status))
        {
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION;
        }
        else if(FolderToGet.matches(Sent))
        {
            isDownload = true;
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_Videos_Sent;
        }
        else if(FolderToGet.matches(Inbox))
        {
            isDownload = true;
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_Videos;
        }
        else if(FolderToGet.matches(Download))
        {
            isDownload = true;
            Images_path = GetDownloadFolder();
        }
        Log.d("SifatFirst", "Path Images : "+Images_path);
        return Images_path;
    }

    public String GetAudios()
    {
        String Images_path = "";
        isDownload = false;
        if(FolderToGet.matches(Status))
        {
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION;
        }
        else if(FolderToGet.matches(Sent))
        {
            isDownload = true;
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_Audio_Sent;
        }
        else if(FolderToGet.matches(Inbox))
        {
            isDownload = true;
            Images_path = Environment.getExternalStorageDirectory().toString() + WHATSAPP_Audio;
        }
        else if(FolderToGet.matches(Download))
        {
            isDownload = true;
            Images_path = GetDownloadFolder();
        }
        Log.d("SifatFirst", "Path Images : "+Images_path);
        return Images_path;
    }


    public String GetDownloadFolder()
    {
        String new_path = "";

        File folder = new File(Environment.getExternalStorageDirectory().toString() + DIRECTORY_TO_SAVE_MEDIA_NOW);
        boolean success = true;
        if (!folder.exists())
        {
            success = folder.mkdirs();
            new_path = Environment.getExternalStorageDirectory().toString() + DIRECTORY_TO_SAVE_MEDIA_NOW;
        }
        if (success)
        {
            new_path = Environment.getExternalStorageDirectory().toString() + DIRECTORY_TO_SAVE_MEDIA_NOW;
        }
        else
        {
            new_path = "";
        }
        return new_path;
    }


}
