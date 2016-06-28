package pl.pcz.wimii.top.jadlodajnia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import pl.pcz.wimii.top.jadlodajnia.db.Product;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private AdapterCallback mAdapterCallback;

    private ArrayList<Product> products;

    boolean[] checks;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvName, tvKcal, tvCost;
        public CheckBox cb;
        public ImageButton ib;

        public ViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tv_ip_name);
            tvKcal = (TextView) v.findViewById(R.id.tv_ip_kcal);
            tvCost = (TextView) v.findViewById(R.id.tv_ip_cost);

            cb = (CheckBox) v.findViewById(R.id.cb_ip);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            ib = (ImageButton) v.findViewById(R.id.ib_ip);

        }
    }

    public ProductAdapter(AdapterCallback callback, ArrayList<Product> products) {
        this.mAdapterCallback = callback;
        this.products = products;
        checks = new boolean[products.size()];
    }


    // Create new views (invoked by the layout manager)


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvName.setText(products.get(position).getName());
        holder.tvKcal.setText(products.get(position).getKcal().toString() + " kcal");
        holder.tvCost.setText("00.00" + " zł");

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checks[position] = isChecked;
                mAdapterCallback.onMethodCallback();
            }
        });
    }

    private void clearChecks()
    {
        Arrays.fill(checks, false);
        this.notifyDataSetChanged();
    }
    public boolean isAllFalse()
    {
        for (boolean checked : checks) {
            if (checked)
                return false;
        }
        return true;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return products.size();
    }
}