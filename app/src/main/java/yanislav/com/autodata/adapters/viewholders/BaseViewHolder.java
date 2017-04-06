package yanislav.com.autodata.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by yani on 10.3.2017 г..
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T model, Context context);

//    public void setOnClickListener(View.OnClickListener listener)
//    {
//
//    }
}
