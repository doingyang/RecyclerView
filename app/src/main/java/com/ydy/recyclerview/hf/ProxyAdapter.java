package com.ydy.recyclerview.hf;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public final class ProxyAdapter extends RecyclerView.Adapter {

    private final HeaderAndFooterRecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private final RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart + getPositionOffset(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart + getPositionOffset(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart + getPositionOffset(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if (itemCount == 1) {
                notifyItemMoved(fromPosition + getPositionOffset(), toPosition + getPositionOffset());
            } else {
                notifyDataSetChanged();
            }
        }

    };

    ProxyAdapter(@NonNull HeaderAndFooterRecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    void setAdapter(RecyclerView.Adapter adapter) {
        if (this.adapter != null) {
            this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
            this.adapter.onDetachedFromRecyclerView(recyclerView);
        }
        this.adapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(adapterDataObserver);
            adapter.onAttachedToRecyclerView(recyclerView);
        }
    }

    void notifyHeaderInserted() {
        if (recyclerView.getHeaderViewCount() == 1) {
            notifyItemInserted(0);
        }
    }

    void notifyHeaderRemoved() {
        if (recyclerView.getHeaderViewCount() == 0) {
            notifyItemRemoved(0);
        }
    }

    void notifyFooterInserted() {
        if (recyclerView.getFooterViewCount() == 1) {
            notifyItemInserted(getItemCount() - 1);
        }
    }

    void notifyFooterRemoved() {
        if (recyclerView.getFooterViewCount() == 0) {
            notifyItemRemoved(getItemCount());
        }
    }

    public boolean isShowHeaderViewHolder() {
        return recyclerView.getHeaderViewCount() > 0;
    }

    public boolean isShowFooterViewHolder() {
        return recyclerView.getFooterViewCount() > 0;
    }

    public boolean isHeaderViewHolderPosition(int position) {
        return position == 0 && isShowHeaderViewHolder();
    }

    public boolean isFooterViewHolderPosition(int position) {
        return position == getItemCount() - 1 && isShowFooterViewHolder();
    }

    public int getHeaderViewHolderCount() {
        return isShowHeaderViewHolder() ? 1 : 0;
    }

    public int getFooterViewHolderCount() {
        return isShowFooterViewHolder() ? 1 : 0;
    }

    public int getPositionOffset() {
        return getHeaderViewHolderCount();
    }

    @Override
    public int getItemCount() {
        return (adapter == null ? 0 : adapter.getItemCount()) + getHeaderViewHolderCount() + getFooterViewHolderCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewHolderPosition(position)) {
            return FixedViewHolder.VIEW_TYPE_HEADER;
        } else if (isFooterViewHolderPosition(position)) {
            return FixedViewHolder.VIEW_TYPE_FOOTER;
        } else {
            if (adapter != null) {
                int viewType = adapter.getItemViewType(position - getPositionOffset());
                if (viewType == FixedViewHolder.VIEW_TYPE_HEADER) {
                    throw new RuntimeException(FixedViewHolder.VIEW_TYPE_HEADER + " is already used for view type Header, please replace another value.");
                } else if (viewType == FixedViewHolder.VIEW_TYPE_FOOTER) {
                    throw new RuntimeException(FixedViewHolder.VIEW_TYPE_FOOTER + " is already used for view type Footer, please replace another value.");
                } else {
                    return viewType;
                }
            } else {
                return 0;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case FixedViewHolder.VIEW_TYPE_HEADER:
                return new FixedViewHolder(recyclerView.getHeaderContainer());
            case FixedViewHolder.VIEW_TYPE_FOOTER:
                return new FixedViewHolder(recyclerView.getFooterContainer());
            default:
                if (adapter != null) {
                    return adapter.onCreateViewHolder(parent, viewType);
                } else {
                    return null;
                }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == FixedViewHolder.VIEW_TYPE_HEADER) {
            recyclerView.adjustFixedViewContainerLayoutParamsAndOrientation(recyclerView.getHeaderContainer());
        } else if (holder.getItemViewType() == FixedViewHolder.VIEW_TYPE_FOOTER) {
            recyclerView.adjustFixedViewContainerLayoutParamsAndOrientation(recyclerView.getFooterContainer());
        } else if (adapter != null) {
            //noinspection unchecked
            adapter.onBindViewHolder(holder, position - getPositionOffset());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if (holder.getItemViewType() == FixedViewHolder.VIEW_TYPE_HEADER) {
            recyclerView.adjustFixedViewContainerLayoutParamsAndOrientation(recyclerView.getHeaderContainer());
        } else if (holder.getItemViewType() == FixedViewHolder.VIEW_TYPE_FOOTER) {
            recyclerView.adjustFixedViewContainerLayoutParamsAndOrientation(recyclerView.getFooterContainer());
        } else if (adapter != null) {
            //noinspection unchecked
            adapter.onBindViewHolder(holder, position - getPositionOffset(), payloads);
        }
    }

    @Override
    public long getItemId(int position) {
        if (adapter != null && !isHeaderViewHolderPosition(position) && !isFooterViewHolderPosition(position)) {
            return adapter.getItemId(position - getPositionOffset());
        } else {
            return RecyclerView.NO_ID;
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (adapter != null && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_HEADER && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_FOOTER) {
            //noinspection unchecked
            adapter.onViewRecycled(holder);
        }
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        //noinspection SimplifiableIfStatement
        if (adapter != null && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_HEADER && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_FOOTER) {
            //noinspection unchecked
            return adapter.onFailedToRecycleView(holder);
        } else {
            return false;
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (adapter != null && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_HEADER && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_FOOTER) {
            //noinspection unchecked
            adapter.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (adapter != null && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_HEADER && holder.getItemViewType() != FixedViewHolder.VIEW_TYPE_FOOTER) {
            //noinspection unchecked
            adapter.onViewDetachedFromWindow(holder);
        }
    }

}
