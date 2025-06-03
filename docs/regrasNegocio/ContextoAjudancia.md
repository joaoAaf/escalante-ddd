<div style="text-align:justify">

## Contexto Ajudância

### Descrição da Necessidade:
Guardar e gerenciar os dados dos militares do CBMCE do quartel de Limoeiro do Norte, atuando como auxiliar dos demais contextos do sistema.

### Regras de Negócio:

1. Somente os militares com perfil do setor da ajudância podem cadastrar, visualizar, atualizar ou remover os dados de qualquer militar no sistema;

1. O comando do quartel pode somente visualizar os dados dos militares cadastrados no sistema;

1. Os demais militares podem somente visualizar seus proprios dados;

1. Deve existir um histórico de alterações dos dados do militar com a versão anterior destes, a data da alteração e o usuário responsável por ela;

1. Cada militar deve possuir os seguintes dados cadastrados: 
    - Matrícula;
    - CPF;
    - Nome completo;
    - Nome de paz;
    - Data de nascimento;
    - Sexo;
    - Telefone;
    - E-mail;
    - Endereço;
    - Patente;
    - Número de antiguidade;
    - Tamanho dos uniformes;
    - Quantidade de folgas, caso possua folga especial;
    - Se ele é C.O.V.;
    - Se ele pode ser escalado;
    - Se o status do militar está ativo ou inativo.

1. No caso de um militar com status inativo, deve ser registrada a data em que este militar foi
inativado e o motivo da inativação;

1. A antiguidade será definida por um numero que é atribuido a cada militar, os militares que possirem os menores numeros serão os mais antigos;

1. Não haverão dois militares com o mesmo numero de antiguidade;

1. O numero que define a antiguidade deve condizer com a hierarquia dos Postos ou Graduações dos militares, de forma que nunca haverá um militar em um Posto ou Graduação com nivel hierarquico superior com numero de antiguidade maior que outro em um Posto ou Graduação com nivel hierarquico inferior. Abaixo esta definida em ordem hierarquica dos Postos e Graduações:
    - **TEN** > **SUB TEN** > **SGT** > **CB** > **SD**;
    - Ex: Se o **TEN** menos antigo tiver o número de antiguidade igual 5, nenhum **SUB TEN** pode ter um numero de antiguidade menor que 5;

1. Os dados de um militar só podem ser removidos do sistema, após este ter sido inativado a pelo menos 1 ano;

1. Além dos dados supracitados, deve haver um registro das ausencias legais dos militares, contendo o período da ausencia e o motivo desta.

</div>