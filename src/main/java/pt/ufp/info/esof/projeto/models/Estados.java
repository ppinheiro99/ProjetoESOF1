package pt.ufp.info.esof.projeto.models;

public enum Estados {
    Concluido(1),
    EmAndamento(2),
    Atrasado(3),
    NaoComecado(4),
    ConcluidoComAtraso(5);

    public final int valorEstado;
    Estados(int numEstado) {
        this.valorEstado = numEstado;
    }
}
