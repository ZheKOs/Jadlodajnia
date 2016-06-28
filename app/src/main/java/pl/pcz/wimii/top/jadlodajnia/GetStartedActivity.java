package pl.pcz.wimii.top.jadlodajnia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.pcz.wimii.top.jadlodajnia.db.DaoMaster;
import pl.pcz.wimii.top.jadlodajnia.db.DaoSession;
import pl.pcz.wimii.top.jadlodajnia.db.Dish;
import pl.pcz.wimii.top.jadlodajnia.db.DishDao;
import pl.pcz.wimii.top.jadlodajnia.db.Product;
import pl.pcz.wimii.top.jadlodajnia.db.ProductDao;

public class GetStartedActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        context = this;

        SharedPreferences settings = getSharedPreferences(MainActivity.PERF_FIRST, 0);
        settings.edit().putBoolean("isFirstLaunch", false).apply();

        prepareDB();

        Button btn = (Button) findViewById(R.id.button);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });

    }

    void backToMainActivity(){
        Intent firstLaunchIntent = new Intent(context,
                MainActivity.class);
        firstLaunchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(firstLaunchIntent);
        finish();
    }

    void prepareDB(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"jadlodajnia.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        DishDao dishDao = daoSession.getDishDao();
        ProductDao productDao = daoSession.getProductDao();


        Product p1 = new Product();
        p1.setName("Test1");
        p1.setKcal(100);
        p1.setProtein(2);
        p1.setFat(2);
        p1.setCarbohydrates(1);
        p1.setCellulose(3);
        p1.setNatrium(1);
        p1.setPotassium(2);
        p1.setCalcium(1);
        p1.setIron(2);
        p1.setMagnesium(1);
        p1.setVitA(1);
        p1.setVitE(1);
        p1.setVitC(1);
        productDao.insert(p1);

        Product p2 = new Product();
        p2.setName("Test2");
        p2.setKcal(100);
        p2.setProtein(2);
        p2.setFat(2);
        p2.setCarbohydrates(1);
        p2.setCellulose(3);
        p2.setNatrium(1);
        p2.setPotassium(2);
        p2.setCalcium(1);
        p2.setIron(2);
        p2.setMagnesium(1);
        p2.setVitA(1);
        p2.setVitE(1);
        p2.setVitC(1);
        productDao.insert(p2);

        Product p3 = new Product();
        p3.setName("Test3");
        p3.setKcal(100);
        p3.setProtein(2);
        p3.setFat(2);
        p3.setCarbohydrates(1);
        p3.setCellulose(3);
        p3.setNatrium(1);
        p3.setPotassium(2);
        p3.setCalcium(1);
        p3.setIron(2);
        p3.setMagnesium(1);
        p3.setVitA(1);
        p3.setVitE(1);
        p3.setVitC(1);
        productDao.insert(p3);

        db.close();
    }

}
