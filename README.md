# Validador de CPF e RG com AFD

## Sobre o Projeto

Este projeto tem como objetivo validar CPF e RG utilizando Autômatos Finitos Determinísticos (AFD). O sistema é desenvolvido em **Spring Boot** e conta com **testes unitários integrados** para garantir a precisão e confiabilidade das validações.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3+
- JUnit 5
- Mockito
- Maven
- Lombok

## Funcionalidades
- Registro Geral (RG), formatos aceitos:

  - XX.XXX.XXX-X (com separadores)
  - XXXXXXXX-X (com separador parcial)
  - XXXXXXXXX (sem separadores)
  - 1 a 9 dígitos (varia por estado).
  - O último dígito pode ser numérico ou X.

- Cadastro de Pessoa Física (CPF), formatos aceitos:

  - XXX.XXX.XXX-XX (com separadores)
  - XXXXXXXXX-XX (com separador parcial)
  - XXXXXXXXXXX (sem separadores)
  - Sempre 11 dígitos numéricos.
  - Os últimos dois dígitos são verificadores (sem validação algorítmica no AFD).
  - Não são aceitas sequências numéricas com valores únicos.

- Validação de CPF com regras padrão da Receita Federal.

- Validação de RG seguindo padrões de diferentes estados brasileiros.

- Implementação baseada em Autômatos Finitos Determinísticos (AFD) para validação.

- Testes unitários integrados garantindo a corretude dos algoritmos.


---

Desenvolvido por [Maycon Silva Cardoso](https://github.com/mayconCardos0) e [Arthur Farias dos Santos](https://github.com/ArthurFariasds).

