package eg.kaboo.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import eg.kaboo.newsapp.R;
import eg.kaboo.newsapp.pojo.ArticlesItem;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private List<ArticlesItem> ArticlesItems;
    Context mContext;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void OnItemLongClick(int position);
    }

    @Inject
    public NewsAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.example, parent, false), onItemClickListener, onItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titleTxt.setText(ArticlesItems.get(position).getTitle());
        holder.bodyTxt.setText(ArticlesItems.get(position).getDescription());
        holder.sourceNameTxt.setText(ArticlesItems.get(position).getSource().getName());

        Glide.with(mContext)
                .load(ArticlesItems.get(position).getUrlToImage())
                .placeholder(R.drawable.loading)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return (ArticlesItems == null) ? 0 : ArticlesItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView titleTxt, bodyTxt, sourceNameTxt;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener, OnItemLongClickListener longClickListener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            titleTxt = itemView.findViewById(R.id.titleTextView);
            bodyTxt = itemView.findViewById(R.id.bodyTextView);
            sourceNameTxt = itemView.findViewById(R.id.sourceNameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.OnItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longClickListener.OnItemLongClick(position);
                        }
                    }
                    return true;
                }
            });
        }
    }

    public void setList(List<ArticlesItem> ArticlesItems) {
        this.ArticlesItems = ArticlesItems;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        onItemLongClickListener = itemLongClickListener;
    }

    public ArticlesItem getArticlesItemByPosition(int position) {
        return ArticlesItems.get(position);
    }

}
