package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yani on 12.3.2017 г..
 */
public class CarListInfoData implements Parcelable
{
    private String brand;
    int id;
    private String model;
    String name;
    String years;

    public CarListInfoData()
    {

    }

    protected CarListInfoData(Parcel in) {
        brand = in.readString();
        id = in.readInt();
        model = in.readString();
        name = in.readString();
        years = in.readString();
    }

    public static final Creator<CarListInfoData> CREATOR = new Creator<CarListInfoData>() {
        @Override
        public CarListInfoData createFromParcel(Parcel in) {
            return new CarListInfoData(in);
        }

        @Override
        public CarListInfoData[] newArray(int size) {
            return new CarListInfoData[size];
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

    public String getModel()
    {
        return this.model;
    }

    public String getName()
    {
        return this.name;
    }

    public String getYears()
    {
        return this.years;
    }

    public void setBrand(String paramString)
    {
        this.brand = paramString;
    }

    public void setId(int paramInt)
    {
        this.id = paramInt;
    }

    public void setModel(String paramString)
    {
        this.model = paramString;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }

    public void setYears(String paramString)
    {
        this.years = paramString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeInt(id);
        dest.writeString(model);
        dest.writeString(name);
        dest.writeString(years);
    }
}
