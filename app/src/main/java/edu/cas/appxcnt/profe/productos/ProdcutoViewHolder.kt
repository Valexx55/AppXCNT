package edu.cas.appxcnt.profe.productos

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.cas.appxcnt.profe.databinding.FilaProductoBinding

/**
 * Esto es como la fila / el contenedor de la fila cuya información
 * se recicla o se actualiza con la información del producto que toque
 */
class ProdcutoViewHolder(val filaProducto: FilaProductoBinding): RecyclerView.ViewHolder(filaProducto.root) {

    val ruta_foto_leche = "https://finditapp.es/_next/image?url=https%3A%2F%2Fdx7csy7aghu7b.cloudfront.net%2Fprods%2F7550424.webp&w=640&q=75"

    fun rellenarFilaProducto (producto: Producto)
    {
        this.filaProducto.idproducto.text = producto.id.toString()
        this.filaProducto.nombreproducto.text = producto.name
        this.filaProducto.precioproducto.text = producto.price
        Glide.with(itemView.context) //gracias A Glide, que hace la conexión HTTP por mí, me ahorro trabajo
            .load(ruta_foto_leche.toUri())
            .into(this.filaProducto.imagenproducto)
    }

}