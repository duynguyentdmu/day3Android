package vnpt.binhduong.day3.apdater;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vnpt.binhduong.day3.R;
import vnpt.binhduong.day3.interfaces.NewsOnClick;
import vnpt.binhduong.day3.model.Item;

public class NewsAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Item> list;
    private NewsOnClick iOnClick;

    public NewsAdapter(Activity activity, List<Item> list) {
        this.activity = activity;
        this.list = list;
    }

    public void setiOnClick(NewsOnClick iOnClick) {
        this.iOnClick = iOnClick;
    }

    public void reloadData(List<Item> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate( R.layout.item_news, parent, false );
        NewsHolder holder = new NewsHolder( itemView );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item model = list.get( position );
        NewsHolder newsHolder = (NewsHolder) holder;
        newsHolder.tvDate.setText( model.getDate() );
        newsHolder.tvTitle.setText( model.getTitle() );
        newsHolder.tvContent.setText( model.getContent().getDescription() );
        Glide.with( activity ).load( model.getImage() ).into( newsHolder.ivCover );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTitle, tvContent;
        ImageView ivCover;

        public NewsHolder(@NonNull View itemView) {
            super( itemView );
            tvDate = itemView.findViewById( R.id.tvDate );
            tvContent = itemView.findViewById( R.id.tvContent );
            tvTitle = itemView.findViewById( R.id.tvTitle );
            ivCover = itemView.findViewById( R.id.ivCover );


            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnClick.onClickItem( getAdapterPosition() );
                }
            });
        }

    }
}
