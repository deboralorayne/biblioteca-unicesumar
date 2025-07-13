<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Biblioteca Unicesumar</title>

    <!-- Importa ícones do Font Awesome para uso de ícones visuais -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Estilos CSS embutidos para layout e design da página -->
    <style>
        /* Reset básico para margens, paddings e definição da fonte */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        /* Estilo geral do corpo da página */
        body {
            background-color: #cbdffd; /* cor de fundo azul clara */
            color: #333; /* cor do texto */
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center; /* centraliza conteúdo na horizontal */
        }

        /* Estilo do cabeçalho */
        header {
            background-color: #004080; /* azul escuro */
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px 40px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.2);
            border-radius: 12px;
            max-width: 900px;
            width: 100%;
        }

        /* Container do logo e texto do cabeçalho */
        .header-left {
            display: flex;
            align-items: center;
            gap: 15px; /* espaço entre logo e texto */
        }

        /* Ajuste do tamanho do logo */
        .header-left img {
            height: 60px;
            width: auto;
        }

        /* Container do texto ao lado do logo */
        .header-text {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            line-height: 1.1; /* reduz espaçamento vertical */
        }

        /* Texto "Biblioteca" em destaque */
        .header-text .biblioteca {
            font-size: 1.6rem;
            font-weight: bold;
            color: white;
        }

        /* Texto "Unicesumar" com cor e tamanho menores */
        .header-text .unicesumar {
            font-size: 1.1rem;
            color: #a2c4ff;
            margin-top: 0;
        }

        /* Estilos para o formulário e a listagem dos livros */
        .container-form,
        .livros-container {
            background-color: #ffffff; /* fundo branco */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* sombra sutil */
            padding: 2rem;
            border-radius: 12px;
            max-width: 900px;
            width: 100%;
            margin: 40px 0; /* espaçamento vertical */
        }

        /* Título dentro do formulário */
        .container-form h2 {
            font-size: 1.8rem;
            color: #004080;
            margin-bottom: 1.5rem;
            text-align: center;
        }

        /* Formulário com layout vertical e espaçamento entre campos */
        form {
            display: flex;
            flex-direction: column;
            gap: 1.2rem;
        }

        /* Estilo dos rótulos dos campos */
        label {
            font-weight: bold;
            color: #004080;
            margin-bottom: 0.5rem;
            display: block;
        }

        /* Campos de entrada de texto e número */
        input {
            padding: 0.6rem;
            border: 1px solid #003366;
            border-radius: 8px;
            width: 100%;
            font-size: 1rem;
        }

        /* Botão de envio do formulário */
        button[type="submit"] {
            background-color: #004080;
            color: #fff;
            border: none;
            padding: 0.8rem;
            border-radius: 8px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        /* Efeito hover no botão */
        button[type="submit"]:hover {
            background-color: #003366;
        }

        /* Estilo para cada livro na lista */
        .lista {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem;
            border-bottom: 1px solid #dce6f0;
        }

        /* Remove a borda do último item da lista */
        .lista:last-child {
            border-bottom: none;
        }

        /* Estilo para ícones dentro da lista */
        .lista i {
            color: #004080;
            margin-right: 0.5rem;
        }

        /* Estilo do botão excluir dentro do formulário de exclusão */
        .delete-form button {
            background-color: #d9534f; /* vermelho */
            border: none;
            color: #fff;
            padding: 0.4rem 0.8rem;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        /* Efeito hover para o botão excluir */
        .delete-form button:hover {
            background-color: #c9302c;
        }

        /* Estilo da caixa de mensagem de erro */
        .mensagem-erro {
            background-color: #ffdddd; /* fundo vermelho claro */
            color: #a94442; /* texto vermelho escuro */
            padding: 1rem;
            border-left: 5px solid #d9534f;
            border-radius: 8px;
            max-width: 900px;
            width: 100%;
            margin: 20px 0;
        }

        /* Logo oculto - não está visível na página */
        .logo {
            display: none;
        }
    </style>
</head>
<body>

<header>
    <!-- Cabeçalho com logo e texto -->
    <div class="header-left">
        <img src="view/img/unicesumar.png" alt="Logo Unicesumar" />
        <div class="header-text">
            <div class="biblioteca">Biblioteca</div>
            <div class="unicesumar">Unicesumar</div>
        </div>
    </div>
</header>

<!-- Exibe mensagem de erro caso exista (definida no servlet) -->
<% if (request.getAttribute("mensagemErro") != null) { %>
<div class="mensagem-erro">
    <i class="fa-solid fa-triangle-exclamation"></i>
    <%= request.getAttribute("mensagemErro") %>  <!-- Mensagem dinâmica -->
</div>
<% } %>

<!-- Formulário para cadastro de livros -->
<div class="container-form">
    <h2>Cadastro de livros</h2>
    <form action="livros" method="post">
        <div>
            <label for="titulo">Título:</label>
            <input type="text" name="titulo" required />
        </div>
        <div>
            <label for="autor">Autor:</label>
            <input type="text" name="autor" required />
        </div>
        <div>
            <label for="ano">Ano:</label>
            <input type="number" name="ano" required />
        </div>
        <div>
            <label for="isbn">ISBN:</label>
            <input type="text"
                   name="isbn"
                   pattern="[0-9]{13}"
                   title="Digite exatamente 13 números. Ex: 9788512345678"
                   placeholder="9788512345678"
                   required />
        </div>
        <button type="submit">Cadastrar</button>
    </form>
</div>

<!-- Container para exibir a listagem dos livros gerada dinamicamente pelo servlet -->
<div class="livros-container">
    <%= request.getAttribute("htmlLivros") %>
</div>

</body>
</html>
