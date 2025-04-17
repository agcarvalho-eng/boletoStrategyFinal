package org.example.boletostrategyfinal.utils;

import org.example.boletostrategyfinal.LeituraRetorno;
import org.example.boletostrategyfinal.model.Boleto;

import java.net.URI;
import java.util.List;

public class ProcessadorBoletos {
    private LeituraRetorno leituraRetorno;

    public ProcessadorBoletos(LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public void processar(URI caminhoArquivo) {
        System.out.println("Boletos processados:");
        System.out.println("--------------------------------------------------------------------------------");
        List<Boleto> boletos = leituraRetorno.lerArquivo(caminhoArquivo);
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }

    public void setLeituraRetorno(LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }
}
