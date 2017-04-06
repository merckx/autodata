package yanislav.com.autodata.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import yanislav.com.autodata.adapters.viewholders.BaseViewHolder;

/**
 * Created by yani on 25.2.2017 г..
 */

public abstract class AutoDataAdapter<T ,VH extends BaseViewHolder<T>>
        extends RecyclerView.Adapter<VH>
        implements ISwitchableContent<T> {

    protected List<T> content = new ArrayList<>();
    protected Context context;
    private Class<VH> vhClass;

    private View.OnClickListener onItemClickListener;

    public AutoDataAdapter(Class<VH> vhClass, List<T> content, Context context) {
        this.content = content;
        this.context = context;
        this.vhClass = vhClass;
        onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    protected abstract int getLayoutItemId();

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(getLayoutItemId(), parent, false);

        Object object = null;
        try {
            Class<?> clazz = Class.forName(vhClass.getName());
            Constructor<?> ctor = clazz.getConstructor(View.class);
            object = ctor.newInstance(new Object[]{v});
            final RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) object;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)
                        onItemClicked(getContentAt(position));
                }
            });
        }
        catch (Exception e)
        {
            Log.e("AUTODATA ADAPTER", "onCreateViewHolder: ", e);
        }

        return (VH)object;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(getContentAt(position), context);
    }


    protected T getContentAt(int position)
    {
        return content.get(position);
    }

    protected void onItemClicked(T bean)
    {

    }



    @Override
    public void switchContent(List<T> newContent) {
        content.clear();
        if (newContent != null)
        {
            content.addAll(newContent);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return content.size();
    }

}
