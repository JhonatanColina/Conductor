package br.com.conductor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

/**
* Transacao Model com os dados fornecidos pela documentação inicial
* da Conductor. O Hibernate trata de pegar as variaveis desta model e
* criar a base de dados caso a mesma ainda nao existe no servidor de
* banco de dados
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
*/
@Entity
public class Transacao implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idTransacao;
  private Long cartao;
  @Column(precision = 11, scale = 0)
  private BigDecimal valor;
  @DateTimeFormat
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ddMMyyyy")
  private Date data;

  public Transacao(){}

  public Transacao(Long cartao, BigDecimal valor, Date data)
  {
    this.cartao = cartao;
    this.valor = valor;
    this.data = data;
  }  

  public Long getIdTransacao()
  {
    return idTransacao;
  }

  public void setIdTransacao(Long idTransacao)
  {
    this.idTransacao = idTransacao;
  }

  public Long getCartao()
  {
    return cartao;
  }

  public void setCartao(Long cartao)
  {
    this.cartao = cartao;
  }

  public BigDecimal getValor()
  {
    return valor;
  }

  public void setValor(BigDecimal valor)
  {
    this.valor = valor;
  }

  public Date getData()
  {
    return data;
  }

  public void setData(Date data)
  {
    this.data = data;
  }

  @Override
  public String toString()
  {
    return "cartao=" + cartao + ", valor=" + valor + ", dataTransacao=" + new SimpleDateFormat("ddMMyyyy").format(data)+";";
  }
}
