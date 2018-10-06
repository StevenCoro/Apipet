package apipet.web.apipet.io;

public class MascotasUsuario {
    private String NombreMascota, TipoMascota, RazaMascota, GeneroMascota;

    public MascotasUsuario() {

    }

    public String getNombreMascota() {
        return NombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        NombreMascota = nombreMascota;
    }

    public String getTipoMascota() {
        return TipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        TipoMascota = tipoMascota;
    }

    public String getRazaMascota() {
        return RazaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        RazaMascota = razaMascota;
    }

    public String getGeneroMascota() {
        return GeneroMascota;
    }

    public void setGeneroMascota(String generoMascota) {
        GeneroMascota = generoMascota;
    }
}
