<h1 align="center"> selfservice </h1>

# Índice
* [Índice](#índice)
* [Descrição do Projeto](#descrição-do-projeto)
* [Status do Projeto](#status-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Instalação do aplicativo](#Instalação-do-aplicativo)
* [Tecnologias utilizadas](#tecnologias-utilizadas)

# Descrição do Projeto

<p>selfservice é uma aplicação com intuito de agilizar o processo de atendimento,
<br>Alem de agilizar o atendimento, o selfservice acompanha funcionalidades como gestão de estoque e de vendas bem detalhadas, onde se pode fazer analise de dados e prever futuras altas e baixas.</p>

# Status do Projeto
<img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=EM-ANDAMENTO&labelColor=%2339362C&color=%2328a745&style=for-the-badge" />


# Funcionalidades
![image](https://github.com/user-attachments/assets/536086d2-00e2-4b84-9be5-ca8bc768cdf4)<bR>

### Request Login POST: (/login)
   >> Recebendo um JSON, esta request fará a autenticação de um usuario com Login e Senha e irá gerar um token, liberando acesso a parte do sistema.<br>
   ![image](https://github.com/user-attachments/assets/aa5e9134-3d74-4f88-875f-1cc7f6b24b79)

### Request Register: (/register-seller)
>>esta request, cadastra usuarios do tipo VENDEDOR no banco de dados.
<br>A aplicação pedirá o login de um user ADMIN, sendo o Json construido da seguinte forma:<br>
![image](https://github.com/user-attachments/assets/b82b9691-ff1e-4ed8-807b-654a3d2407ea)

### Request Register: (/register-customer)
>>para CLIENTES, não será necessário senha e login.
<br>Apenas seu nome e telefone será salvo no banco de dados, o telefone deverá conter 11 digitos, incluindo o DDD. O json será construido da seguinte forma:<br>
![image](https://github.com/user-attachments/assets/da881d1b-9d56-424e-9514-d4f6417c7537)


### Request Register: (/admin/delete)
>>essa request apagará o user VENDEDOR pelo login.
<br>O json terá que conter um login do ADMIN, construido da seguinte forma:<br>
![image](https://github.com/user-attachments/assets/b830dd68-7e21-4ab8-9a73-dc90a6431125)

