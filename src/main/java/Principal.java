
import org.example.boletostrategyfinal.LeituraRetorno;
import org.example.boletostrategyfinal.utils.LeituraRetornoFactory;
import org.example.boletostrategyfinal.utils.ProcessadorBoletos;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Principal {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String nomeArquivo = "bradesco-1.csv"; // ou "bradesco-1.csv"
        URI caminhoArquivo = Principal.class.getResource(nomeArquivo).toURI();
        System.out.println("Lendo arquivo: " + caminhoArquivo + "\n");

        LeituraRetorno leituraRetorno = LeituraRetornoFactory.criar(caminhoArquivo);
        ProcessadorBoletos processador = new ProcessadorBoletos(leituraRetorno);
        processador.processar(caminhoArquivo);
    }
}
