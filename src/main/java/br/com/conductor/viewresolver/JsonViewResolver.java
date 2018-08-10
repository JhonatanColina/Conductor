package br.com.conductor.viewresolver;

import br.com.conductor.conf.AppWebConfiguration;
import java.util.Locale;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
* JsonViewResolver mantenedora das configurações Json aplicadas na AppConfiguration
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
* @see AppWebConfiguration
*/
public class JsonViewResolver implements ViewResolver
{

   /**
   * Metodo responsavel pela resolucao do Json.
   * Por padrão o Spring MVC trabalha utilizando o Jackson
   * como resolvedor de Json
   * @param string recebe o nome do parametro
   * @param locale seu locale
   * @return MappingJackson2JsonView 
   * @throws Exception
   */
  @Override
  public View resolveViewName(String string, Locale locale) throws Exception
  {
    MappingJackson2JsonView view = new MappingJackson2JsonView();
    view.setPrefixJson(true); // facil visualização
    return view;
  }
  
}
