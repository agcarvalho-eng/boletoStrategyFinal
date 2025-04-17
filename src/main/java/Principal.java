
import org.example.boletostrategyfinal.LeituraRetorno;
import org.example.boletostrategyfinal.utils.LeituraRetornoFactory;
import org.example.boletostrategyfinal.utils.ProcessadorBoletos;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Principal {

    // Método principal da aplicação Java, ponto de entrada da execução
    public static void main(String[] args) throws URISyntaxException, IOException {

        // Define o nome do arquivo CSV a ser lido (pode ser "banco-brasil-1.csv" ou "bradesco-1.csv")
        String nomeArquivo = "bradesco-1.csv";

        // Obtém o caminho do arquivo a partir da pasta resources e converte em um URI
        URI caminhoArquivo = Principal.class.getResource(nomeArquivo).toURI();

        // Exibe no console o caminho completo do arquivo que será lido
        System.out.println("Lendo arquivo: " + caminhoArquivo + "\n");

        // Utiliza a "fábrica" (LeituraRetornoFactory) para criar uma instância da estratégia de leitura apropriada com base no conteúdo do arquivo
        LeituraRetorno leituraRetorno = LeituraRetornoFactory.criar(caminhoArquivo);

        // Cria uma instância do processador de boletos, utilizando a estratégia selecionada
        ProcessadorBoletos processador = new ProcessadorBoletos(leituraRetorno);

        // Processa o arquivo, imprimindo os boletos lidos no console
        processador.processar(caminhoArquivo);
    }
}