package businessLayer;

import java.io.Serializable;

public class Client implements Serializable {
    private String nume,parola,rol;
    public Client(){}
    public Client(String nume, String parola,String rol) {
        this.nume = nume;
        this.parola = parola;
        this.rol=rol;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
