# TDE3
Atividade feita para a matéria de performace em sistemas cyberfísicos na PucPr

link do vídeo: https://youtu.be/8PMqQ946umw

Feito por:<br>
 Maristela Alves Lopes Lederer<br>
 Rodrigo do Amaral Junior<br>
 Vitoria Giovana Aparecida de Faria<br>



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

Parte 2 - Threads e Semáforos (Contador)
Sobre o problema do Contador Concorrente: Esse é um problema no qual a gente tem várias threads rodando ao mesmo tempo e tentando somar +1 numa mesma variável compartilhada , fazendo isso 200.000 vezes cada um. O problema começa porque a operação de somar count = count + 1 não é feita de uma vez so pelo computador, a thread precisa ler o valor que está na variavel,e somar 1 e salvar de novo.

Esse problema traz à tona a condição de corrida"na programação concorrente. Nosso objetivo é mostrar o erro acontecendo e depois consertar. Na primeira versão do código  as threads leem o mesmo número juntas e acabam salvando valores iguais, apagando o incremento das outras. Por isso o valor final nunca chega no esperado de 1.600.000.

Depois de resolver o problema, usamos um Semáforo inicializado com 1 permissão . Antes de fazer a conta, a thread dá um "acquire" no semáforo. Se tiver outra thread lá, ela fica na fila. Quando termina, ela da o "release". Isso arruma a contagem e garante a visibilidade dos dados, mas a gente notou que o codigo corrigido demora mais para rodar  porque forçamos uma fila para somar (o throughput cai).

Fluxograma da solução:
[Inicio]
|
[Thread começa o loop]
|
[Pedir permissão pro Semáforo]
|
<Permissão livre?> --não--> [Esperar na fila do Semáforo]
| sim
[Ler o contador]
|
[Somar +1 e salvar]
|
[Soltar a permissão (Release)]
|
(Volta pro loop ou termina)
   |<br>
[Soltar garfo de MENOR número]<br>

Parte 3 - Deadlock
Sobre o problema do Deadlock: Esse é um problema criado para forçar e tratar um travamento total do sistema. Aqui temos duas threads e dois recursos . O problema começa porque a Thread 1 esta programada para pegar o Lock A e depois o B, enquanto a Thread 2 faz o contrario pega o Lock B e depois o A. Colocamos um sleep de 50ms no meio pra garantir que as duas peguem o primeiro lock juntas.

Esse problema traz a tona as 4 condições de Coffman rolando ao mesmo tempo. O sistema trava porque a Thread 1 fica segurando o A e esperando o B, e a Thread 2 segurando o B e esperando o A.

Nosso objetivo é garantir que o deadlock não ocorra na versão corrigida, e para fazermos isso paramos com a condição de espera circular. A condição foi negada criando uma hierarquia global de recursos: a gente obrigou que no codigo inteiro, não importa a thread, o Lock A TEM que ser pedido antes do Lock B.

Assim a Thread 1 pega o A, e quando a Thread 2 tenta rodar, ela esbarra no Lock A que já tá ocupado. A Thread 1 pega o B tranquilo, faz o trabalho dela, solta os dois e só aí a Thread 2 consegue trabalhar, ignorando a espera circular.

Fluxograma da versão corrigida:
[Inicio]
|
[Threads rolando]
|
[Pedir Lock A (sempre o A primeiro)]
|
<Lock A livre?> --não--> [Esperar na fila do Lock A]
| sim
[Pedir Lock B]
|
<Lock B livre?> --não--> [Esperar na fila do Lock B (segurando o A)]
| sim
[Executar a seção crítica]
|
[Soltar Lock B]
|
[Soltar Lock A]
|
(Fim)
   |<br>
(volta para Pensando)<br>
