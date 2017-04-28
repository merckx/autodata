package yanislav.com.autodata.utils;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.BiFunction;
import yanislav.com.autodata.adapters.viewholders.BaseViewHolder;
import yanislav.com.autodata.model.BaseAutodataModelEntity;

/**
 * Created by yani on 25.2.2017 г..
 */

public abstract class AutoDataAdapter<T extends BaseAutodataModelEntity,VH extends BaseViewHolder<T>>
        extends RecyclerView.Adapter<VH>
        implements ISwitchableContent<T> , Filterable{

    protected List<T> content = new ArrayList<>();
    private List<T> mStringFilterList = new ArrayList<>();
    protected Context context;
    private Class<VH> vhClass;
    private ValueFilter valueFilter;


    public AutoDataAdapter(Class<VH> vhClass, List<T> content, Context context) {
        this.content = content;
        this.mStringFilterList = new ArrayList<>(content);
        this.context = context;
        this.vhClass = vhClass;

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
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback()
        {
            @Override
            public int getOldListSize()
            {
                return content.size();
            }

            @Override
            public int getNewListSize()
            {
                return newContent.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
            {
                return content.get(oldItemPosition).getId()
                       == newContent.get(newItemPosition).getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
            {
                return content.get(oldItemPosition)
                        .areContentsTheSame(newContent.get(newItemPosition));
            }
        });
        content.clear();
        mStringFilterList.clear();
        if (newContent != null)
        {
            content.addAll(newContent);
            mStringFilterList.addAll(content);
        }

        diffResult.dispatchUpdatesTo(this);

//        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return content.size();
    }


    @Override
    public Filter getFilter()
    {
        if (valueFilter == null)
        {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if (mStringFilterList.get(i).contains(constraint.toString())){
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            content = (List) results.values;
            notifyDataSetChanged();
        }

    }
}
