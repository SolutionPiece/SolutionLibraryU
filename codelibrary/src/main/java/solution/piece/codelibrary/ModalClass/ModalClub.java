package solution.piece.codelibrary.ModalClass;

import androidx.annotation.Keep;
//import com.google.firebase.database.IgnoreExtraProperties;


//@IgnoreExtraProperties
@Keep
public class ModalClub {

    public String id;
    public String club_image;
    public String club_name;

    public String image_url;
    public String player_name;


    public ModalClub() {

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

    public ModalClub(String club_image, String club_name) {
        this.club_image = club_image;
        this.club_name = club_name;
    }


    public String getClub_image() {
        return club_image;
    }

    public void setClub_image(String club_image) {
        this.club_image = club_image;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }
}


