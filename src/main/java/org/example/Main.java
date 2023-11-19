package org.example;


import org.example.avl.ArvoreAvl;

public class Main {
    public static void main(String[] args) {
        ArvoreAvl arvoreAvl = new ArvoreAvl();

        arvoreAvl.inserir(30);
        arvoreAvl.inserir(20);
        arvoreAvl.inserir(40);
        arvoreAvl.inserir(10);
        arvoreAvl.inserir(25);
        arvoreAvl.inserir(5);

        System.out.println("Arvore AVL inicial:");
        System.out.println(arvoreAvl.imprimirArvore());

        arvoreAvl.remover(20);

        System.out.println("\nArvore AVL após remover o nó com valor 20:");
        System.out.println(arvoreAvl.imprimirArvore());
    }
}