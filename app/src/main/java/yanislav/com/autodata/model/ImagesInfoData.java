package yanislav.com.autodata.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import yanislav.com.autodata.utils.IVisitor;

/**
 * Created by yani on 26.3.2017 г..
 */
@Entity
public class ImagesInfoData extends BaseAutodataModelEntity
{
    @Id(autoincrement = true)
    long id;
    private String big;
    private String copyRight;
    private String small;

    @Generated(hash = 1176005252)
    public ImagesInfoData(long id, String big, String copyRight, String small) {
        this.id = id;
        this.big = big;
        this.copyRight = copyRight;
        this.small = small;
    }

    @Generated(hash = 1784668907)
    public ImagesInfoData() {
    }

    public boolean equals(Object paramObject)
    {
        if ((paramObject instanceof ImagesInfoData)) {
            return ((ImagesInfoData)paramObject).big.equals(this.big);
        }
        return super.equals(paramObject);
    }

    public String getBig()
    {
        return this.big;
    }

    public String getCopyRight()
    {
        return this.copyRight;
    }

    public String getSmall()
    {
        return this.small;
    }

    public void setBig(String paramString)
    {
        this.big = paramString;
    }

    public void setCopyRight(String paramString)
    {
        this.copyRight = paramString;
    }

    public void setSmall(String paramString)
    {
        this.small = paramString;
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
