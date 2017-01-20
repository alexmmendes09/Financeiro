# README #

SISTEMA DE CONTROLE DE FINANCEIRO

 Projeto desenvolvido para suprir a necessidade de controle financeiro doméstico.
 As etapas a seguir listam as tecnologias utilizadas e o andamento do desenvolvimento da implementação.
 #Java; JSF:# também conhecido como JSF, é uma tecnologia para desenvolvimento web que utiliza um modelo de interfaces gráficas baseado em eventos; JPA (Java Persistence API): é uma API padrão do Java para persistência de dados, que usa um conceito de mapeamento objeto-relacional; EJB (Enterprise Java Beans): são componentes que executam em servidores de aplicação e possuem como principais objetivos, fornecer facilidade e produtividade no desenvolvimento de componentes distribuídos, transacionados, seguros e portáveis; CDI - Contexts and Dependency Injection:é a especificação da Java EE que trabalha com injeção de dependências. Podemos usar CDI para instanciar e injetar objetos da aplicação; O Hibernate Core é a base para o funcionamento da persistência, com APIs nativas e metadados de mapeamentos em arquivos XML. Possui uma linguagem de consultas chamada HQL (parecido com SQL), um conjunto de interfaces para consultas usando critérios (Criteria API), etc. MySQL: É um sistema de gerenciamento de banco de dados (SGBD), que utiliza a linguagem SQL (Linguagem de Consulta Estruturada, do inglês Structured Query Language) como interface.
 #HTML, CSS e JavaScript; PrimeFaces:# PrimeFaces é uma bibliotecas de componentes ricos em JavaServer Faces. A suíte de componentes inclui diversos campos de entrada, botões, tabelas de dados, árvores, gráficos, diálogos, etc.

#Comentários#
 Bibliotecas do Hibernate, JFreeChart, MySQL JDBC, Mojarra (implementação JSF da Oracle), JSTL, Facelets, Primefaces, Commons BeanUtils, Commons Digester, Commons Logging, Log4j e SL4J Log4j; Eclipse, JBoss Tools, EMS SQL Manager for MySQL, Toad for MySQL e MySQL GUI Tools, MySqlServer 6.0; JAVA 8; Tomcat 7; Maven2;

#Instruções:#
*  Necessário clonar o repositório do projeto via bitbucket e acesse o diretório:

#SSH#
git clone git@bitbucket.org:alexmmendes09/financeiro.git 

#HTTPS#
git clone https://bitbucket.org/alexmmendes09/financeiro.git

#Estrutura#

 * Em termos estruturais, o frontend foi estruturado da seguinte maneira:
src/ --> todo o código-fonte da aplicação. main/java --> classes responsáveis pelos endpoints RESTful, banco de dados (MongoDB) e configurações de segurança. Pré-Requisitos
 * Java 8+ Instância do Mysql Instalada, executar o arquivo: lucenelanches.sql
 * Procure por Maven package No terminal, rode apt-cache encontrada do maven, para recuperar todos os pacotes disponíveis do Maven.
 * $ apt-cache search maven .... libxmlbeans-maven-plugin-java-doc - Documentation for Maven XMLBeans Plugin maven - Java software project management and comprehension tool maven-debian-helper - Helper tools for building Debian packages with Maven maven2 - Java software project management and comprehension tool The maven package always comes with latest Apache Maven.
 * Instalando Execute o comando sudo apt-get install maven, para instalar a última versar do Apache Maven.
$ sudo apt-get install maven
 * Verificação Rode o comando mvn -version para validar sua instalação.

 * Build pacote projeto: Agora acessar o pacote com os códigos Java. Para trocar para o modo de linha de comando dentro do diretório do projeto e usar o gatilho para executar o build.
mvn clean install

 * Com a instância do tomcat7 configurada, execute:
sudo sh startup.sh
Via SSH: cd tomcat/bin; ./startup.sh

#Contato#
Alex Miguel Mendes
[alexm.mendes09@gmail.com](alexm.mendes09@gmail.com)