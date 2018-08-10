# Desafio Conductor

Olá Senhores, este Readme irá demonstrar como realizar o deploy da aplicação de desafio.

O desafio foi passado a partir deste link: https://github.com/devconductor/desafio-arquivo no dia 10/08/2018

## DataSouce
A unica configuração necessária para subir a aplicação Spring MVC em um servidor Web é alterar a configuração básica do DataSouce encontrado em **br.com.conductor.conf**: 

- URL: Porta: Se a URL for no localhost nenhuma configuração é necessária ja que esta no parametro da forma correta: jdbc:mysql://localhost:3306/DesafioConductor?createDatabaseIfNotExist=true 
- Username: Usuário da base de dados
- Password: Senha da base de dados

## Amazon
Esta versão da aplicação esta atualmente no ar em uma instância EC2 da Amazon que pode ser acessada pelo link: http://34.232.234.138/DESAFIOCONDUCTOR/
