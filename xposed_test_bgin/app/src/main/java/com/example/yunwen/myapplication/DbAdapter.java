package com.example.yunwen.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunwen.myapplication.dao.XModuleLog;
import com.example.yunwen.myapplication.dao.XModuleLogRepo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yunwen on 4/10/2016.
 */
public class DbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    ArrayList<HashMap<String, String>> data;


    public DbAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.data = data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(mLayoutInflater.inflate(R.layout.view_db_entry, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        try {
            Log.d("DbFragment", data.get(position).get("id"));
            Log.d("DbFragment", data.get(position).get("topic"));
            ((TextViewHolder) holder).mTextView.setText(data.get(position).get("topic"));
            final String dataContent = ((TextViewHolder) holder).mTextView.getText().toString();
        } catch (Exception e) {
            Log.d("FragmentAdapter", e.toString());
        }
    }
    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        // @Bind(R.id.text_view)
        TextView mTextView;
        ImageView mImageView;

        TextViewHolder(View view) {
            super(view);
            mTextView = (TextView) itemView.findViewById(R.id.algorithm_name);
            mImageView = (ImageView) itemView.findViewById(R.id.algorithm_delete);
        }
    }
}
