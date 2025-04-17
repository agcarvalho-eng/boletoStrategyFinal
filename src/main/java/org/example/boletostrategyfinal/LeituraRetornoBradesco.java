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
    @Override
    public List<Boleto> lerArquivo(URI caminhoArquivo) {
        try {
            var linhas = Files.readAllLines(Paths.get(caminhoArquivo));
            var boletos = new ArrayList<Boleto>();

            for (String linha : linhas) {
                var campos = linha.split(",");

                var boleto = new Boleto();
                boleto.setId(Integer.parseInt(campos[0]));
                boleto.setCodBanco(campos[1]);
                boleto.setAgencia(campos[2]);
                boleto.setContaBancaria(campos[3]);
                boleto.setDataVencimento(LocalDate.parse(campos[4], FORMATO_DATA));
                boleto.setDataPagamento(LocalDateTime.parse(campos[5], FORMATO_DATA_HORA));
                boleto.setCpfCliente(campos[6]);
                boleto.setValor(Double.parseDouble(campos[7]));
                boleto.setMulta(Double.parseDouble(campos[8]));
                boleto.setJuros(Double.parseDouble(campos[9]));

                boletos.add(boleto);
            }

            return boletos;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
