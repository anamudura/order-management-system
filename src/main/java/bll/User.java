package bll;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private Rol rol;
    private int contor;
    private int ID;

    public User(String username, String password, Rol rol) {
        this.password = password;
        this.username = username;
        this.rol = rol;
        setContor(1);
        if(rol == Rol.ADMINISTRATOR)
        {
            this.ID = contor;
        }
        else
        {
            contor++;
            this.ID = contor;
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getContor() {
        return contor;
    }

    public void setContor(int contor) {
        this.contor = contor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString()
    {
        return this.username+" "+this.rol;

    }
}
