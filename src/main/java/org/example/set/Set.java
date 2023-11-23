package org.example.set;

public class Set<T> {
    private final T[] elementos;
    private int tamanho;

    public Set(int capacity) {
        this.elementos = (T[]) new Object[capacity];
        this.tamanho = 0;
    }

    public void adicionar(T elemento) {
        if (this.contem(elemento)) {
            return;
        }

        if (this.tamanho == this.elementos.length) {
            throw new IllegalStateException("Set is full");
        }

        this.elementos[this.tamanho] = elemento;
        this.tamanho++;
    }

    public boolean contem(T elemento) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i].equals(elemento)) {
                return true;
            }
        }

        return false;
    }

    public void remover(T elemento) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i].equals(elemento)) {
                this.elementos[i] = this.elementos[this.tamanho - 1];
                this.elementos[this.tamanho - 1] = null;
                this.tamanho--;
                return;
            }
        }
    }

    public String imprimir() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < this.tamanho; i++) {
            sb.append(this.elementos[i]);

            if (i < this.tamanho - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    public int getTamanho() {
        return this.tamanho;
    }
}
