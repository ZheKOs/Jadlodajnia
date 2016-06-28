package pl.pcz.wimii.top.jadlodajnia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

import pl.pcz.wimii.top.jadlodajnia.db.DaoMaster;
import pl.pcz.wimii.top.jadlodajnia.db.DaoSession;
import pl.pcz.wimii.top.jadlodajnia.db.Dish;
import pl.pcz.wimii.top.jadlodajnia.db.DishDao;
import pl.pcz.wimii.top.jadlodajnia.db.Product;
import pl.pcz.wimii.top.jadlodajnia.db.ProductDao;

public class MainActivity extends AppCompatActivity implements MainCallback{

    public static final String PERF_FIRST = "is_first";
    public static final String isNull = "is null";

    private SQLiteDatabase db;

    DaoMaster daoMaster;
    DaoSession daoSession;

    FloatingActionButton fab;

    private boolean isCreatorShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstLaunch()){
            Intent firstLaunchIntent = new Intent(this,
                    GetStartedActivity.class);
            firstLaunchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(firstLaunchIntent);
            finish();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"jadlodajnia.db",null);
        db = helper.getReadableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        DishDao dishDao = daoSession.getDishDao();
        ArrayList<Dish> dishes = new ArrayList<>(
                dishDao.queryBuilder()
                .orderAsc(DishDao.Properties.Name)
                .list()
        );
        Bundle dishArgs = new Bundle();
        if (!dishes.isEmpty()){
            dishArgs.putParcelableArrayList(DishFragment.ARG_DISHES,dishes);
        }

        ProductDao productDao = daoSession.getProductDao();
        ArrayList<Product> products = new ArrayList<>(
                productDao.queryBuilder()
                    .orderAsc(ProductDao.Properties.Name)
                    .list()
        );
        Bundle productArgs = new Bundle();
        if (!products.isEmpty()){
            productArgs.putParcelableArrayList(ProductFragment.ARG_PRODUCTS, products);
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Dania", DishFragment.class, dishArgs)
                .add("Produkty", ProductFragment.class, productArgs)
                .create());

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        assert viewPager != null : "viewPager" + isNull;
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        assert viewPagerTab != null : "viewPagerTab" + isNull;
        viewPagerTab.setViewPager(viewPager);

        isCreatorShown = false;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null : "fab" + isNull;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem()==0){
                    viewPager.setCurrentItem(1);
                }else{
                    Snackbar.make(view, "Pick products", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isFirstLaunch() {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PERF_FIRST, 0);
        boolean isFirstLaunch = settings.getBoolean("isFirstLaunch", true);
        Log.i("TAG" + ".isFirstLaunch", "sharedPreferences ");
        return isFirstLaunch;
    }

    @Override
    public void onMainCallback(boolean isPicked) {
        if (isPicked){
            fab.hide();
        }else{
            fab.show();
            //TODO: Show AddingDishFragment

        }
    }
}
