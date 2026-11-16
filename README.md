# TDE3
Atividade feita para a matéria de performace em sistemas cyberfísicos na PucPr

link do vídeo: 

Feito por:
 Maristela Alves Lopes Lederer
 Rodrigo do Amaral Junior
 Vitoria Giovana Aparecida de Faria
Vitoria Giovana Aparecida de Faria



Sobre o problema Jantar dos filósofos:
Esse é um problema no qual existem 5 filósofos sentados a uma mesa redonda alternando entre pensar e comer, o problema começa por que para comer um filósofo precisa de dois garfos (ninguém come com 2 garfos eu sei) e esses garfos são compartilhados entre os filósofos, então na prática só temos 5 garfos na mesa junto com os 5 filósofos. <br>
Esse problema trás a tona algumas ocorrencias comuns em programação concorrente e nosso objetivo é garantir que não ocorra deadlock neste jantar, para fazermos isso negamos uma das condições de Coffman (condições para ocorrer um deadlock), a condição negada foi a de espera circular, que negamos fazendo com que os filósofos não pegassem os garfos de forma circular.<br>
Os filósofos pegam os garfos na seguinte ordem e com os seguintes fatos de escolha, primeiro o garfo esquerdo, escolhido por garfos[i] sendo i o indice do filósofo, e depois o direito escolhido por garfos[(i + 1) % f]. <br>

Antes de resolver o problema os filosofos pegavam os garfos na seguinte ordem: f0--> g0, f1--> g1, f2--> g2, f3--> g3, f4--> g4. E como no sistema o objetivo era simular uma thread, todos eles pegam o primeiro garfo antes de quanquer 1 conseguir pegar o segundo.<br> 
Depois de resolver o problema, os garfos são escolhidos pelos seguintes parametros:<br> int g1=i;
            <br>int g2= (i+1) %f;
            <br>int menor = Math.min(g1, g2);
            <br>int maior = Math.max(g1,g2);<br>
sendo o garfo esquerdo: garfos[menor], e o direito: garfos[maior], assim eles pegam os garfos na seguinte ordem: <br>
f0--> g0, f1--> g1, f2--> g2, f3--> g3, f4--> g0.
assim ignorando a espera circular.


Fluxograma:<br>
[Inicio]<br>
   |<br>
[Pensando]<br>
   |<br>
[Com fome]<br>
   |<br>
[Pedir garfo de menor número]<br>
   |<br>
<Garfo livre?> --não--> [Esperar na fila] --volta--> <Garfo livre?><br>
   | sim<br>
[Pedir garfo de maior número]<br>
   |<br>
<Garfo livre?> --não--> [Esperar na fila (segurando o 1º)] --volta--> <Garfo livre?><br>
   | sim<br>
[Comendo]<br>
   |<br>
[Soltar garfo de MAIOR número]<br>
   |<br>
[Soltar garfo de MENOR número]<br>
   |<br>
(volta para Pensando)<br>
