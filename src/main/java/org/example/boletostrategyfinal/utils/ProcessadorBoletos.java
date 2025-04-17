package org.example.boletostrategyfinal.utils;

import org.example.boletostrategyfinal.LeituraRetorno;
import org.example.boletostrategyfinal.model.Boleto;

import java.net.URI;
import java.util.List;

// Processa arquivos de boletos utilizando uma estratégia de leitura definida (padrão Strategy)
public class ProcessadorBoletos {

    // Atributo que armazena a estratégia atual de leitura de retorno de boletos
    private LeituraRetorno leituraRetorno;

    // Construtor da classe que recebe uma estratégia (implementação de LeituraRetorno) como parâmetro
    public ProcessadorBoletos(LeituraRetorno leituraRetorno) {
        // Inicializa o atributo com a estratégia fornecida
        this.leituraRetorno = leituraRetorno;
    }

    // Método responsável por processar o arquivo informado, imprimindo os boletos lidos
    public void processar(URI caminhoArquivo) {
        // Exibe um título indicando o início do processamento
        System.out.println("Boletos processados:");

        // Imprime uma linha separadora para organização
        System.out.println("--------------------------------------------------------------------------------");

        // Utiliza a estratégia definida para ler o arquivo de boletos e obtém a lista de objetos Boleto
        List<Boleto> boletos = leituraRetorno.lerArquivo(caminhoArquivo);

        // Itera sobre cada boleto lido e imprime suas informações formatadas no console
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }

    // Permite alterar dinamicamente a estratégia de leitura de boletos, caso necessário
    public void setLeituraRetorno(LeituraRetorno leituraRetorno) {
        // Atualiza a estratégia de leitura com a nova fornecida
        this.leituraRetorno = leituraRetorno;
    }
}

