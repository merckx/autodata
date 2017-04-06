package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yani on 25.2.2017 г..
 */
public class Submodel implements Parcelable
{
    @SerializedName("n")
    private int begin;
    private String brand;
    @SerializedName("k")
    private int end;
    private int id;
    @SerializedName("im")
    private String image;
    private String model;
    @SerializedName("na")
    private String name;

    protected Submodel(Parcel in) {
        begin = in.readInt();
        brand = in.readString();
        end = in.readInt();
        id = in.readInt();
        image = in.readString();
        model = in.readString();
        name = in.readString();
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

    public int getId()
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

    public void setId(int paramInt)
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
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(model);
        dest.writeString(name);
    }
}