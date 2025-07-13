# 📚 Biblioteca Unicesumar

Este é um sistema simples de cadastro e listagem de livros desenvolvido como parte de um trabalho acadêmico para a disciplina de IMERSÃO PROFISSIONAL: FÁBRICA DE SOFTWARE da **Unicesumar**.

---

## 🧾 Descrição do Projeto

O projeto consiste em uma aplicação web desenvolvida com **Java (Servlet + JSP)**, permitindo ao usuário cadastrar livros em uma biblioteca e visualizar a lista de livros já registrados. É uma aplicação básica voltada para fins didáticos, demonstrando a integração entre front-end (HTML + CSS) e back-end (Servlets).

---

## 🎯 Funcionalidades

- ✅ Cadastro de livros com os campos:
  - Título
  - Autor
  - Ano
  - ISBN (13 dígitos)

- ✅ Validação no campo ISBN (somente 13 números)
- ✅ Exibição da lista de livros cadastrados
- ✅ Exclusão de livros da lista
- ✅ Exibição de mensagens de erro em caso de falha no cadastro

---

## 📦 Estrutura do Projeto

```
BibliotecaUnicesumar/
├── src/
│   └── controller/
│       └── LivroServlet.java
│   └── model/
│       └── Livro.java
├── web/
│   └── view/
│       └── index.jsp
│       └── img/
│           └── unicesumar.png
├── WEB-INF/
│   └── web.xml
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos:

- ✅ JDK 8 ou superior
- ✅ Apache Tomcat (recomendado: 9 ou superior)
- ✅ IDE como IntelliJ IDEA ou Eclipse
- ✅ Git (opcional, para clonar o repositório)

### Passos:

1. **Clone o projeto:**

```bash
git clone https://github.com/deboralorayne/biblioteca-unicesumar.git
```

2. **Importe o projeto em sua IDE**

   - No IntelliJ: *File > Open > Selecione a pasta do projeto*
   - No Eclipse: *File > Import > Existing Projects into Workspace*

3. **Configure o servidor Tomcat na IDE**

4. **Execute o projeto**

   Acesse no navegador:
   ```
   http://localhost:8080/biblioteca-unicesumar/
   ```

---

## 💡 Como Usar

1. Acesse a página principal
2. Preencha o formulário com os dados do livro
3. Clique em **Cadastrar**
4. O livro será listado abaixo
5. Para excluir um livro, clique no botão **Excluir** ao lado do item correspondente

---

## 📘 Tecnologias Utilizadas

- Java (Servlets)
- JSP
- HTML5 + CSS3
- Font Awesome (ícones)
- Apache Tomcat
- IntelliJ IDEA (IDE)

---

## 🎓 Trabalho Acadêmico

Este sistema foi desenvolvido como **atividade prática** da disciplina de **IMERSÃO PROFISSIONAL: FÁBRICA DE SOFTWARE** da **Unicesumar - Centro Universitário Cesumar**. O objetivo era praticar o uso de Servlets, JSPs, estrutura MVC básica e integração com páginas HTML estilizadas.

---

## 👩‍💻 Autoria

Desenvolvido por **Debora Lorayne**  
[GitHub](https://github.com/deboralorayne)

---

## 📄 Licença

Este projeto é de uso acadêmico e **não possui licença comercial**.  
Sinta-se à vontade para utilizar como base de estudos.
