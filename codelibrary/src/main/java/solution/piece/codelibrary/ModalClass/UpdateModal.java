package solution.piece.codelibrary.ModalClass;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
@Keep
public class UpdateModal
{
    String update_status;
    String version_app_code;
    String version_update_code;
    String info_date;
    String info_day;

    public UpdateModal(String update_status, String version_app_code, String version_update_code, String info_date, String info_day) {
        this.update_status = update_status;
        this.version_app_code = version_app_code;
        this.version_update_code = version_update_code;
        this.info_date = info_date;
        this.info_day = info_day;
    }

    public String getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(String update_status) {
        this.update_status = update_status;
    }

    public String getVersion_app_code() {
        return version_app_code;
    }

    public void setVersion_app_code(String version_app_code) {
        this.version_app_code = version_app_code;
    }

    public String getVersion_update_code() {
        return version_update_code;
    }

    public void setVersion_update_code(String version_update_code) {
        this.version_update_code = version_update_code;
    }

    public String getInfo_date() {
        return info_date;
    }

    public void setInfo_date(String info_date) {
        this.info_date = info_date;
    }

    public String getInfo_day() {
        return info_day;
    }

    public void setInfo_day(String info_day) {
        this.info_day = info_day;
    }
}