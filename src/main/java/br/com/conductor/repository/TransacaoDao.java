package br.com.conductor.repository;

import br.com.conductor.controller.TransacaoController;
import br.com.conductor.model.Transacao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
* TransacaoDao Repository da Transação
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
* @see Transacao
*/
@Repository
public class TransacaoDao
{
  @PersistenceContext
  EntityManager em;
  
  /**
   * Persiste uma Transacao na base de dados
   * @param t Transacao Model
   * @see Transacao
   */
  public void save(Transacao t)
  {
    em.persist(t);
  }
  /**
   * Persiste uma lista de Transacao na base de dados
   * @param transacoes Lista de Transações
   * @see Transacao
   */
  public void saveList(List<Transacao> transacoes)
  {
    for(Transacao t : transacoes)
    {
      em.persist(t);
    }
  }
  /**
   * retorna uma lista de transações por data passada pelo usuário
   * @param data Data requerida do usuário
   * @see TransacaoController
   * @return List de Transacao
   */
  public List<Transacao> listByDate(Date data)
  {
    List<Transacao> transacoes = new ArrayList<>();
    try
    {
      transacoes = em.createQuery("select t from Transacao t where t.data=:data order by t.idTransacao asc",Transacao.class)
              .setParameter("data", data)
              .getResultList();
    }
    catch(NoResultException ex){ }
    
    return transacoes;
  }
}
