# 🚀 Projeto Interdisciplinar ( POO + SI )

### 🏛️ Universidade Portucalense (UPT) | Engenharia Informática
### 📋 UC: Sistemas de Informação & Programação Orientada a Objetos 
### 👩‍🏫 Docentes : Filomena Castro Lopes ( SI ) & Paula Morais ( POO )
### 📅 Ano Letivo : 2025/2026
---


## ✨ 1. Sobre o Projeto

> **TEMA ESCOLHIDO** : Sistema de atendimento do bar 
* **DESCRIÇÃO** : Este projeto une a componente de **Sistemas de Informação** ( identificação de requisitos, criação de User Stories, gestão do Product Backlog e criação de Sprint Backlog) com a componente de **Programação Orientada a Objetos** ( criação do diagrama de classes, desenvolvimento da arquitetura robusta e implementação completa em Java ).
* **OBJETIVO** : O objetivo deste projeto é compreender o funcionamento atual do bar, identificar
problemas existentes e propor soluções que respondam às necessidades dos
diferentes stakeholders.


## 🛠️ 2. Tecnologias Usadas

| Tecnologia | Versão / Detalhe |
|---|---|
| Java | JDK 17+ |
| IDE | Eclipse |
| Gestão de Projeto | SCRUM |
| Controlo de Versões | Git & GitHub |
| Modelação | Diagrama de Classes (UML) |

## 👥 3. Equipa de Desenvolvimento (Grupo 2)

 Nome | Número de Aluno |
|---|---|
| Ana Souto | `53986` |
| António Santos | `47303` |
| Diogo Ferreira | `53501` |
| Mathias Pando | `53035` |
| Tomás Santos | `54379` |

## 📐 4. Arquitetura do Sistema
A arquitetura do sistema foi desenhada de forma a seguir as boas práticas de POO, promovendo os conceitos de modularidade, coesão e capsulamento. A arquitetura do sistema também ajuda a entender e ter uma visão inicial dos contornos que o projeto terá, de forma a impulsionar positivamente o começo deste projeto. 
```
📦 Proj26E3/
├── 📂 src/
│   └── 📂 proj26E3/
│       ├── 📄 Administrador.java       # Classe do perfil Administrador
│       ├── 📄 CategoriaProduto.java    # Enum/Classe de categorias de produto
│       ├── 📄 Funcionario.java         # Classe base do Funcionário
│       ├── 📄 FuncionarioBar.java      # Classe do perfil Funcionário de Bar
│       ├── 📄 Gerente.java             # Classe do perfil Gerente
│       ├── 📄 GerirBar.java            # Classe principal de gestão do bar
│       ├── 📄 ItemPedido.java          # Classe que representa um item de um pedido
│       ├── 📄 Produto.java             # Classe de Produto
│       └── 📄 Teste.java              # Classe de testes
│       └── ...                  
├── 📄 .classpath
├── 📄 .gitignore
├── 📄 .project
└── 📄 README.md
```
## 🏃5. Metodologia SCRUM (Sprints)

O projeto foi dividido rigidamente em dois blocos de entrega (Incrementos):

### 📅 Sprint 1: [11/04] -> [24/04] 🔄
* **Objetivo do Sprint:** Criação de um sistema que permite registar e gerir funcionários e produtos, consultar o stock disponível, registar pedidos no balcão e efetuar pré-reservas por parte dos clientes.
* **Entregáveis:** 1º Incremento do código Java e relatório de conclusões (Sprint Retrospective 1).
---
### 📅 Sprint 2: [25/05] -> [08/06] 🔄
* **Objetivo do Sprint:** Por definir.
* **Entregáveis:** 2º Incremento (Aplicação Global) e Reflexão Global do PBL.
---

## 📜6. Regras de Nomenclatura ( GitHub )

> 🌿 **Relativamente á nomeação de branches :**
- Procurar seguir o formato tipo/descricao-curta; 
- Separar com hifens para melhor organização e leitura;
- Cada branch ter um objetivo especifico para evitar a multiplicidade desnecessária de branches.

**Exemplos:**
```
feature/login-utilizador
fix/calculo-total-pedido
docs/atualizar-readme
```
## ⚠️7. Resolução de Merge Conflicts (GitHub Desktop)

Um Merge Conflict ocorre quando dois membros editam o mesmo ficheiro/linha em branches diferentes.

### 📌 Passo a Passo

1. Fazer **fetch/pull** antes de começar a trabalhar → `Repository` → `Fetch origin` → `Pull`
2. O GitHub Desktop assinala os ficheiros em conflito com ⚠️
3. Clicar em **"Open in editor"** no ficheiro em conflito e resolver manualmente:
<<<<<<< HEAD (a tua versão)
código que tu escreveste
código que o colega escreveu
|||||||| branch-do-colega

4. Apagar os marcadores `<<<<<<<` `=======` `>>>>>>>` e ficar só com o código correto
5. Voltar ao GitHub Desktop → **"Mark as resolved"**
6. Clicar em **"Continue merge"** e fazer **push**

### 💡 Como evitar conflicts

- Fazer sempre **pull antes de começar** a trabalhar
- Cada membro trabalha em **ficheiros/funcionalidades diferentes**
- **Comunicar** antes de editar ficheiros partilhados (ex: `GerirBar.java`)
- Fazer **commits pequenos e frequentes**

---
> 💬 **Relativamente á nomeação de Commit:**
- `[ADD]` -- adicionar algo
- `[COR]` -- correção de um erro
- `[ORG]` -- organizar
- `[ATL]` -- atualização de algo
- `[REM]` -- remoção de código / ficheiros
- `[DOC]` -- relativo a documentação

Exemplos:
```
[ADD] classe Produto 
[COR] erro no cálculo do troco
[DOC] atualizar README com instruções de execução
```

---

### ⚖️ 8. Licença
Projeto académico desenvolvido no âmbito da Universidade Portucalense.
Uso restrito — não autorizado para fins comerciais ou reprodução sem permissão dos autores.
