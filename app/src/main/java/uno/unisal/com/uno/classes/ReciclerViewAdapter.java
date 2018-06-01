package uno.unisal.com.uno.classes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import uno.unisal.com.uno.R;

public class ReciclerViewAdapter extends RecyclerView.Adapter {

    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;
    private static final String TAG = "RecyclerViewAdapter";
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateVIewHolder: called.")
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imagem;
        public ViewHolder(View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imagemID);
        }
    }
}
