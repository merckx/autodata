package yanislav.com.autodata.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import yanislav.com.autodata.utils.IVisitor;

/**
 * Created by yani on 15.3.2017 г..
 */

@Entity
public class DetailsInfoData extends BaseAutodataModelEntity
{
    @Id(autoincrement = true)
    private long id;
    private long carListInfoDataId;
    private String key;
    private String value;

    @Generated(hash = 1071103026)
    public DetailsInfoData(long id, long carListInfoDataId, String key,
            String value) {
        this.id = id;
        this.carListInfoDataId = carListInfoDataId;
        this.key = key;
        this.value = value;
    }

    @Generated(hash = 2023883287)
    public DetailsInfoData() {
    }

    public String getKey()
    {
        return this.key;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setKey(String paramString)
    {
        this.key = paramString;
    }

    public void setValue(String paramString)
    {
        this.value = paramString;
    }

    @Override
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getCarListInfoDataId()
    {
        return carListInfoDataId;
    }

    public void setCarListInfoDataId(long carListInfoDataId)
    {
        this.carListInfoDataId = carListInfoDataId;
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

