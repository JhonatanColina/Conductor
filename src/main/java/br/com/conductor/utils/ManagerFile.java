package br.com.conductor.utils;

import br.com.conductor.controller.TransacaoController;
import br.com.conductor.model.Transacao;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
* ManagerFile para configurar a manutenção dos arquivos
*
* @author  Jhonatan Colina
* @version 1.0
* @since   09/08/2018 
* @see TransacaoController
*/
@Component
public class ManagerFile
{
  private final String basePath = "/tmp/";
  /**
   * Salva o arquivo na pasta basePath
   * caso o array nao esteja nulo e vazio ele é persistido em um arquivo de texto
   * passado pelo seu basepath com um numero randomico para o seu nome
   * @param file lista de transacoes no dia pela data
   * @return String nome do arquivo gerado
   */
  public String save(ArrayList<Transacao> file)
  {
    String fileName = generateRandomNumber()+".txt";

    try
    {
      if (Objects.nonNull(file) && !file.isEmpty() && file.size() > 0)
      {
        Path path = Paths.get(basePath+fileName);
        List<String> strings = new ArrayList<>();
        for (Transacao t : file) {
            strings.add(t.toString());
        }
        Files.write(path, strings,Charset.defaultCharset());
      } else
      {
        fileName = null;
      }
    } catch (IllegalStateException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      fileName = null;
    } catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      fileName = null;
    }
    return fileName;
  }
  /**
   * Gera um numero randomico para o nome do arquivo .txt a ser criado
   * @return String nome do arquivo gerado
   */
  private static String generateRandomNumber()
  {
    int aNumber = 0; 
    aNumber = (int)((Math.random() * 9000000)+1000000); 
    return String.valueOf(aNumber);
  }
}
