package org.example;

import org.example.hash.TabelaHash;
import org.example.set.Set;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static TabelaHash<Integer, String> tabelaHash = new TabelaHash<>(10);
    static Set<Integer> set = new Set<>(10);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        int escolha;

        do {
            escolha = menuPrincipal();

            limparTerminal();
            switch (escolha) {
                case 1:
                    operarTabelaHash();
                    break;
                case 2:
                    operarSet();
                    break;
                case 3:
                    System.out.println("Saindo do programa");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (escolha != 3);
    }

    private static void operarTabelaHash() throws IOException, InterruptedException {
        int operacao;

        do {
            operacao = opcoes();

            switch (operacao) {
                case 1:
                    System.out.println("Digite a chave a ser inserida: ");
                    int chave = scanner.nextInt();
                    System.out.println("Digite o valor a ser inserido: ");
                    String valor = scanner.next();
                    tabelaHash.adicionar(chave, valor);
                    break;
                case 2:
                    System.out.println("Digite o valor a ser removido: ");
                    int valorRemover = scanner.nextInt();
                    tabelaHash.remover(valorRemover);
                    break;
                case 3:
                    System.out.println("Digite o valor a ser buscado: ");
                    int valorBuscar = scanner.nextInt();
                    System.out.println(tabelaHash.obter(valorBuscar));
                    break;
                case 4:
                    System.out.println(tabelaHash.imprimir());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
            limparTerminal();
        } while (operacao != 5);
    }

    private static void operarSet() {
        int operacao;

        do {
            operacao = opcoes();

            switch (operacao) {
                case 1:
                    System.out.println("Digite o valor a ser inserido: ");
                    int valor = scanner.nextInt();
                    set.adicionar(valor);
                    break;
                case 2:
                    System.out.println("Digite o valor a ser removido: ");
                    int valorRemover = scanner.nextInt();
                    set.remover(valorRemover);
                    break;
                case 3:
                    System.out.println("Digite o valor a ser buscado: ");
                    int valorBuscar = scanner.nextInt();
                    System.out.println(set.contem(valorBuscar));
                    break;
                case 4:
                    System.out.println(set.imprimir());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (operacao != 5);
    }

    private static int menuPrincipal() {
        System.out.println("O que você deseja operar?");
        System.out.println("1 - Tabela Hash");
        System.out.println("2 - Set");
        System.out.println("3 - Sair");
        System.out.print(": ");

        return scanner.nextInt();
    }

    private static int opcoes() {
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Inserir");
        System.out.println("2 - Remover");
        System.out.println("3 - Buscar");
        System.out.println("4 - Imprimir");
        System.out.println("5 - Voltar");
        System.out.print(": ");

        return scanner.nextInt();
    }

    private static void limparTerminal() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}
