package yanislav.com.autodata.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yani on 12.3.2017 г..
 */

public class ImageHolder extends BaseAutodataModelEntity
                         implements Parcelable
{
    private int id;
    private String url;

    public ImageHolder()
    {

    }

    protected ImageHolder(Parcel in) {
        id = in.readInt();
        url = in.readString();
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

    public int getId()
    {
        return this.id;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setId(int paramInt)
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
        dest.writeInt(id);
        dest.writeString(url);
    }
}
