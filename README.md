# 📚 LivrariaApp

LivrariaApp é um aplicativo Android desenvolvido em Kotlin para gerenciar livros e usuários. Ele permite cadastrar, listar, editar e visualizar detalhes de livros, além de autenticação de usuários.

Este aplicativo foi desenvolvido como parte da disciplina **Programação para Dispositivos Móveis** na **Universidade Federal do Rio Grande do Norte (UFRN)**. O objetivo do projeto foi criar um sistema de gerenciamento de livros para uma livraria, incluindo funcionalidades como login, cadastro de usuários e um banco de dados local para armazenamento dos livros.

## 🚀 Funcionalidades

- 🔐 **Login e Cadastro de Usuário**: Permite criar uma conta e acessar o sistema.
- 📖 **Cadastro de Livros**: Adicionar novos livros ao catálogo.
- 📋 **Listagem de Livros**: Exibir os livros cadastrados utilizando RecyclerView.
- 🔍 **Detalhes do Livro**: Visualizar informações completas sobre um livro.
- ✏️ **Edição de Livros**: Modificar informações de livros existentes.
- ❌ **Exclusão de Livros**: Remover livros cadastrados no sistema.
- 🔄 **Recuperação de Senha**: Permite redefinir a senha do usuário.
- 🗄 **Banco de Dados SQLite**: Armazena e gerencia as informações dos livros.
- 🖼 **Carregamento de Imagens**: Uso da biblioteca Glide para exibição da capa dos livros.
- 🔄 **Mensagens Interativas**: Uso de Toasts e AlertDialogs para feedback ao usuário.

## 📂 Estrutura do Projeto

O projeto está estruturado da seguinte forma:

```
LivrariaApp/
│── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/livrariaapp/
│   │   │   │   ├── CadastrarUsuarioActivity.kt
│   │   │   │   ├── CadastroLivroActivity.kt
│   │   │   │   ├── DetalheLivroActivity.kt
│   │   │   │   ├── EditarLivroActivity.kt
│   │   │   │   ├── EsqueciSenhaActivity.kt
│   │   │   │   ├── ItemLivroActivity.kt
│   │   │   │   ├── ListarLivrosActivity.kt
│   │   │   │   ├── Livro.kt
│   │   │   │   ├── LoginActivity.kt
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── Usuario.kt
│   │   │   ├── res/layout/
│   │   │   │   ├── activity_cadastrar_usuario.xml
│   │   │   │   ├── activity_cadastro_livro.xml
│   │   │   │   ├── activity_detalhe_livro.xml
│   │   │   │   ├── activity_editar_livro.xml
│   │   │   │   ├── activity_esqueci_senha.xml
│   │   │   │   ├── activity_listar_livros.xml
│   │   │   │   ├── activity_login.xml
│   │   │   ├── AndroidManifest.xml
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
```

## 🛠 Tecnologias Utilizadas

- 💻 **Linguagem**: Kotlin
- 📱 **Framework**: Android SDK
- 🎨 **UI**: ViewBinding
- 🗄 **Armazenamento**: SQLite (Banco de Dados Local)
- 🔄 **Bibliotecas**: Glide (para carregamento de imagens), RecyclerView (para listagem de livros)

## 📌 Melhorias Pendentes

- 🔒 Melhorar a autenticação do usuário.
- 📊 Implementar gráficos de estatísticas de livros cadastrados.
- 📡 Sincronização dos dados com um backend remoto.
- 🎨 Refinamento da interface do usuário para melhor experiência.

## ▶️ Como Executar

1. 📥 Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/livrariaapp.git
   ```
2. 🛠 Abra o projeto no Android Studio.
3. ▶️ Compile e execute no emulador ou dispositivo físico.

## 🤝 Contribuição

1. 🍴 Fork o repositório.
2. 🌿 Crie uma branch com sua feature:
   ```sh
   git checkout -b minha-feature
   ```
3. 💾 Commit suas alterações:
   ```sh
   git commit -m 'Adicionando nova funcionalidade'
   ```
4. ⬆️ Envie para o repositório:
   ```sh
   git push origin minha-feature
   ```
5. 🔄 Abra um Pull Request.

## 📜 Licença

Este projeto está sob a licença MIT - veja o arquivo LICENSE para mais detalhes.

---

## 📞 Contato

Feito com ❤️ por **Samuel Costa** 👋🏽

[![Linkedin Badge](https://img.shields.io/badge/-Samuel%20Costa-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/costa-samuel/)](https://www.linkedin.com/in/costa-samuel/)
[![Gmail Badge](https://img.shields.io/badge/-samu.ks@outlook.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:samu.ks@outlook.com)](mailto:samu.ks@outlook.com)
