package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import yanislav.com.autodata.utils.IVisitor;


/**
 * Created by yani on 12.3.2017 г..
 */
@Entity
public class CarListInfoData extends BaseAutodataModelEntity
                             implements Parcelable
{
    private String brand;
    @Id
    long id;
    private String model;
    String name;
    String years;

    public CarListInfoData()
    {

    }

    protected CarListInfoData(Parcel in) {
        brand = in.readString();
        id = in.readLong();
        model = in.readString();
        name = in.readString();
        years = in.readString();
    }

    @Generated(hash = 1620939010)
    public CarListInfoData(String brand, long id, String model, String name, String years) {
        this.brand = brand;
        this.id = id;
        this.model = model;
        this.name = name;
        this.years = years;
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

    @Override
    public long getId()
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

    public void setId(long paramInt)
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
        dest.writeLong(id);
        dest.writeString(model);
        dest.writeString(name);
        dest.writeString(years);
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
