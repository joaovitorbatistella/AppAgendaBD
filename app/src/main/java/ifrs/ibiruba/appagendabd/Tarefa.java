package ifrs.ibiruba.appagendabd;

import java.io.Serializable;

public class Tarefa implements Serializable {
    public long id;
    public String descricao;
    public String data;
    public String hora;

    public Tarefa(long id, String descricao, String data, String hora) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
    }

    public Tarefa(String descricao, String data, String hora) {
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return descricao + " | " + data + " | " + hora;
    }
}
