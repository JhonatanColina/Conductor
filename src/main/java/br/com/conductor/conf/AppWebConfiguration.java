package br.com.conductor.conf;

import br.com.conductor.controller.TransacaoController;
import br.com.conductor.repository.TransacaoDao;
import br.com.conductor.service.TransacaoService;
import br.com.conductor.utils.ManagerFile;
import br.com.conductor.viewresolver.JsonViewResolver;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
* AppWebConfiguration possui todos os metodos de configuração da aplicação web
* utilizando da anotação @ComponentScan os pacotes que o Spring MVC ira cuidar é
* passado a partir de uma classe inserida em sua lista
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
*/
@EnableWebMvc
@ComponentScan(basePackageClasses =
{
  TransacaoController.class, TransacaoService.class, 
  TransacaoDao.class, ManagerFile.class
})
public class AppWebConfiguration extends WebMvcConfigurerAdapter
{  
  /**
   * Permite que resources (CSS,JS) sejam colocados antes da web-inf
   * e faz com que o Spring MVC libere este caminho para a utilização das views
   * @param  configurer DefaultServletHandlerConfigurer, apenas necessario enable
   * para ativa-lo
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
          configurer.enable();
  }

  /**
   * Define os primeiros resources de resolução do Spring MVC como sufixo
   * e prefixo dos caminhos utilizados nas views, fazendo com que a url não tenha a
   * necessidade de ser passada inteira nos controllers
   * @return InternalResourceViewResolver
   */
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver()
  {
    InternalResourceViewResolver resolver
            = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }
  
  /**
   * Interface criada para as mensagens de validação
   * passando a url de local do properties de mensagens
   * @return ReloadableResourceBundleMessageSource
   */
  @Bean
  public MessageSource messageSource()
  {
    ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
    reloadableResourceBundleMessageSource.setBasename("WEB-INF/resources/messages");
    reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
    reloadableResourceBundleMessageSource.setCacheSeconds(1);
    return reloadableResourceBundleMessageSource;

  }
  
   /**
   * Interface para converter as datas para o padrao recebido pelo Spring MVC
   * é passado qual formato o Spring ira receber das views
   * @return FormattingConversionService
   */
  @Bean
  public FormattingConversionService mvcConversionService()
  {
    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
    DateFormatter dateFormatter = new DateFormatter("ddMMyyyy");
    DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
    formatterRegistrar.setFormatter(dateFormatter);
    formatterRegistrar.registerFormatters(conversionService);
    return conversionService;
  }
  
   /**
   * Metodo interface para receber varios tipos de header (html/json/xml...)
   * @param contentNegotiationManager ContentNegotiationManager com este parametro
   * é iniciado todos os resolvers de Json/XML
   * @return ContentNegotiatingViewResolver
   */
  @Bean
  public ViewResolver viewResolver(ContentNegotiationManager contentNegotiationManager)
  {
    List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
    resolvers.add(internalResourceViewResolver());
    resolvers.add(new JsonViewResolver());

    ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
    contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager);
    contentNegotiatingViewResolver.setViewResolvers(resolvers);
    return contentNegotiatingViewResolver;
  }
  
  /**
   * Libera resources fora do ambiente de produção como pastas fora da aplicação
   * com a possibilidade de cirar uma url externa a partir de um caminho privado do
   * Spring MVC
   * @param registry ResourceHandlerRegistry 
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry)
  {
    final String basePath = "file:/tmp/";
    registry.addResourceHandler("file/**").addResourceLocations(basePath);
    super.addResourceHandlers(registry);
  }
}
