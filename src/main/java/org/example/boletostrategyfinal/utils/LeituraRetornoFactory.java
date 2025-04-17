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
    public static LeituraRetorno criar(URI caminhoArquivo) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(caminhoArquivo))) {
            String linha = reader.readLine();

            if (linha == null || linha.isBlank()) {
                throw new IllegalArgumentException("Arquivo está vazio.");
            }

            String[] campos = linha.split(",");
            String codigoBanco = campos[1];

            return switch (codigoBanco) {
                case "001" -> new LeituraRetornoBancoBrasil();
                case "002" -> new LeituraRetornoBradesco();
                default -> throw new IllegalArgumentException("Código de banco não suportado: " + codigoBanco);
            };
        }
    }
}
