package solution.piece.codelibrary.ModalClass;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
@Keep
public class ApplicationModal {

    String update_type;
    String update_version;
    String facebook_banner_id;
    String facebook_inter_id;


    public String getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(String update_type) {
        this.update_type = update_type;
    }

    public String getUpdate_version() {
        return update_version;
    }

    public void setUpdate_version(String update_version) {
        this.update_version = update_version;
    }

    public String getFacebook_banner_id() {
        return facebook_banner_id;
    }

    public void setFacebook_banner_id(String facebook_banner_id) {
        this.facebook_banner_id = facebook_banner_id;
    }

    public String getFacebook_inter_id() {
        return facebook_inter_id;
    }

    public void setFacebook_inter_id(String facebook_inter_id) {
        this.facebook_inter_id = facebook_inter_id;
    }

}
