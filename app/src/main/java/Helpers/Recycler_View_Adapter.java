package Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myinfo.R;

import java.util.ArrayList;

public class Recycler_View_Adapter extends RecyclerView.Adapter<Recycler_View_Adapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<RecyclerHelper> modules;

    public Recycler_View_Adapter(Context context, ArrayList<RecyclerHelper> modules, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.modules = modules;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Recycler_View_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_module, parent, false);
        return new Recycler_View_Adapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_View_Adapter.MyViewHolder holder, int position) {
        holder.vModuleName.setText(modules.get(position).getModuleName());
        holder.vModuleImg.setImageResource(modules.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView vModuleImg;
        TextView vModuleName;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            vModuleImg = itemView.findViewById(R.id.module_img);
            vModuleName = itemView.findViewById(R.id.module_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onModuleClick(position);
                        }
                    }
                }
            });
        }
    }
}
