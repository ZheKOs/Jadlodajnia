package pl.pcz.wimii.top.jadlodajnia.gdgenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
import de.greenrobot.daogenerator.ToOne;

public class Main {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1,"pl.pcz.wimii.top.jadlodajnia.db");

        Entity dish = schema.addEntity("Dish");
        dish.addIdProperty();
        dish.addStringProperty("name").notNull();   //name
        dish.addIntProperty("kcal");                //kcal
        dish.addIntProperty("protein");             //Białko
        dish.addIntProperty("fat");                 //Tłuszcz
        dish.addIntProperty("carbohydrates");       //Węglowodany
        dish.addIntProperty("cellulose");           //Błonnik
        dish.addIntProperty("natrium");             //Sód
        dish.addIntProperty("potassium");           //Potas
        dish.addIntProperty("calcium");             //Wapń
        dish.addIntProperty("iron");                //Żelazo
        dish.addIntProperty("magnesium");           //Magnez
        dish.addIntProperty("vitA");                //Witamina A
        dish.addIntProperty("vitE");                //Witamina E
        dish.addIntProperty("vitC");                //Witamina C

        Entity product = schema.addEntity("Product");
        Property productId = product.addIdProperty().getProperty();
        product.addStringProperty("name");
        product.addIntProperty("kcal");             //kcal
        product.addIntProperty("protein");          //Białko
        product.addIntProperty("fat");              //Tłuszcz
        product.addIntProperty("carbohydrates");    //Węglowodany
        product.addIntProperty("cellulose");        //Błonnik
        product.addIntProperty("natrium");          //Sód
        product.addIntProperty("potassium");        //Potas
        product.addIntProperty("calcium");          //Wapń
        product.addIntProperty("iron");             //Żelazo
        product.addIntProperty("magnesium");        //Magnez
        product.addIntProperty("vitA");             //Witamina A
        product.addIntProperty("vitE");             //Witamina E
        product.addIntProperty("vitC");             //Witamina C

        Entity dishProduct = schema.addEntity("DishProduct");
        dishProduct.addIdProperty();
        Property dpPropertyDish = dishProduct.addLongProperty("dishId").getProperty();
        Property doPropertyProduct = dishProduct.addLongProperty("productId").getProperty();
        ToMany dishToDP = dish.addToMany(dishProduct, dpPropertyDish);
        dishToDP.setName("dishProducts");

        ToMany dpToProducts = dishProduct.addToMany(product,doPropertyProduct);
        dpToProducts.setName("products");

        DaoGenerator dg = new DaoGenerator();
        dg.generateAll(schema,"./app/src/main/java");

    }

}