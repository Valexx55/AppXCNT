package edu.cas.appxcnt.profe.productos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.cas.appxcnt.profe.databinding.FilaProductoBinding


/**
 * Esta será la clase que almacene la lista de productos y a la que el recycler view
 * (la estructura de la lista) le irá pidiendo ítems/filas o contenido
 * de alguna forma que adapter es siempre el proveedor de datos del recycler
 */
class ProductosAdapter (var listaProductos: List<Producto>): RecyclerView.Adapter<ProdcutoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProdcutoViewHolder {
        val filaProducto = FilaProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdcutoViewHolder(filaProducto)
    }

    override fun onBindViewHolder(
        holder: ProdcutoViewHolder,
        position: Int
    ) {
        val producto = listaProductos[position]
        holder.rellenarFilaProducto(producto)
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}