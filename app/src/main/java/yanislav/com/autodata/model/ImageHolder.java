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
public class ImageHolder extends BaseAutodataModelEntity
                         implements Parcelable
{
    @Id
    private long id;
    private String url;

    public ImageHolder()
    {

    }

    protected ImageHolder(Parcel in) {
        id = in.readLong();
        url = in.readString();
    }

    @Generated(hash = 1986561113)
    public ImageHolder(long id, String url) {
        this.id = id;
        this.url = url;
    }

    public static final Creator<ImageHolder> CREATOR = new Creator<ImageHolder>() {
        @Override
        public ImageHolder createFromParcel(Parcel in) {
            return new ImageHolder(in);
        }

        @Override
        public ImageHolder[] newArray(int size) {
            return new ImageHolder[size];
        }
    };

    @Override
    public long getId()
    {
        return this.id;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setId(long paramInt)
    {
        this.id = paramInt;
    }

    public void setUrl(String paramString)
    {
        this.url = paramString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(url);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor)
    {
        return null;
    }

    @Override
    public boolean areContentsTheSame(BaseAutodataModelEntity baseAutodataModelEntity)
    {
        return false;
    }
}
