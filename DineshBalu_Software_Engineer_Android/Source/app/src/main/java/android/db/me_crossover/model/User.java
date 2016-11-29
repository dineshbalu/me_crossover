package android.db.me_crossover.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dineshbalu on 27/11/16.
 */
public class User implements Parcelable {


    int _id;
    String fullname;
    String email;

    public User(Parcel in) {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(Cursor c) {

        _id = c.getInt( 0 );
        fullname = c.getString(1);
        email = c.getString(2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public String getName() {
        return fullname;
    }
}
