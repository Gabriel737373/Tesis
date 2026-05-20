data class Post(
    val id: String = "",
    var titulo: String = "",
    var contenido: String = "",
    var autorId: String = "",
    val creadoEn: LocalDateTime = LocalDateTime.now(),
    var actualizadoEn: LocalDateTime = LocalDateTime.now()
) {

    fun actualizarTitulo(nuevoTitulo: String) {
        if (nuevoTitulo.isNotBlank()) {
            titulo = nuevoTitulo.trim()
            marcarActualizado()
        }
    }

    fun actualizarContenido(nuevoContenido: String) {
        if (nuevoContenido.isNotBlank()) {
            contenido = nuevoContenido.trim()
            marcarActualizado()
        }
    }

    private fun marcarActualizado() {
        actualizadoEn = LocalDateTime.now()
    }
}