package android.db.me_crossover.fragments;

import android.content.Context;
import android.content.Intent;
import android.db.me_crossover.R;
import android.db.me_crossover.SchedularActivity;
import android.db.me_crossover.helper.DBHelper;
import android.db.me_crossover.interfaces.OnListFragmentInteractionListener;
import android.db.me_crossover.model.Conference;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ConferenceListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConferenceListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ConferenceListFragment newInstance(int columnCount) {
        ConferenceListFragment fragment = new ConferenceListFragment();
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
        View view = inflater.inflate(R.layout.fragment_conference_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);



        DBHelper helper = new DBHelper(getActivity());
        ArrayList<Conference> conferences = helper.getConferences();

        recyclerView.setAdapter(new ConferenceListAdapter( conferences , mListener));

        FloatingActionButton btn = (FloatingActionButton
                ) view.findViewById(R.id.conferenceBtn);
        btn.setOnClickListener( onScheduleConference );

    }


    public View.OnClickListener onScheduleConference = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(),SchedularActivity.class);
            startActivity(i);
        }
    };



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


}
