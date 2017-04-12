package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by yani on 20.2.2017 г..
 */

public class Model extends BaseAutodataModelEntity
                   implements Parcelable
{
    private String brand;
    int id;
    @SerializedName("im")
    String image;
    @SerializedName("na")
    String name;

    public Model() {}

    public Model(JSONObject paramJSONObject)
    {
        this.name = paramJSONObject.optString("na");
        this.id = paramJSONObject.optInt("id");
    }

    protected Model(Parcel in) {
        brand = in.readString();
        id = in.readInt();
        image = in.readString();
        name = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public String getBrand()
    {
        return this.brand;
    }

    public int getId()
    {
        return this.id;
    }

    public String getImage()
    {
        return this.image;
    }

    public String getName()
    {
        return this.name;
    }

    public void setBrand(String paramString)
    {
        this.brand = paramString;
    }

    public void setId(int paramInt)
    {
        this.id = paramInt;
    }

    public void setImage(String paramString)
    {
        this.image = paramString;
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
        dest.writeString(brand);
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(name);
    }

    @Override
    public boolean contains(String constraint)
    {
        return this.name.toUpperCase()
                        .contains(constraint.toUpperCase());
    }
}

