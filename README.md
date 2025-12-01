# City Events JavaFX

Um sistema simples de gerenciamento de eventos em JavaFX, permitindo cadastrar usuários, criar eventos e visualizar listas de eventos de forma organizada. Este projeto usa arquivos de texto (`.txt`) como armazenamento, sem banco de dados.

---

## Funcionalidades

### Usuários
- Cadastro de usuário com:
    - Nome
    - E-mail
    - Senha (armazenada como MD5)
- Validação para e-mails duplicados.
- Login e logout de usuários.

### Eventos
- Criação de eventos com:
    - Título
    - Data
    - Descrição
    - Categoria (Cultura, Esporte, Educação, Saúde, Tecnologia, Outro)
- Visualização de eventos em lista organizada, mostrando:
    - Título
    - Data
    - Categoria
    - Descrição
- Eventos salvos em `data/events.txt`.

### Testes
- Testes JUnit 4 para:
    - Cadastro de usuário e verificação da senha MD5.
    - Criação de eventos e verificação se foram salvos corretamente.

---


---

## Como Rodar

1. Abrir o projeto no IntelliJ IDEA.
2. Compile e execute a classe principal (`Main.java`).
3. Os arquivos `users.txt` e `events.txt` serão criados automaticamente dentro da pasta `data/`.
4. Navegue pelas telas:
    - Registro de usuários
    - Criação de eventos
    - Lista de eventos

---

## Testes

- Os testes estão no pacote `test/` e usam **JUnit 4**.
- Testes disponíveis:
    - `RegisterSaveJUnit4Test.java` → verifica se o usuário é salvo e a senha é armazenada como MD5.
    - `FileEventDaoTest.java` → verifica se os eventos são salvos corretamente no arquivo.
- Para rodar:
    - No IntelliJ, clique com o botão direito no arquivo de teste → Run.

---

## Observações

- Este projeto **não usa banco de dados**, apenas arquivos de texto para persistência.
- Senhas são armazenadas como **hash MD5** (não recomendável para sistemas de produção, apenas para fins de aprendizado).
- Categorias de eventos são fixas, mas podem ser alteradas no `CreateEventController`.

---

## Contato





