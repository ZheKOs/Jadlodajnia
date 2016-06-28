package pl.pcz.wimii.top.jadlodajnia;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import pl.pcz.wimii.top.jadlodajnia.db.Product;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductFragment . OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment implements AdapterCallback {

    public static final String ARG_PRODUCTS = "products";
    private static ArrayList<Product> products;

    RecyclerView rv;
    RecyclerView.LayoutManager lm;
    ProductAdapter productAdapter;

    MainCallback callActivity;

    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PRODUCTS, products);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callActivity = (MainCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LogoutUser");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            products = getArguments().getParcelableArrayList(ARG_PRODUCTS);
        }else{
            products = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (products != null){
            View v  = inflater.inflate(R.layout.fragment_product,container,false);
            rv = (RecyclerView) v.findViewById(R.id.rv_fragment_product);
            lm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(lm);
            productAdapter = new ProductAdapter(this,products);
            rv.setAdapter(productAdapter);
            return v;
        }
        return inflater.inflate(R.layout.layout_no_products, container, false);
    }

    @Override
    public void onMethodCallback() {
        callCreator();
    }

    void callCreator(){
        Toast.makeText(getActivity(),"callCreator()",Toast.LENGTH_SHORT).show();
        callActivity.onMainCallback(isEnyPicked());
    }

    boolean isEnyPicked(){
        return !(productAdapter.isAllFalse());

    }
}
