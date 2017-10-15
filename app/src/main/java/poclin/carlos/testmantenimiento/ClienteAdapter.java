package poclin.carlos.testmantenimiento;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import poclin.carlos.testmantenimiento.models.Cliente;

/**
 * Created by CARLOSIVÁN on 12/10/2017.
 */

//extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>
public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>{

    //Se crea la lista
    private List<Cliente> clientes;

    //2do Pase: Crear una variable global de la interface
    private OnClienteItemClickListener onClienteItemClick;


    //Recibir el listener por el constructor
    public ClienteAdapter(OnClienteItemClickListener onClienteItemClick){
        this.onClienteItemClick = onClienteItemClick;
    }

    public void addList(List<Cliente> clientes){
        this.clientes=clientes;
        notifyDataSetChanged();
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Se pone el modelo
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo,parent,false);
        return new ClienteViewHolder(view);
    }

    //Se une y pone la funcion
    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        final Cliente cliente = clientes.get(position);

        holder.tvName.setText(cliente.getName());
        holder.tvLastName.setText(cliente.getLastname());
        holder.tvAge.setText(cliente.getAge().toString());

        //Paso 5: integrar el método de la interface con el onClickListener del View
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClienteItemClick!=null){
                    onClienteItemClick.onItemClick(cliente);
                }
            }
        });

        //Paso 5: integrar el método de la interface con el onClickListener del View
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClienteItemClick!=null){
                    onClienteItemClick.onEditarClienteClick(cliente);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(clientes==null){
            return 0;
        }else{
            return clientes.size();
        }
    }

    //Se crea esta clase
    class ClienteViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvLastName, tvAge, tvSexo;
        Button btnEdit;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvLastName=itemView.findViewById(R.id.tv_lastname);
            btnEdit=itemView.findViewById(R.id.btn_edit);
            tvAge=itemView.findViewById(R.id.tv_edad);
            tvSexo=itemView.findViewById(R.id.tv_sexo);
        }
    }

    //1er Paso: Crear la interface
    public interface OnClienteItemClickListener{
        void onItemClick(Cliente cliente);// metodo para eliminar un item
        void onEditarClienteClick(Cliente cliente);//metodo para el click del boton editar
    }

}
