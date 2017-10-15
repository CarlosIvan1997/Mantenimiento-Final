package poclin.carlos.testmantenimiento;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import poclin.carlos.testmantenimiento.models.Cliente;
import poclin.carlos.testmantenimiento.models.DaoSession;

//Paso 6: implements ClienteAdapter.OnClienteItemClickListener
public class MainActivity extends AppCompatActivity implements ClienteAdapter.OnClienteItemClickListener{

    private List<Cliente> clientes;
    private ClienteAdapter clienteAdapter;
    private DaoSession daoSession;

    @BindView(R.id.rv_clientes) RecyclerView rvClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //base de datos
        ManApplication application = (ManApplication)getApplication();
        daoSession = application.getDaoSession();

        /* pasar el  mainactivity que ahora tiene el comportamiento del listener.*/
        clienteAdapter = new ClienteAdapter(this);

        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        rvClientes.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvClientes.setAdapter(clienteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clientes = daoSession.getClienteDao().loadAll();
        clienteAdapter.addList(clientes);

    }


    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        LLenarActivity.start(this, null);
    }

    //Paso 8: Se pone la funcion del metodo que sale de la interface
    @Override
    public void onItemClick(Cliente cliente) {
        daoSession.delete(cliente);
        clientes = daoSession.getClienteDao().loadAll();
        clienteAdapter.addList(clientes);
        Toast.makeText(this, "Cliente Eliminado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditarClienteClick(Cliente cliente) {
        LLenarActivity.start(this,cliente);
    }
}
