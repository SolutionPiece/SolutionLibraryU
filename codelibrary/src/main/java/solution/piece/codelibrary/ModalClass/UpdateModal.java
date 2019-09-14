package solution.piece.codelibrary.ModalClass;

import androidx.annotation.Keep;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
@Keep
public class UpdateModal
{
    String update_status;
    String date_day;

    public UpdateModal(String update_status, String date_day) {
        this.update_status = update_status;
        this.date_day = date_day;
    }

    public String getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(String update_status) {
        this.update_status = update_status;
    }

    public String getDate_day() {
        return date_day;
    }

    public void setDate_day(String date_day) {
        this.date_day = date_day;
    }

}