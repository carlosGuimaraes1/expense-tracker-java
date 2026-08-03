# Expense Tracker Java

Uma aplicação de linha de comando para rastreamento de despesas, construída em Java para gerenciar finanças pessoais.

Projeto: https://roadmap.sh/projects/expense-tracker

## Funcionalidades

- Adicionar, atualizar e excluir despesas
- Listar todas as despesas
- Filtrar despesas por categoria
- Ver um resumo de todas as despesas
- Ver um resumo das despesas de um mês específico
- Definir um orçamento mensal com alertas ao ultrapassar ou se aproximar do limite
- Exportar despesas para um arquivo CSV

## Tecnologias

- Java 21
- NIO.2 para manipulação de arquivos
- Serialização Java para persistência de dados

## Estrutura do Projeto

```
src/
└── main/
    └── java/
        ├── controller/
        │   └── ExpenseController.java
        ├── model/
        │   ├── Expense.java
        │   └── Budget.java
        ├── service/
        │   ├── ExpenseManager.java
        │   └── BudgetManager.java
        ├── storage/
        │   ├── ExpenseStorage.java
        │   └── BudgetStorage.java
        └── Main.java
```

## Comandos

### Despesas

```bash
# Adicionar uma despesa
$ expense-tracker add --description "Almoço" --amount 20
# Despesa adicionada com sucesso (ID: 1)

# Adicionar uma despesa com categoria
$ expense-tracker add --description "Almoço" --amount 20 --category "Alimentação"
# Despesa adicionada com sucesso (ID: 1)

# Atualizar uma despesa
$ expense-tracker update --id 1 --description "Almoço" --amount 25
# Despesa atualizada com sucesso

# Excluir uma despesa
$ expense-tracker delete --id 1
# Despesa excluída com sucesso

# Listar todas as despesas
$ expense-tracker list
# ID    Data         Descrição       Categoria    Valor
# -------------------------------------------
# 1     2026-04-03   Almoço          Alimentação  $20.00

# Listar despesas por categoria
$ expense-tracker list --category "Alimentação"

# Resumo de todas as despesas
$ expense-tracker summary
# Total de despesas: $20.00

# Resumo por mês
$ expense-tracker summary --month 4
# Total de despesas em abril: $20.00

# Exportar para CSV
$ expense-tracker export
# Despesas exportadas com sucesso para /path/to/expense/expense.csv
```

### Orçamento

```bash
# Adicionar um orçamento mensal
$ expense-tracker budget add --month 4 --amount 500
# Orçamento adicionado com sucesso

# Atualizar um orçamento mensal
$ expense-tracker budget update --month 4 --amount 600
# Orçamento atualizado com sucesso

# Excluir um orçamento mensal
$ expense-tracker budget delete --month 4
# Orçamento excluído com sucesso

# Listar orçamento de um mês específico
$ expense-tracker budget list --month 4
```

## Armazenamento de Dados

Todos os dados são persistidos usando Serialização Java:

```
expense/
├── expense.ser   → dados das despesas
├── budget.ser    → dados do orçamento
└── expense.csv   → arquivo CSV exportado
```

## Tratamento de Erros

- Formato de número inválido
- Valores negativos
- IDs de despesa ou orçamento inexistentes
- Argumentos obrigatórios ausentes
- Aviso de orçamento ultrapassado
- Aviso de orçamento em 90%
