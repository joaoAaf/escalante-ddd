<div style="text-align:justify">

## Contexto Escalante

### Descrição da Necessidade:
Com base no domínio apresentado, verifica-se que a elaboração de escalas de serviço operacional é uma atividade complexa e demanda uma quantidade excessiva de tempo do escalante, devido a forma manual de sua execução.

Para mitigar esta complexidade e reduzir consideravelmente tempo de execução desta tarefa, propõe-se um sistema que gerencie e automatize a criação das escalas de forma a atender as peculiaridades do domínio e prover eficiência a este processo.

### Regras de Negócio:

1. A escala deve elaborada mensalmente, de forma a permitir que o escalante defina o número de dias trabalhados por equipe e as datas de inicio e fim da escala, para que o sistema escale automaticamente as equipes dentro do intervalo de datas definido, ou que o escalante possa escalar manualmente uma equipe em um ou mais dias;

1. Os militares que comporem a escala devem prestar o serviço operacional no regime de 1/3 (1 dia de serviço para 3 dias de folga) ou 1/4 (1 dia de serviço para 4 dias de folga), caso estes exerçam a função de Fiscal de Dia ou C.O.V.;
1. A relação de serviços/folgas deve poder ser aumentada proporcionalmente conforme solicitado pelo comando, por exemplo 1/3 --> 2/6 --> 3/9;
1. Podem existir relações de serviços/folgas especiais tanto militares como patentes específicas;
1. Cada dia de serviço deverá haver 01 equipe com pelo menos 05 militares, 01 para cada função, somente em casos extremos a equipe pode ser reduzida (menor que 5);
1. Cada militar, durante o período de um mês, deve participar de pelo menos 2 serviços operacionais;
1. Caso, durante um mês, hajam militares suficientes para que todas as equipes escaladas tenham pelo menos 5 militares e ainda existam militares que não participaram de pelo menos 2 serviços operacionais, estes militares devem distribuídos nas equipes de forma que todos tenham participado de pelo menos 2 serviços operacionais;
1. As equipes serão divididas em 5 funções, estas serão distribuídas aos militares escalados de acordo com suas patentes, conforme as tabelas abaixo:

    #### PRIORIDADES PARA ASSUMIR FUNÇÕES

    <div style="text-align:center">
    
    | | TEN | SUB TEN | SGT | CB | SD
    --- | --- | --- | --- | --- | ---
    **Fiscal de Dia** | 1º | 2º | 3º | 4º | 5º
    **Operador de Linha** | não assume | 4º | 3º | 1º | 2º
    **Ajudante de Linha** | não assume | não assume | não assume | 1º | 2º
    **Permanente** | não assume | não assume | não assume | 2º | 1º
    **C.O.V.** | - | - | - | - | -

    </div>

1. Qualquer militar poderá assumir a função de C.O.V., desde que esteja autorizado a exercê-la;

1. No momento da seleção dos militares para cada função deverá ser levada em conta a antiguidade do militar. A seleção das funções será feita na seguinte ordem:
    - **1º C.O.V.**: Os mais antigos serão escolhidos primeiro;
    - **2º Fiscal de Dia**: Os mais antigos serão escolhidos primeiro;
    - **3º Permanente**: Os mais modernos serão escolhidos primeiro;
    - **4º Ajudante de Linha**: Os mais modernos serão escolhidos primeiro;
    - **5º Operador de Linha**: Os mais modernos serão escolhidos primeiro.

1. Deve-se escalar primeiro os militares que estiverem a mais tempo fora da escala;
1. Os militares autorizados a exercerem a função de **C.O.V.** serão os últimos a serem escolhidos para as demais funções, com exceção da função de **Fiscal de Dia**.
1. O militar que assumir a função de **Fiscal de Dia** também pode assumir a de **C.O.V.**, neste caso deverão haver pelo menos 02 **Operadores de Linha** na escala;
1. Todas as alterações da escala que ocorrerem durante um mês de sua execução, devem ser registradas de forma separada da escala original, de forma a possibilitar a revisão pelo escalante;
1. Com base na escala executada de um mês, devem ser contados os serviços de cada militar realizou a fim de apresentar ao comando.

</div>