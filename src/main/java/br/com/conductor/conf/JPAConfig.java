package br.com.conductor.conf;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* JPAConfig possui todos os metodos de inicializacao da JPA para o Spring MVC
* sem o uso de arquivos xml o que facilita muito a sua manutenção
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
*/
@EnableTransactionManagement
public class JPAConfig
{
  
  /**
   * Cria o datasource utilizado na aplicação, recebendo a url,username e password
   * @return DriverManagerDataSource.
   */
  @Bean
  public DataSource dataSource()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/DesafioConductor?createDatabaseIfNotExist=true");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return dataSource;
  }
  
  /**
   * Cria a fabrica de Entity Manager do hibernate passando os pacotes de scan da model
   * recebe o datasource, as configurações padrões do JPA e o pacote de scan da model
   * @return LocalContainerEntityManagerFactoryBean.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean()
  {
    LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    managerFactoryBean.setDataSource(dataSource());
    managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    managerFactoryBean.setJpaProperties(myJpaConfig());
    managerFactoryBean.setPackagesToScan(new String[]{"br.com.conductor.model"});
    return managerFactoryBean;
  }
  
  /**
   * Configurações de utilização da JPA
   * tais quais os properties dialect, hbm
   * @return Properties.
   */
  private Properties myJpaConfig()
  {
    Properties properties = new Properties();
    properties.setProperty("hibernate.show_sql", "true");
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    return properties;
  }
  
  /**
   * Gerenciador de transações da JPA
   * @param emf EntityManagerFactory o qual o Spring trata de resolve-lo
   * @return JpaTransactionManager.
   */
  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
  {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(emf);
    return jpaTransactionManager;
  }
  
}
