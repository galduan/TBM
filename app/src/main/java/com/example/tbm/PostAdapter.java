package com.example.tbm;

import android.app.Dialog;
import android.graphics.BitmapShader;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    ArrayList<ItemCard> mItemCardArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageResourceView;
        private TextView mCaptionView;
        private ImageView mLikeIM;
        private ImageView mLoveIM;
        private ImageView mBraveIM;
        private ImageView mHappyIM;
        private ImageView mSadIM;
        private Button mSaveBTN;
        private TextView likeC,braveC,loveC,sadC,happyC;
        private DatabaseReference ref;
        private EditText HashtagsET;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageResourceView = itemView.findViewById(R.id.PostIM);
            mCaptionView = itemView.findViewById(R.id.CaptionTV);
            mLikeIM = itemView.findViewById(R.id.Like);
            mLoveIM = itemView.findViewById(R.id.Love);
            mBraveIM = itemView.findViewById(R.id.Brave);
            mHappyIM = itemView.findViewById(R.id.Happy);
            mSadIM = itemView.findViewById(R.id.Sad);
            mSaveBTN = itemView.findViewById(R.id.saveBTN);

            likeC = itemView.findViewById((R.id.likeC));
            sadC= itemView.findViewById((R.id.sadC));
            braveC= itemView.findViewById((R.id.braveC));
            happyC= itemView.findViewById((R.id.happyC));
            loveC= itemView.findViewById((R.id.loveC));

            HashtagsET = itemView.findViewById(R.id.Hashtags);

            ref = FirebaseDatabase.getInstance().getReference().child("main");



        }
    }

    public PostAdapter(ArrayList<ItemCard> itemCardsList){
        mItemCardArrayList = itemCardsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {
            int width = holder.mImageResourceView.getWidth();
            holder.mImageResourceView.setMaxHeight(width);
        final ItemCard itemCard = mItemCardArrayList.get(position);
        new UrlPic(itemCard.getUrl(),holder.mImageResourceView);

        holder.HashtagsET.setText(mItemCardArrayList.get(position).getHashtag());

//        UrlPic urlPic = new UrlPic(itemCard.getUrl(),holder.mImageResourceView);
        //holder.mImageResourceView.setImageResource((itemCard.getImageResource() ));
        holder.mCaptionView.setText(itemCard.getCaption());


        holder.likeC.setText("like: "+mItemCardArrayList.get(position).getLikeCounter());
        holder.sadC.setText("sad: "+mItemCardArrayList.get(position).getSadCounter());
        holder.loveC.setText("love: "+mItemCardArrayList.get(position).getLoveCounter());
        holder.happyC.setText("like: "+mItemCardArrayList.get(position).getHappyCounter());
        holder.braveC.setText("like: "+mItemCardArrayList.get(position).getBraveCounter());

        //TO DO: send to server
        holder.mBraveIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int braveC = mItemCardArrayList.get(position).getBraveCounter();
                if(!itemCard.isBraveClicked()) {
                    holder.mBraveIM.setImageResource(itemCard.getEndBraveIMG());
//                    braveC++;
//                    holder.ref.child("posts/MX8OtODuIVf7TBYaxPv/reactions").child("brave").setValue(braveC);
                    itemCard.increaseBraveCounter();
                }
                else {
                    holder.mBraveIM.setImageResource(itemCard.getStartBraveIMG());
//                    braveC--;
//                    holder.ref.child("posts/-MX8OtODuIVf7TBYaxPv/reactions").child("brave").setValue(braveC);
                    itemCard.decreaseBraveCounter();

                    //MX8OtODuIVf7TBYaxPv
                }
            }
        });
        holder.mLoveIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!itemCard.isLoveClicked()) {
                    holder.mLoveIM.setImageResource(itemCard.getEndLoveIMG());
                    itemCard.increaseLoveCounter();
                }
                else {
                    holder.mLoveIM.setImageResource(itemCard.getStartLoveIMG());
                    itemCard.decreaseLoveCounter();
                }
            }
        });
        holder.mHappyIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!itemCard.isHappyClicked()) {
                    holder.mHappyIM.setImageResource(itemCard.getEndHappyIMG());
                    itemCard.increaseHappyCounter();
                }
                else {
                    holder.mHappyIM.setImageResource(itemCard.getStartHappyIMG());
                    itemCard.decreaseHappyCounter();
                }
            }
        });
        holder.mLikeIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!itemCard.isLikeClicked()) {
                    holder.mLikeIM.setImageResource(itemCard.getEndLikeIMG());
                    itemCard.increaseLikeCounter();
                }
                else {
                    holder.mLikeIM.setImageResource(itemCard.getStartLikeIMG());
                    itemCard.decreaseLikeCounter();
                }
            }
        });
        holder.mSadIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!itemCard.isSadCClicked()) {
                    holder.mSadIM.setImageResource(itemCard.getEndSadIMG());
                    itemCard.increaseSadCounter();
                }
                else {
                    holder.mSadIM.setImageResource(itemCard.getStartSadIMG());
                    itemCard.decreaseSadCounter();
                }
            }
        });
        holder.mSaveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), " like: "+itemCard.getLikeCounter()+
                                                        " love: " + itemCard.getLoveCounter()+
                                                        " brave: " + itemCard.getBraveCounter()+
                                                        " sad: " + itemCard.getSadCounter()+
                                                        " happy: "+ itemCard.getHappyCounter()
                                                        , Toast.LENGTH_SHORT).show();


//                holder.HashtagsET.setText(mItemCardArrayList.get(position).getHashtag());


//                String[] braveWords = StringToListOfWords(holder.HashtagsET.getText().toString());
//                for(int i = 0; i<braveWords.length;i++)
//                {
//                    Log.d("liad",braveWords[i]);
//                }
//                //Toast.makeText(v.getContext(), StringToListOfWords(holder.HashtagsET.getText().toString()).length + " ", Toast.LENGTH_SHORT).show();

            }
        });
    }
//    private String[] StringToListOfWords(String sentence)
//    {
//        String[] words = sentence.split("\\s+");
//        for (int i = 0; i < words.length; i++) {
//            // You may want to check for a non-word character before blindly
//            // performing a replacement
//            // It may also be necessary to adjust the character class
//            words[i] = words[i].replaceAll("[^\\w]", "");
//        }
//        return words;
//    }
    @Override
    public int getItemCount() {
        return mItemCardArrayList.size();
    }



}
