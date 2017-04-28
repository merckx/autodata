package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import yanislav.com.autodata.utils.IVisitor;

/**
 * Created by yani on 25.2.2017 г..
 */
@Entity
public class Submodel extends BaseAutodataModelEntity
                      implements Parcelable
{
    @SerializedName("n")
    private int begin;
    private String brand;
    @SerializedName("k")
    private int end;
    @Id
    private long id;
    @SerializedName("im")
    private String image;
    private String model;
    @SerializedName("na")
    private String name;

    protected Submodel(Parcel in) {
        begin = in.readInt();
        brand = in.readString();
        end = in.readInt();
        id = in.readLong();
        image = in.readString();
        model = in.readString();
        name = in.readString();
    }

    @Generated(hash = 342437937)
    public Submodel(int begin, String brand, int end, long id, String image,
            String model, String name) {
        this.begin = begin;
        this.brand = brand;
        this.end = end;
        this.id = id;
        this.image = image;
        this.model = model;
        this.name = name;
    }

    @Generated(hash = 1082278455)
    public Submodel() {
    }

    public static final Creator<Submodel> CREATOR = new Creator<Submodel>() {
        @Override
        public Submodel createFromParcel(Parcel in) {
            return new Submodel(in);
        }

        @Override
        public Submodel[] newArray(int size) {
            return new Submodel[size];
        }
    };

    public int getBegin()
    {
        return this.begin;
    }

    public String getBrand()
    {
        return this.brand;
    }

    public int getEnd()
    {
        return this.end;
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

    public String getModel()
    {
        return this.model;
    }

    public String getName()
    {
        return this.name;
    }

    public void setBegin(int paramInt)
    {
        this.begin = paramInt;
    }

    public void setBrand(String paramString)
    {
        this.brand = paramString;
    }

    public void setEnd(int paramInt)
    {
        this.end = paramInt;
    }

    public void setId(long paramInt)
    {
        this.id = paramInt;
    }

    public void setImage(String paramString)
    {
        this.image = paramString;
    }

    public void setModel(String paramString)
    {
        this.model = paramString;
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
        dest.writeInt(begin);
        dest.writeString(brand);
        dest.writeInt(end);
        dest.writeLong(id);
        dest.writeString(image);
        dest.writeString(model);
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