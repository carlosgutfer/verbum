package com.GF.verbum.ui.pantallajuegos.Sintaxis;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.GF.verbum.DB.Entities.SintaxisEntity;
import com.GF.verbum.R;

import java.util.ArrayList;
import java.util.List;

public class tiposSintaxisFragment extends Fragment  {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;


    private SintaxisViewModel viewModel;
    public tiposSintaxisFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static tiposSintaxisFragment newInstance(int columnCount) {
        tiposSintaxisFragment fragment = new tiposSintaxisFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sintaxis_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            final List<SintaxisEntity> allSintaxis= new ArrayList<>();
            viewModel = new ViewModelProvider(this).get(SintaxisViewModel.class);
            viewModel.getAllSintaxis().observe(getActivity(), new Observer<List<SintaxisEntity>>() {
                @Override
                public void onChanged(List<SintaxisEntity> sintaxisEntities) {
                    recyclerView.setAdapter(new MytiposSintaxisRecyclerViewAdapter( sintaxisEntities,getActivity(),mListener));


                }
            });

        }
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {

    }
}