package com.GF.verbum.ui.pantallajuegos.Sintaxis;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.SintaxisEntity;
import com.GF.verbum.R;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MytiposSintaxisRecyclerViewAdapter extends RecyclerView.Adapter<MytiposSintaxisRecyclerViewAdapter.ViewHolder> {

    private final List<SintaxisEntity> mValues;
    private final tiposSintaxisFragment.OnListFragmentInteractionListener mListener;
    private Context ctx;


    public MytiposSintaxisRecyclerViewAdapter(List<SintaxisEntity> items,Context context, tiposSintaxisFragment.OnListFragmentInteractionListener listener) {
        ctx=context;
        mValues = items;
        mListener=listener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sintaxist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNombre());
        holder.mIdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prueba = new Intent(ctx, explicacionSintaxisActivity.class);
                prueba.putExtra("nombre",mValues.get(position).getNombre());
                prueba.putExtra("texto",mValues.get(position).getTexto());
                ctx.startActivity(prueba);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final Button mIdView;
        public SintaxisEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.TV_nombre);

        }

        @Override
        @NonNull
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}