package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by yani on 19.2.2017 г..
 */


public class Brand implements Parcelable
{
    int id;
    @SerializedName("na")
    String name;

    public Brand() {}

    public Brand(JSONObject paramJSONObject)
    {
        this.name = paramJSONObject.optString("na");
        this.id = paramJSONObject.optInt("id");
    }

    protected Brand(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Brand> CREATOR = new Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setId(int paramInt)
    {
        this.id = paramInt;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}