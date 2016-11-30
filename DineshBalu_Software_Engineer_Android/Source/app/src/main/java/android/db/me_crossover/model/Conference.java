package android.db.me_crossover.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Caritor on 11/29/2016.
 */
public class Conference implements Parcelable {
    int _id;
    String title;
    String description;
    String from_date;
    String to_date;
    String venue;
    int manager;

    public Conference(Parcel in) {
        _id = in.readInt();
        title = in.readString();
        description = in.readString();
        from_date = in.readString();
        to_date = in.readString();
        venue = in.readString();
        manager = in.readInt();
    }

    public static final Creator<Conference> CREATOR = new Creator<Conference>() {
        @Override
        public Conference createFromParcel(Parcel in) {
            return new Conference(in);
        }

        @Override
        public Conference[] newArray(int size) {
            return new Conference[size];
        }
    };

    public Conference(Cursor c) {
        _id = c.getInt( 0 );
        title = c.getString(1);
        description = c.getString(2);
        from_date = c.getString(2);
        to_date = c.getString(2);
        venue = c.getString(2);
        manager = c.getInt(3);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(from_date);
        dest.writeString(to_date);
        dest.writeString(venue);
        dest.writeInt(manager);
    }

    public String getTitle() {

        return title;
    }
}
