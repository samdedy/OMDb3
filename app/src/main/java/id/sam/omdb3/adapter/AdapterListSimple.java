package id.sam.omdb3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.sam.omdb3.R;
import id.sam.omdb3.model.Search;

public class AdapterListSimple extends RecyclerView.Adapter<AdapterListSimple.ViewHolder> {

    List<Search> data;
    Context context;

    public AdapterListSimple(Context context, List<Search> data){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);//untuk menempelkan layout ke list item


        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.txtTitle.setText(data.get(position).getTitle());
        holder.txtRating.setText(data.get(position).getYear());
        holder.txtGenre.setText(data.get(position).getType());
        holder.txtDirectedBy.setText(data.get(position).getImdbID());

        String image = data.get(position).getPoster();
        Picasso.get().load(image).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtTitle,txtRating,txtGenre,txtDirectedBy;
        public CardView parentLayout;
        public ImageView imgPoster;

        public ViewHolder(View v) {
            super(v);

            imgPoster = v.findViewById(R.id.imgPoster);
            txtTitle = v.findViewById(R.id.txtTitle);
            txtRating = v.findViewById(R.id.txtYear);
            txtGenre = v.findViewById(R.id.txtType);
            txtDirectedBy = v.findViewById(R.id.txtDirectedBy);
            parentLayout = v.findViewById(R.id.cardViewListItemMovie);
        }
    }
}
