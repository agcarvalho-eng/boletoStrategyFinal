package org.example.boletostrategyfinal;

import org.example.boletostrategyfinal.model.Boleto;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBancoBrasil implements LeituraRetorno {

    // Método lerArquivo, que lê um arquivo CSV e retorna uma lista de objetos Boleto
    @Override
    public List<Boleto> lerArquivo(URI caminhoArquivo) {
        try {
            // Lê todas as linhas do arquivo especificado pelo caminho (URI)
            var linhas = Files.readAllLines(Paths.get(caminhoArquivo));

            // Cria uma lista para armazenar os boletos lidos do arquivo
            var boletos = new ArrayList<Boleto>();

            // Itera sobre cada linha lida do arquivo
            for (String linha : linhas) {
                // Separa a linha em campos, com base na vírgula (CSV)
                var campos = linha.split(",");

                // Cria um novo objeto Boleto
                var boleto = new Boleto();

                // Preenche os dados do boleto com os valores dos campos do arquivo
                boleto.setId(Integer.parseInt(campos[0]));
                boleto.setCodBanco(campos[1]);
                // Converte a string da data de vencimento para LocalDate usando o formato de data definido na interface
                boleto.setDataVencimento(LocalDate.parse(campos[2], FORMATO_DATA));
                // Converte a string da data de pagamento para LocalDate e define a hora como 00:00
                boleto.setDataPagamento(LocalDate.parse(campos[3], FORMATO_DATA).atTime(0, 0));
                boleto.setCpfCliente(campos[4]);
                boleto.setValor(Double.parseDouble(campos[5]));
                boleto.setMulta(Double.parseDouble(campos[6]));
                boleto.setJuros(Double.parseDouble(campos[7]));

                // Adiciona o boleto à lista de boletos
                boletos.add(boleto);
            }

            // Retorna a lista de boletos lidos
            return boletos;

            // Se houver um erro ao tentar ler o arquivo, lança uma exceção não verificada
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

