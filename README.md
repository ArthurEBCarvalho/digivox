# Digivox

Projeto de seleção para vaga de desenvolvedor Pleno - Dogivox

## O que foi desenvolvido no Projeto:

* CRUDS:
	* Tipo de Item;
	* Item;
	* Cliente;
	* Aluguel.
* Aluguel tem um campo tipo, onde define se é Aluguel ou Reserva:
	* No caso do cadastro de um Aluguel, a data inicial é, obrigatoriamente, a atual (hoje);
	* No caso do cadastro de uma Reserva, a data inicial é, no mínimo, a de um dia depois do atual (amanhã).
* Reservar item, cancelar reserva, alugar item e devolução de item estão no CRUD de Aluguel, mas o menu possui uma opção para cada uma das operações.
* O Dashboard (tela inicial) possui 3 listagens:
	* Itens que serão devolvidos na semana atual;
	* Itens que foram alugados na semana atual;
	* Itens que estão reservados;
* Quando se cadastra um Aluguel ou faz uma Reserva num item que já está alugado ou reservado dentro do período selecionado, o sistema retorna para o formulário, exibindo uma mensagem de erro.

Todo o projeto foi feito utilizando as tecnologias JDK 1.8, Maven 4, Postgres e Spring Boot. Não utilizei o React, pois nunca trabalhei com ele. Até estudei um pouco e entendi o funcionamento, mas optei por não utilizar.

Como adicional, utilizei Lombok para enxugar o código dos Models e o layout thymeleaf.

O arquivo db_digivox.pgsql é o banco de dados gerado e utilizado no desenvolvimento do projeto.