package com.company;

public class Arvore {
    private Valor RaizArvore;

    //Construtor da Arvore.
    public Arvore(){
        RaizArvore = null;
    }
    //Metodo da arvore.
    public Valor getRaizArvore(){
        return RaizArvore;
    }

    public int AlturaArvore(Valor valorAt){
        if (valorAt == null){
            //se não possui valores retorna -1.
            return -1;
        }
        if (valorAt.getDireita() == null && valorAt.getEsquerda() == null){
            //Quando a arvore possui somente um valor(raiz).
            return 0;
        }else if (valorAt.getEsquerda() == null){
            return 1 + AlturaArvore(valorAt.getDireita());
            //Quando valor só tem um filho retorna 1 mais a altura do valor filho.
        }else if (valorAt.getDireita() == null){
            return 1 + AlturaArvore(valorAt.getEsquerda());
            //Quando valor só tem um filho retorna 1 mais a altura do valor filho.
        }else {
            if (AlturaArvore(valorAt.getEsquerda()) > AlturaArvore(valorAt.getDireita())){
                return 1 + AlturaArvore(valorAt.getEsquerda());
                //Quando tem dois filhos 1 mais a altura do filho com a maior altura
                //e para quando chega nos valores folha que não possuem nenhum valor filho.
            }else {
                return 1 + AlturaArvore(valorAt.getDireita());
            }
        }
    }

    public void DefineBalanceamento(Valor valorAt){
        //O balanceamento é definido por meio da subtração da atutura da subarvore esquerda
        //menos a subarvore direita.
        valorAt.setBalancear(AlturaArvore(valorAt.getEsquerda()) - AlturaArvore(valorAt.getDireita()));
        if (valorAt.getDireita() != null){
            //Verificar os valores da direita
            DefineBalanceamento(valorAt.getDireita());
        }
        if (valorAt.getEsquerda() != null){
            //Verificar os valores da esquerda
            DefineBalanceamento(valorAt.getEsquerda());
        }
    }

    public Valor Equipara(Valor valorAt){
        //Se o valorAt possuir fator de balanceamento igual 2 e seu filho da esquerda fator de balanceamento
        //maior ou igual zero
        //valorAt sera substituido pelo resultado da rotação da direita.
        if (valorAt.getBalancear() == 2 && valorAt.getEsquerda().getBalancear() >= 0){
            valorAt = RotacionaParaDireita(valorAt);
        }
        //Se valorAt possuir fator de balanceamento igual -2 e seu
        //filho da direita fator de balanceamento menor que 0
        //valorAt sera substituido pelo resultado da rotação da Esquerda.
        else if (valorAt.getBalancear() == -2 && valorAt.getDireita().getBalancear() <= 0){
            valorAt = RotacionaParaEsquerda(valorAt);
        }
        //Para encontrar outros valores não balanceados.
        if (valorAt.getDireita() != null){
            Equipara(valorAt.getDireita());
        }
        if (valorAt.getEsquerda() != null){
            Equipara(valorAt.getEsquerda());
        }
        return valorAt;
        //Retorna a arvore com todos os filhos balanceados.
    }

//Comportamento para adicionar um elemento a arvore e no comportamento
//o elemento é adicionado de forma balanceada e também verifica se um valor ja existe
//na arvore.

    public void EmOrdem (Valor valorAt){
        if (valorAt != null){
            EmOrdem(valorAt.getEsquerda());
            System.out.println(valorAt.getDado() + valorAt.getBalancear());
            EmOrdem(valorAt.getDireita());
        }
    }
    //Mostra os valores em Pre ordem.
    public void PreOrdem(Valor valorAt){
        if (valorAt != null){
            System.out.println(valorAt.getDado() + valorAt.getBalancear());
            PreOrdem(valorAt.getEsquerda());
            PreOrdem(valorAt.getDireita());
        }
    }

    //Mostra os valores em Pos-Ordem
    public void PosOrdem(Valor valorAt){
        if (valorAt != null){
            PreOrdem(valorAt.getEsquerda());
            PreOrdem(valorAt.getDireita());
            System.out.println(valorAt.getDado() + valorAt.getBalancear());
        }
    }

    public void add(int numero, Valor valorAt){
        //para ver se ta vazio.
        //se estiver o elemento adicionado sera a raiz
        if (RaizArvore == null){
            Valor OutroValor = new Valor(numero);
            RaizArvore = OutroValor;
            DefineBalanceamento(RaizArvore);
            //Para definir o tipo de balanceamento de cada um dos valores.
        }else {
            if (numero > valorAt.getDado()){
                //Maior que a raiz adiciona na direita
                if (valorAt.getDireita() == null){
                    Valor OutroValor = new Valor(numero);
                    valorAt.setDireita(OutroValor);
                    OutroValor.setPai(valorAt);
                    //Para definir o tipo de balanceamento de cada um dos valores.
                    DefineBalanceamento(RaizArvore);
                    //Para balancear a arvore.
                    RaizArvore = Equipara(RaizArvore);
                }else {
                    add(numero, valorAt.getDireita());
                    //Pega a direita do ValorAt
                }
            }else if (numero < valorAt.getDado()){
                //Se é menor que a raiz vai para a esquerda.
                if (valorAt.getEsquerda() == null){
                    Valor OutroValor = new Valor(numero);
                    valorAt.setEsquerda(OutroValor);
                    DefineBalanceamento(RaizArvore);
                    //Para definir o tipo de balanceamento de cada um dos valores
                    RaizArvore = Equipara(RaizArvore);
                    //Para balancear a arvore.
                }else {
                    add(numero, valorAt.getEsquerda());
                    //Pega o valor da esquerda do valorAt
                }
            }else {
                //Aqui é para verificar se um elemento ja existe dentro da arvore e
                //se existir fala que ja existe.
                System.out.println("Valor ja existente na arvore!");
            }
        }
    }

    public Valor Retira(int numero, Valor valorAt){
        if (RaizArvore == null){
            //se não tem raiz a arvore esta vazia então retorna nulo.
            return null;
        }else if (numero > valorAt.getDado()){
            //valor removido maior que o valorAt o valorAt vai para a direita
            valorAt.setDireita(Retira(numero, valorAt.getDireita()));
        }else if (numero < valorAt.getDado()){
            //valor removido menor que o valorAt o valorAt vai para a esquerda
            valorAt.setEsquerda(Retira(numero, valorAt.getEsquerda()));
        }else {
            if (valorAt.getDireita() == null && valorAt.getEsquerda() == null){
                //Se o valor não tem subarvores ele é removido.
                if (valorAt == RaizArvore){
                    RaizArvore = null;
                }else {
                    valorAt = null;
                }
            }else if (valorAt.getEsquerda() == null){
                //Se valor tem subarvore somente para direita, substitui valor da raiz por valorAt.
                valorAt = valorAt.getDireita();
            }else if (valorAt.getDireita() == null){
                //Se valor tem subarvore somente para esquerda, substitui valor da raiz por valorAt e troca
                //pela raiz da subarvore direita.
                valorAt = valorAt.getEsquerda();
            }else {
                // Se o valor possui duas subarvores.
                Valor OutroValor = valorAt.getEsquerda();
                //Primeiro:Cria OutroValor pra percorrer a arvore
                while (OutroValor.getDireita() != null){
                    //Segundo: OutroValor vai ate o valor localizado mais a direita na subarvore da esquerda.
                    OutroValor = OutroValor.getDireita();
                }
                valorAt.setDado(OutroValor.getDado());
                //Terceiro: Troca valorAt por OutroValor e depois troca OutroValor por ValorAt.
                OutroValor.setDado(numero);
                valorAt.setEsquerda(Retira(numero, valorAt.getEsquerda()));
                //Recursão que remove Outrovalor que agora armazena o ValorAt
            }
        }
        if (RaizArvore != null){
            DefineBalanceamento(RaizArvore);
            //Para Balancear a Arvore
            RaizArvore = Equipara(RaizArvore);
        }
        return valorAt;
        //Retorna o ValorAt que foi trocado por OutroValor
    }
    //Mostra os valores em ordem.


    //Função de rotação simples para a direita.
    public Valor RotacionaParaDireita(Valor valorAt){
        Valor OutroValor = valorAt.getEsquerda();
        //Garda valor da esquerda do valorAt.
        OutroValor.setPai(valorAt.getPai());
        if (OutroValor.getDireita() != null){
            OutroValor.getDireita().setPai(valorAt);
            //Função para tratar arvores que possuem formato de listas.
        }
        valorAt.setPai(OutroValor);
        valorAt.setEsquerda(OutroValor.getDireita());
        //Pega o No da direita do No que se localiza na Esquerda do valorAt
        //e coloca no lado esquerdo do ValorAt e troca o No da esquerda pelo ValorAt.
        OutroValor.setDireita(valorAt);
        if (OutroValor.getPai() != null){
            //Quando OutroValor não é raiz principal, faz o pai apontar para o No novo
            if (OutroValor.getPai().getDireita() == valorAt){
                OutroValor.getPai().setDireita(OutroValor);
            }else if (OutroValor.getPai().getEsquerda() == valorAt){
                OutroValor.getPai().setEsquerda(OutroValor);
            }
        }
        DefineBalanceamento(OutroValor);
        //Remodela o valor do balanceamento
        return OutroValor;
        //Devolve o valor do No da Direita que agora é pai.

    }
    //Função de rotação simples para a esquerda.
    public Valor RotacionaParaEsquerda(Valor valorAt){
        Valor OutroValor = valorAt.getDireita();
        //Guarda valor da direita do valorAt
        OutroValor.setPai(valorAt.getPai());
        if (OutroValor.getEsquerda() != null){
            OutroValor.getEsquerda().setPai(valorAt);
            //Função para tratar arvores que possuem formato de listas.
        }
        valorAt.setPai(OutroValor);
        valorAt.setDireita(OutroValor.getEsquerda());
        //Pega o No da esquerda do No que se localiza na direita do valorAt
        //e coloca no lado direito do ValorAt e troca o No da direita pelo ValorAt.
        OutroValor.setEsquerda(valorAt);
        if (OutroValor.getPai() != null){
            //Quando OutroValor não é raiz principal, faz o pai apontar para o No novo
            if (OutroValor.getPai().getDireita() == valorAt){
                OutroValor.getPai().setDireita(OutroValor);
            }else if (OutroValor.getPai().getEsquerda() == valorAt){
                OutroValor.getPai().setEsquerda(OutroValor);
            }
        }
        DefineBalanceamento(OutroValor);
        //Remodela o valor do balanceamento
        return OutroValor;
        //Devolve o valor do No da esquerda que agora é pai.
    }

}
