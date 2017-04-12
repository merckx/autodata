package yanislav.com.autodata.model;

/**
 * Created by yani on 26.3.2017 г..
 */

public class ImagesInfoData extends BaseAutodataModelEntity
{
    private String big;
    private String copyRight;
    private String small;

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
}
