package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.json.JSONObject;

import yanislav.com.autodata.utils.IVisitor;


/**
 * Created by yani on 19.2.2017 г..
 */

@Entity
public class Brand extends BaseAutodataModelEntity
                   implements Parcelable
{
    @Id
    long id;
    @SerializedName("na")
    String name;

    public Brand() {}

    public Brand(JSONObject paramJSONObject)
    {
        this.name = paramJSONObject.optString("na");
        this.id = paramJSONObject.optLong("id");
    }

    protected Brand(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    @Generated(hash = 289657852)
    public Brand(long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setId(long paramLong)
    {
        this.id = paramLong;
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
        dest.writeLong(id);
        dest.writeString(name);
    }


    @Override
    public boolean contains(String constraint)
    {
        return name.toUpperCase()
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
        Brand brand = (Brand) baseAutodataModelEntity;
        return this.getName().equals(brand.getName());
    }
}