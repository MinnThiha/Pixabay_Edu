package net.mycompany.pixabay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.mycompany.pixabay.R;
import net.mycompany.pixabay.obj.Hits;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    Context ctxt;
    List<Hits> hits=new ArrayList<>();

    public PhotoAdapter(Context ctxt) {
        this.ctxt = ctxt;
    }

    public void setHits(List<Hits> hits){
        this.hits=hits;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(ctxt).inflate(R.layout.sample_view,parent,false);
        return new PhotoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Hits h=hits.get(position);
        holder.tv.setText(h.getUser());
        Picasso.get().load(h.getWebformatURL()).placeholder(R.drawable.ic_arrow_downward_black_24dp).into(holder.iv);
        Picasso.get().load(h.getUserImageURL()).placeholder(R.drawable.ic_arrow_downward_black_24dp).into(holder.iv2);
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;
        ImageView iv2;
        public PhotoHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv_photo_sv);
            iv=itemView.findViewById(R.id.iv_photo_sv);
            iv2=itemView.findViewById(R.id.iv_user_sv);
        }
    }
}
