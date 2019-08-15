package solution.piece.codelibrary.WallpaperData;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import solution.piece.codelibrary.ModalClass.ModalClub;

public class FootballUniSingleton {

    public static int clickedImagePosition = 0;
    public static Drawable drawable;

    //TODO
  //  public static List<ModalPopular> popular_image_list = new ArrayList<>();
  //  public static List<ModalFavourite> listFavourite = new ArrayList<>();
  //  public static List<ModalClub> club_image_list = new ArrayList<>();

    public static ArrayList<String> listImages = new ArrayList<>();


    private static String DIRECTORY_TO_SAVE_MEDIA_NOW = "/Wallpaper Collection/";


    private static FootballUniSingleton mInstance;
    private static Context ctx;

    public FootballUniSingleton(Context context) {
        ctx = context;
    }


    public static synchronized FootballUniSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FootballUniSingleton(context);
        }
        return mInstance;
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
