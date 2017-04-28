package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.json.JSONObject;
import org.greenrobot.greendao.annotation.Generated;

import yanislav.com.autodata.utils.IVisitor;

/**
 * Created by yani on 20.2.2017 г..
 */

@Entity
public class Model extends BaseAutodataModelEntity
                   implements Parcelable
{
    private String brand;
    @Id
    long id;
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
        id = in.readLong();
        image = in.readString();
        name = in.readString();
    }

    @Generated(hash = 1618906368)
    public Model(String brand, long id, String image, String name) {
        this.brand = brand;
        this.id = id;
        this.image = image;
        this.name = name;
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

    @Override
    public long getId()
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

    public void setId(long paramInt)
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
        dest.writeLong(id);
        dest.writeString(image);
        dest.writeString(name);
    }

    @Override
    public boolean contains(String constraint)
    {
        return this.name.toUpperCase()
                        .contains(constraint.toUpperCase());
    }


    @Override
    public <T> T accept(IVisitor<T> visitor)
    {
        return visitor.visit(this);
    }

    @Override
    public boolean areContentsTheSame(BaseAutodataModelEntity baseAutodataModelEntity)
    {
        return false;
    }
}

