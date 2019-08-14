# Project-Bot

Para este projeto é necessário utilizar o banco de dados MongoDB para que seja possivel criar o usuario no bot e gerenciar as mensagens.

# Versões utilizadas
Java "1.8.0_201"<br>
Java(TM) SE Runtime Environment (build 1.8.0_201-b09)<br>
Eclipse 2019-03<br>
Apache-maven-3.6.0<br>
MongoDB version v4.0.10

<h1> EndPoints Disponiveis </h1>

<h2>Criar um usuario no bot</h2>

<h2>POST</h2>
http://localhost:8080/bots
<p>Request Body</p>

```json
{
	"id": "36b9f842-ee97-11e8-9443-0242ac120002",
	"name": "Jamelao"
}
```

<p>Response Body</p>

```json
{
	"id": "36b9f842-ee97-11e8-9443-0242ac120002",
	"name": "Jamelao"
}
```

<h2>Recuperar o usuario do bot por id</h2>

<h2>GET</h2>
http://localhost:8080/bots/36b9f842-ee97-11e8-9443-0242ac120002
<p>Response Body</p>

```json
{
    "id": "36b9f842-ee97-11e8-9443-0242ac120002",
    "name": "Jamelao"
}
```

<h2>Criar a mensagem</h2>

<h2>POST</h2>
http://localhost:8080/messages
<p>Request Body</p>

```json
{
  "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
  "timestamp": "2018-11-16T23:30:52.6917722Z",
  "from": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
  "to": "36b9f842-ee97-11e8-9443-0242ac120002",
  "text": "Gostaria de saber meu saldo do cartão?"
}
```

<p>Response Body</p>

```json
{
    "id": "ed71ab70-6392-4d0e-99d1-b2b8b5f9ef91",
    "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
    "timestamp": "2018-11-16T23:30:52.6917722",
    "from": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
    "to": "36b9f842-ee97-11e8-9443-0242ac120002",
    "text": "Gostaria de saber meu saldo do cartão?"
}
```

<h2>Recuperar mensagem por Id</h2>

<h2>GET</h2>
http://localhost:8080/messages/ed71ab70-6392-4d0e-99d1-b2b8b5f9ef91
<p>Response Body</p>

```json
{
    "id": "ed71ab70-6392-4d0e-99d1-b2b8b5f9ef91",
    "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
    "timestamp": "2018-11-16T23:30:52.6917722",
    "from": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
    "to": "36b9f842-ee97-11e8-9443-0242ac120002",
    "text": "Gostaria de saber meu saldo do cartão?"
}
```

<h2>Recuperar as mensagens de uma conversa</h2>

<h2>GET</h2>
http://localhost:8080/messages?conversationId=7665ada8-3448-4acd-a1b7-d688e68fe9a1
<p>Response Body</p>

```json
[
  {
    "id": "d681f760-2b6a-4294-97dd-77ebe5c30798",
    "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
    "timestamp": "2018-11-16T23:30:52.691",
    "from": "36b9f842-ee97-11e8-9443-0242ac120002",
    "to": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
    "text": "Oi! Como posso te ajudar?"
  },
  {
    "id": "e3abe453-a223-431a-83a4-3944f4eaa63e",
    "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
    "timestamp": "2018-11-16T23:30:52.691",
    "from": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
    "to": "36b9f842-ee97-11e8-9443-0242ac120002",
    "text": "Gostaria de saber o valor da minha fatura?"
  }
]
```

