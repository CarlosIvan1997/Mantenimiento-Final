package poclin.carlos.testmantenimiento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import poclin.carlos.testmantenimiento.models.Cliente;
import poclin.carlos.testmantenimiento.models.DaoSession;

public class LLenarActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_lastname)
    EditText etLastname;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.btn_save)
    Button btnSave;

    private DaoSession daoSession;
    private Cliente cliente;
    private boolean actualizar;

    //Se crea un starter con un parcelable
    public static void start(Context context, Cliente cliente) {
        Intent starter = new Intent(context, LLenarActivity.class);
        starter.putExtra("cliente", cliente);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llenar);
        ButterKnife.bind(this);

        cliente = getIntent().getParcelableExtra("cliente");

        ManApplication application = (ManApplication) getApplication();
        daoSession = application.getDaoSession();

        if(cliente==null){
            btnSave.setText("AGREGAR");
            actualizar = false;
        }else{
            etName.setText(cliente.getName());
            etLastname.setText(cliente.getLastname());
            etAge.setText(cliente.getAge().toString());
            btnSave.setText("ACTUALIZAR");
            actualizar = true;
        }
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        if(actualizar){
            actualizarCliente();
        }else{
            agregarCliente();
        }
    }

    private void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setName(etName.getText().toString());
        cliente.setLastname(etLastname.getText().toString());
        cliente.setAge(Integer.parseInt(etAge.getText().toString()));

        daoSession.getClienteDao().insert(cliente);

        Toast.makeText(this, "El cliente se ha agregado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void actualizarCliente(){
        cliente.setName(etName.getText().toString());
        cliente.setLastname(etLastname.getText().toString());
        cliente.setAge(Integer.parseInt(etAge.getText().toString()));

        daoSession.getClienteDao().update(cliente);

        Toast.makeText(this, "El cliente se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
