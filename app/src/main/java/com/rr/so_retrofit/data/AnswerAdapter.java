package com.rr.so_retrofit.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rr.so_retrofit.data.model.Item;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    List<Item> mItems;
    PostItemclickListener mitemclickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textview;
        PostItemclickListener itemclickListener;

        public ViewHolder(View itemView, PostItemclickListener itemclickListener){
            super(itemView);

            textview = itemView.findViewById(android.R.id.text1);
            this.itemclickListener = itemclickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            Item item = mItems.get(getAdapterPosition());
            itemclickListener.onPostClick(item.getAnswerId());
        }

    }

    public AnswerAdapter(List<Item> items, PostItemclickListener itemclickListener){
        mItems = items;
        mitemclickListener = itemclickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent,
                false);
        // What happens if attachToRoot is true? -
        // https://www.bignerdranch.com/blog/understanding-androids-layoutinflater-inflate/

        ViewHolder vh = new ViewHolder(view, mitemclickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = mItems.get(position);
        holder.textview.setText(item.getOwner().getDisplayName()) ;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items){
        mItems = items;
        notifyDataSetChanged();
    }

    public interface PostItemclickListener{
        public void onPostClick(long id);
    }
}
