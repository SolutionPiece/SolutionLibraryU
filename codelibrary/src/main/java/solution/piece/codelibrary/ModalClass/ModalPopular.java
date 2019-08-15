package solution.piece.codelibrary.ModalClass;


import androidx.annotation.Keep;

//import com.google.firebase.database.IgnoreExtraProperties;

//@IgnoreExtraProperties
@Keep
public class ModalPopular {

    public String id;
    public String player_name;
    public String image_url;

    public ModalPopular() {
    }

    public ModalPopular(String id, String player_name, String image_url) {
        this.id = id;
        this.player_name = player_name;
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
