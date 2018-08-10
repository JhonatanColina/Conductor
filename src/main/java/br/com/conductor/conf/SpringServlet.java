package br.com.conductor.conf;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
* SpringServlet possui todos os metodos de inicializacao do Spring MVC sem
* o uso de arquivos xml o que facilita muito a sua manutenção
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
*/
public class SpringServlet extends AbstractAnnotationConfigDispatcherServletInitializer
{
  /**
   * Utilizado para o que implementar ao spring o que sera usado antes 
   * da config de classe subir (Utilizado para implementacao do SpringSecurity)
   * @return Class[]{ClassesImplementadas.class}.
   */
  @Override
  protected Class<?>[] getRootConfigClasses()
  {
    return new Class[]{AppWebConfiguration.class,JPAConfig.class};
  }
  
  /**
   * Metodo para definir todas as configurações que vai subir no Spring 
   * após as root config
   * @return Class[]{ClassesImplementadasApósRootConfig.class}.
   */
  @Override
  protected Class<?>[] getServletConfigClasses()
  {
    return new Class[]{};
  }

  /**
   * Metodo para definir o primeiro caminho que o Spring ira buscar
   * Normalmente é o /
   * @return String[]{caminho}.
   */
  @Override
  protected String[] getServletMappings()
  {
    return new String[]{"/"};
  }
  
  /**
   *  Metodo para setar o charset do Spring para toda a aplicação
   * @return Filter[]{CharacterEncodingFilter}.
   */
  @Override
  protected Filter[] getServletFilters() 
  {
     CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
     characterEncodingFilter.setEncoding("UTF-8");
     return new Filter[]{characterEncodingFilter};
  }

}
