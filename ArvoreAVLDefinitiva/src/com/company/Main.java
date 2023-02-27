package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Integrantes: Gabriel Sposito Conciani e Pedro Henrique Faria Amadeu.
public class Main {

    public static void main(String[] args) throws IOException{

        Arvore arvore = new Arvore();
        //Elementos adicionados a uma arvore.
        arvore.add(3, arvore.getRaizArvore());
        arvore.add(5, arvore.getRaizArvore());
        arvore.add(6, arvore.getRaizArvore());
        arvore.add(8, arvore.getRaizArvore());
        arvore.add(9, arvore.getRaizArvore());

        //Elemento retirado da arvore
        arvore.Retira(3, arvore.getRaizArvore());

        //Em Ordem, Pre-Ordem, Pos-Ordem.
        System.out.println("Em Ordem:");
        arvore.EmOrdem(arvore.getRaizArvore());
        System.out.println(" ");
        System.out.println("Pos-Ordem:");
        arvore.PosOrdem(arvore.getRaizArvore());
        System.out.println(" ");
        System.out.println("Pre-Ordem:");
        arvore.PreOrdem(arvore.getRaizArvore());
        System.out.println(" ");
        //Altura da Arvore.
        System.out.println("Altura:");
        System.out.println(arvore.AlturaArvore(arvore.getRaizArvore()));
        System.out.println(" ");
        //Contador de Palavras.
        System.out.println("Numero de palavras:");
        contarPalavraArquivos("src/com/company/txt/", "anarquia");

    }

    public static long contarPalavraArquivos(String diretorio, String palavra) throws IOException {
        // Path - diretorio informado
        Path path = Paths.get(diretorio);
        // Arquivos disponiveis no path
        List<Path> listaArquivos = Files.walk(path).filter(Files::isRegularFile).collect(Collectors.toList());

        List<String> palavras;
        long quantidade = 0;
        long quantidadeArquivo = 0;

        // For usado para percorrer a lista dos arquivos encontrados no diretorio
        for (Path arquivo : listaArquivos) {
            // Le cada arquivo e transforma em uma lista de strings
            palavras = Files.lines(Paths.get(arquivo.toString()))
                    .map(String::toUpperCase) // -> Transforma para maiusculo
                    .map(line -> line.split("\\s+")).flatMap(Arrays::stream)

                    .collect(Collectors.toList());

            // Usa o filter do stream para pesquisar a palavra e com o count adiciona
            quantidadeArquivo = palavras.stream().filter(p -> p.startsWith(palavra.toUpperCase())).count();

            // Quantidade total
            quantidade = quantidade + quantidadeArquivo;

            System.out.println("Arquivo " + arquivo.toString() + ": " + quantidadeArquivo);
        }
        System.out.println("Total: " + quantidade);
        return quantidade;
    }



}
