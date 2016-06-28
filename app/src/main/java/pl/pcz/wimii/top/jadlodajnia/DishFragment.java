package pl.pcz.wimii.top.jadlodajnia;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pl.pcz.wimii.top.jadlodajnia.db.Dish;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DishFragment . OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DishFragment extends Fragment {

    public static final String ARG_DISHES = "dishes";
    private static ArrayList<Dish> dishes;

    private Context context;

    public DishFragment() {
        // Required empty public constructor
    }


    public static DishFragment newInstance() {
        DishFragment fragment = new DishFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_DISHES, dishes);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (getArguments() != null) {
            dishes = getArguments().getParcelableArrayList(ARG_DISHES);
        }else{
            dishes = null;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (dishes == null)
            return inflater.inflate(R.layout.layout_no_dishes,container,false);
        return inflater.inflate(R.layout.fragment_dish, container, false);
    }

}
