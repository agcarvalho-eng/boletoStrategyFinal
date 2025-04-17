package org.example.boletostrategyfinal.utils;

import org.example.boletostrategyfinal.LeituraRetorno;
import org.example.boletostrategyfinal.LeituraRetornoBancoBrasil;
import org.example.boletostrategyfinal.LeituraRetornoBradesco;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class LeituraRetornoFactory {

    // Método que recebe o caminho do arquivo CSV (BB ou Bradesco) e retorna a estratégia para leitura adequada (implementação de LeituraRetorno)
    public static LeituraRetorno criar(URI caminhoArquivo) throws IOException {

        // Abre o arquivo para leitura usando um BufferedReader
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(caminhoArquivo))) {

            // Lê a primeira linha do arquivo, que será usada para identificar o código do banco
            String linha = reader.readLine();

            // Se a linha estiver nula ou vazia, lança uma exceção informando que o arquivo está vazio
            if (linha == null || linha.isBlank()) {
                throw new IllegalArgumentException("Arquivo está vazio.");
            }

            // Divide a linha lida em campos (vírgula é o separador)
            String[] campos = linha.split(",");

            // Pega o segundo campo (índice 1), que representa o código do banco
            String codigoBanco = campos[1];

            // Usa o switch para retornar a estratégia correta de leitura (baseado no código do banco)
            return switch (codigoBanco) {
                // Se for 001 (Banco do Brasil), retorna a instância da estratégia BB
                case "001" -> new LeituraRetornoBancoBrasil();

                // Se for 002 (Bradesco), retorna a instância da estratégia Bradesco
                case "002" -> new LeituraRetornoBradesco();

                // Se o código não for suportado, lança uma exceção informando que o banco não é reconhecido
                default -> throw new IllegalArgumentException("Código de banco não suportado: " + codigoBanco);
            };
        }
    }
}
