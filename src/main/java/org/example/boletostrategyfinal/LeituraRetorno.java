package org.example.boletostrategyfinal;

import org.example.boletostrategyfinal.model.Boleto;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Interface que define um contrato para leitura de arquivos de retorno de boletos bancários
// Esta interface é usada no padrão Strategy para permitir múltiplas implementações de leitura
public interface LeituraRetorno {

    // Constante com o formato padrão para datas (BB), usada ao interpretar campos de data de vencimento
    DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Constante com o formato padrão para data e hora (Bradesco), usada para datas de pagamento com hora
    DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Método abstrato que deve ser implementado pelas estratégias de leitura
    // Recebe o caminho (URI) de um arquivo CSV de boletos e retorna uma lista de objetos Boleto
    List<Boleto> lerArquivo(URI caminhoArquivo);
}
