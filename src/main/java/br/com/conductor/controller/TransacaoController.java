package br.com.conductor.controller;

import br.com.conductor.model.Transacao;
import br.com.conductor.repository.TransacaoDao;
import br.com.conductor.service.TransacaoService;
import br.com.conductor.utils.ManagerFile;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
* TransacaoController Controller das transações efetuadas
* trata de delegar ao Spring a partir de url's fornecidas pelo usuário o que
* deve-se fazer
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
*/
@Controller
@RequestMapping("/")
public class TransacaoController
{
  @Autowired
  TransacaoService transacaoService;
  @Autowired
  TransacaoDao transacaoDao;
  @Autowired
  ManagerFile managerFile;
  
  /**
   * Abre o index ao usuário
   * @param transacao Transacao model inicializador da view
   * @return ModelAndView.
   */
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView index(Transacao transacao)
  {
    ModelAndView view = new ModelAndView("index");
    return view;
  }
  
  /**
   * popula o Banco de dados com dados randomicos geradoos pela TransacaoService
   * alem de poder ser visualizado como um Json pelo seu header
   * @see TransacaoService
   * @return List de Transacao
   */
  @RequestMapping(method = RequestMethod.POST, value = "generateData")
  @Transactional
  public @ResponseBody List<Transacao> generateData()
  {
    List<Transacao> dados = transacaoService.generateData();
    transacaoDao.saveList(dados);
    return dados;
  }
  
  /**
   * Cria o arquivo .txt com as informações buscadas no banco de dados a partir do
   * parametro de data enviado pelo usuario
   * @see TransacaoDao
   * @see TransacaoService
   * @param transacao Model recebido pelo usuário
   * @param bindingResult BindingResult se os dados foram preenchidos corretamente(data)
   * @param redirectAttributes redireciona mensagens de resposta ao usuário
   * @return ModelAndView.
   */
  @RequestMapping(method = RequestMethod.POST, value = "generateFile")
  @Transactional
  public ModelAndView generateFile(Transacao transacao, 
          BindingResult bindingResult, RedirectAttributes redirectAttributes)
  {    
    if(bindingResult.hasErrors())
      return index(transacao);
    else
    {
      ArrayList<Transacao> dados = (ArrayList<Transacao>)transacaoDao.listByDate(transacao.getData());
      if(dados.isEmpty())
      {
        bindingResult.rejectValue("data", "data.sem.registro");
        if(bindingResult.hasErrors())
          return index(transacao);
      }
      redirectAttributes.addFlashAttribute("successFile", 
              "Arquivo gerado com sucesso.<br>Pronto para <a target=\"_blank\" href=\"file/"+managerFile.save(dados)+"\">download</a>!");
      return new ModelAndView("redirect:/");
    }
  }
  
}
