package com.GF.verbum.ui.pantallajuegos.record;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GF.verbum.R;
import com.GF.verbum.commun.user;


import java.util.List;


public class MyplayersRecyclerViewAdapter extends RecyclerView.Adapter<MyplayersRecyclerViewAdapter.ViewHolder> {

    private final List<user> mValues;

    public MyplayersRecyclerViewAdapter(List<user> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_record_players, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(String.valueOf(mValues.get(position).getPoints()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public user mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.TV_nombre);
            mContentView =  view.findViewById(R.id.TV_puntuacion);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}