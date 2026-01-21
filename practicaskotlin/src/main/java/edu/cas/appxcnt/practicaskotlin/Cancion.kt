package edu.cas.appxcnt.practicaskotlin

/*Imagina que necesitas crear una app de reproducción de música.

Crea una clase que pueda representar la estructura de una canción. La clase Song debe incluir estos elementos de código:

Propiedades para el título, el artista, el año de publicación y el recuento de reproducciones
Propiedad que indica si la canción es popular (si el recuento de reproducciones es inferior a 1,000, considera que es poco popular)
Un método para imprimir la descripción de una canción en este formato:
"[Título], interpretada por [artista], se lanzó en [año de lanzamiento]".*/


data class Cancion(val titulo:String, val artista:String? = null, val anio:Int, val reproducciones:Int ) {
    val esPopular: Boolean //la canción es popular si tiene más de mil reproduccionesç
        get() = reproducciones >= 1000

    // en el caso de que no se conozca el artista, debemos imprimir Artista desconocido
    override fun toString(): String {
        val nombreArtista = artista ?: "Artista desconocido"
        val cancionString = "$titulo, compuesta por $nombreArtista, se lanzó en $anio"
        return cancionString
    }
}
fun main() {

    val cancion0 = Cancion(titulo="Hola mundo", anio= 2015, reproducciones =  300 )
    val cancion1 = Cancion("Vaina loca", "Fuego", 2008, 5000)
    val cancion2 = Cancion("5ª Sinfonía", "Bethoven", 1700, 10000)
    val cancion3 = Cancion("Show must go on", "Queen", 1989, 300)
    val cancion4 = Cancion("Cuando zarpa el amor", "Camela", 2001, 650)
    val cancion5 = Cancion("Slither", "Velvet Revolver", 2008, 70)
    println(cancion1.esPopular)
    println(cancion1.toString())//cancion1.toString()
    val listaCanciones = listOf(cancion1, cancion2, cancion3, cancion4, cancion5)
    //TODO:

    //1 listado imprimiendo el detalle de todas las canciones
    //1 listado imprimiendo el detalle de todas las canciones
    println("--- Detalle de todas las canciones ---")
    listaCanciones.forEach { cancion: Cancion -> println(cancion) }

    listaCanciones.forEach { println(it) } //it es una variable predefinida, que representa a cada elemento de la colección que recorro
    println(listaCanciones.joinToString(", "))

    //2 el título de las canciones populares
    val cancionesPopulares : List<String> = listaCanciones.filter { song -> song.esPopular }.map { song -> song.titulo }
    println(cancionesPopulares.joinToString(", "))

    val populares = listaCanciones.filter { it.esPopular }.map { it.titulo }
    println(populares)

    val popularescanciones = listaCanciones.filter { it.esPopular }
    popularescanciones.forEach { it}
    //3 la canción más antigua
    val cancionAntigua : Cancion = listaCanciones.minBy { song -> song.anio }
    println(cancionAntigua)

    val masAntigua = listaCanciones.minByOrNull { it.anio }
    println("\nCanción más antigua: ${masAntigua?.titulo} (${masAntigua?.anio})")

    val cancionmasAntigua = listaCanciones.minByOrNull { it.anio }
    cancionmasAntigua?.let { println(it.toString()) } //sólo si es distinto de null, se ejecuta el bloque let
    //4 la canción más popular

    val cancionMasPopular : Cancion = listaCanciones.maxBy { song -> song.reproducciones }
    println(cancionMasPopular)

    val masPopular = listaCanciones.maxByOrNull { it.reproducciones }
    println("Canción más popular: ${masPopular?.titulo} con ${masPopular?.reproducciones} reproducciones")

    val cmasPopular = listaCanciones.maxByOrNull { it.reproducciones }
    cmasPopular?.let {  println(it)}
}
