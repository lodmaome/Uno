package uno.unisal.com.uno;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import uno.unisal.com.uno.classes.Carta;
import uno.unisal.com.uno.util.CallableStatement;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Carta> handView;
    private List <Integer> cardsPictures = new ArrayList<>();
    private Context mContext;
    private CallableStatement callable;

    //gambiarra para pegar a carta da mesa
    Carta played = JogoActivity.cardPlayed;
    ImageView playedView = JogoActivity.cardPlayedView;

    public RecyclerViewAdapter(List<Carta> handView, List<Integer> cardsPictures, Context mContext, CallableStatement callable) {
        this.handView = handView;
        this.cardsPictures = cardsPictures;
        this.mContext = mContext;
        this.callable = callable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listview, parent, false);
        return new ViewHolder((cardView));
    }

    //toque em carquer carta
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(cardsPictures.get(position))
                .into(holder.imageViewCarta);

        holder.imageViewCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on an image: " + cardsPictures.get(position));
                //Toast.makeText(mContext, cardsPictures.get(position), Toast.LENGTH_SHORT).show();
                //remover carta teste
                if (callable.canPlayCard(position)) {
                    cardsPictures.remove(position);
                    played = handView.get(position);
                    playedView.setImageResource(handView.get(position).getImage());
                    handView.remove(position);
                    callable.callGameLoop();
                } else{
                    Toast.makeText(mContext, "Play a " + played.getColor().toString() + " card or any " + played.getSymbol().toString() + ".", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardsPictures.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewCarta;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewCarta = itemView.findViewById(R.id.imageViewCardId);
        }
    }
}
