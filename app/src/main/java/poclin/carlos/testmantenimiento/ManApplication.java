package poclin.carlos.testmantenimiento;

import android.app.Application;
import org.greenrobot.greendao.database.Database;
import poclin.carlos.testmantenimiento.models.DaoMaster;
import poclin.carlos.testmantenimiento.models.DaoSession;

/**
 * Created by CARLOSIVÁN on 12/10/2017.
 */

//Se crea una application, que extends Application
public class ManApplication extends Application {

    //Se crea un DaoSession
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this,"mydb");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}

//Se añade el android:name=".ManApplication" en el AndroidManifest