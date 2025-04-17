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
                boleto.setDataVencimento(LocalDate.parse(campos[2], FORMATO_DATA));
                boleto.setDataPagamento(LocalDate.parse(campos[3], FORMATO_DATA).atTime(0, 0));
                boleto.setCpfCliente(campos[4]);
                boleto.setValor(Double.parseDouble(campos[5]));
                boleto.setMulta(Double.parseDouble(campos[6]));
                boleto.setJuros(Double.parseDouble(campos[7]));

                boletos.add(boleto);
            }

            return boletos;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

