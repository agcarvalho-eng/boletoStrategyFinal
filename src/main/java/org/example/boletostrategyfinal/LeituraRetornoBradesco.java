package org.example.boletostrategyfinal;

import org.example.boletostrategyfinal.model.Boleto;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBradesco implements LeituraRetorno {

    // Implementação do método lerArquivo, que lê um arquivo CSV e retorna uma lista de objetos Boleto
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
                boleto.setAgencia(campos[2]);
                boleto.setContaBancaria(campos[3]);
                // Converte a string da data de vencimento para LocalDate usando o formato de data definido na interface
                boleto.setDataVencimento(LocalDate.parse(campos[4], FORMATO_DATA));
                // Converte a string da data de pagamento para LocalDateTime usando o formato de data e hora
                boleto.setDataPagamento(LocalDateTime.parse(campos[5], FORMATO_DATA_HORA));
                boleto.setCpfCliente(campos[6]); // CPF do cliente
                boleto.setValor(Double.parseDouble(campos[7]));
                boleto.setMulta(Double.parseDouble(campos[8]));
                boleto.setJuros(Double.parseDouble(campos[9]));

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
