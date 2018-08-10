package br.com.conductor.service;

import br.com.conductor.controller.TransacaoController;
import br.com.conductor.model.Transacao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
* TransacaoService Service de utilidade da Transação
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
* @see TransacaoController
*/
@Service
public class TransacaoService
{
   /**
   * Gera uma lista de objetos Transacao randomicos para preencher a base de dados
   * o metodo cria um numero randomico de transacoes a ser efetuada, cria uma lista de
   * transacoes vazia e comeca a iterar este numero de linhas a ser criada.
   * Neste momento o metodo cria um numero randomico de linhas com a mesma data a ser criada
   * para obter varias transacoes no mesmo dia.
   * @see TransacaoController
   * @return List de Transacao
   */
  public List<Transacao> generateData()
  {
    Random random = new Random();
    int numberOfRows = random.nextInt(200);
    
    List<Transacao> data = new ArrayList<>();
    for(int i=0;i<numberOfRows;i++)
    {
      int numberOfTransactionsPerDay = random.nextInt(20);
      Date dataTransacao = generateDate();
      for(int numberTransactions=numberOfTransactionsPerDay;numberTransactions>0;numberTransactions--)
      {
        Transacao transacao = new Transacao(generateCartao(),generateValor(),dataTransacao);
        data.add(transacao);
      }
    }
    return data;
  }
  
  /**
   * Gera um long para o numero do cartão de credito com 16 digitos
   * a partir da classe Random
   * @see Random
   * @return Long
   */
  private Long generateCartao()
  {
    Random random = new Random();
    return (long) (1000000000000000L+random.nextFloat()*9000000000000000L);
  }
  
  /**
   * Gera um BigDecimal para o valor da transacao com 11 digitos
   * a partir da classe Random
   * @see Random
   * @return BigDecimal
   */
  private BigDecimal generateValor()
  {
   Random random = new Random();
   Long value =  (long)(10000000000L+random.nextFloat()*90000000000L);
   return new BigDecimal(value);
  }
  
 /**
   * Gera um Date para a data da transacao
   * a partir do dia 01/01/2017 até o dia presente
   * @see LocalDate
   * @return Date
   */
  private Date generateDate()
  {
    LocalDate inicio = LocalDate.of(2017, 1,1);
    long start = inicio.toEpochDay();
    LocalDate fim = LocalDate.now();
    long end = fim.toEpochDay();
    long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
    try
    {
      return new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.ofEpochDay(randomEpochDay).toString());
    } catch (ParseException ex)
    {
      Logger.getLogger(TransacaoService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
