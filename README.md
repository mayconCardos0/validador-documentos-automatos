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

## Representação do Autômato

### Autômato para RG
Abaixo está a imagem representando o Autômato Finito Determinístico (AFD) utilizado para a validação de RG:

![Autômato Finito Determinístico RG](https://github.com/user-attachments/assets/c4178f18-b0b8-4f12-9ac4-783500b2310e)

### Tabela de Transições

| Estado Atual | Entrada | Próximo Estado |
|-------------|---------|---------------|
| q0          | [0-9]   | q1            |
| q1          | [0-9]   | q2            |
| q2          | .       | q3            |
| q2          | [0-9]   | q4            |
| q3          | [0-9]   | q4            |
| q4          | [0-9]   | q5            |
| q5          | [0-9]   | q6            |
| q6          | .       | q7            |
| q6          | [0-9]   | q8            |
| q7          | [0-9]   | q8            |
| q8          | [0-9]   | q9            |
| q9          | [0-9]   | q10           |
| q10         | -       | q11           |
| q10         | [0-9]   | q12           |
| q11         | [0-9]   | q12           |

### Autômato para CPF
Abaixo está a imagem representando o Autômato Finito Determinístico (AFD) utilizado para a validação de CPF:

![Autômato Finito Determinístico CPF drawio](https://github.com/user-attachments/assets/fb90b0e8-701d-4825-ba7a-aecae31725dc)

### Tabela de Transições

| Estado Atual | Entrada | Próximo Estado |
|-------------|---------|---------------|
| q0          | [0-9]   | q1            |
| q1          | [0-9]   | q2            |
| q2          | [0-9]   | q3            |
| q3          | .       | q4            |
| q3          | [0-9]   | q5            |
| q4          | [0-9]   | q5            |
| q5          | [0-9]   | q6            |
| q6          | [0-9]   | q7            |
| q7          | .       | q8            |
| q7          | [0-9]   | q9            |
| q8          | [0-9]   | q9            |
| q9          | [0-9]   | q10           |
| q10         | [0-9]   | q11           |
| q11         | -       | q12           |
| q11         | [0-9]   | q13           |
| q12         | [0-9]   | q13           |
| q13         | [0-9]   | q14           |




---

Desenvolvido por [Maycon Silva Cardoso](https://github.com/mayconCardos0) e [Arthur Farias dos Santos](https://github.com/ArthurFariasds).

