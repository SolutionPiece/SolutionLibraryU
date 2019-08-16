package solution.piece.codelibrary.ModalClass;

import android.graphics.drawable.Drawable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
@Keep
public class ModalFavourite {

    public String id;
    public String image_url;
    public String player_name;
    public boolean isFromClub;
    public Drawable drawable;


    public ModalFavourite() {

    }

    public ModalFavourite(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }


    public boolean isFromClub() {
        return isFromClub;
    }

    public void setFromClub(boolean fromClub) {
        isFromClub = fromClub;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
