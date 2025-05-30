package concurso;

public class Participante {

    //ATRIBUTO DE CLASE
    private static int contador = 1;

    //ATRIBUTOS DE INSTANCIA
    private final int id;
    private String nombre;
    private String email;
    private int puntosAcumulados;


    public Participante(String nombre, String email) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("Nombre no puede ser vacío");
        }
        if (email == null || !email.contains("@")) {
            throw new RuntimeException("Email inválido");
        }
        this.id = this.contador++;
        this.nombre = nombre;
        this.email = email;
        this.puntosAcumulados = 0;
    }

    public void agregarPuntos(int puntos) {
        if (puntos > 0) {
            this.puntosAcumulados += puntos;
        }
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public int getPuntosAcumulados() { return puntosAcumulados; }

}