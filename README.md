# Localhealth 

# Integrantes

RM95854 - Eduarda Nicoli Cavalheiro
RM93535 - Erik Siarkowski Salafia
RM95396 - Ingrid Vieira de Oliveira

## Objetivo do projeto:
Este projeto propõe a criação de um sistema unificado para rastreamento e monitoramento de doenças, onde médicos podem registrar diagnósticos juntamente com informações de localização e data em um banco de dados centralizado. O objetivo principal é fornecer uma ferramenta eficaz para identificar e controlar surtos de doenças emergentes.

Ao centralizar esses dados vitais, o sistema permitiria um monitoramento detalhado, revelando padrões sobre quando e onde novos surtos estão ocorrendo. Essa abordagem não apenas simplificaria o rastreamento de doenças, mas também possibilitaria um controle mais rápido e eficiente desses surtos. Profissionais de saúde e autoridades teriam acesso a informações cruciais que lhes permitiriam agir de maneira proativa para prevenir e conter epidemias antes que se espalhem.

A visão por trás desse projeto é transformar a maneira como detectamos e respondemos a novas epidemias, promovendo um mundo mais seguro para todos. A centralização de dados e a colaboração entre profissionais de saúde e autoridades visam criar uma compreensão abrangente dos desafios da saúde pública e oferecer uma solução inovadora para lidar com esses desafios.

## Endpoints para Medico

<!-- Endereço do recurso -->
### Obter Médico todos os medicos
`GET`/api/v1/medico

Descrição: Retorna uma lista de todos os médicos cadastrados

### Obter Médico por ID
`GET` /api/doctors/{id}
Descrição: Retorna informações sobre um médico específico com base no ID.

### Criar um novo Médico
`POST`/api/v1/medico

Descrição: Cria um novo registro de médico


# Exemplo de corpo de requisição
```js
    {
        "crm": "123456",
        "nmMedico": "Dr. John Doe",
        "email": "john.doe@example.com",
        "senha": "senha"
    }
```
-------------------------
### Atualizar Médico
Descrição: Atualizar informações sobre um médico específico.

`PUT` /api/v1/medico/{id}


# Exemplo de corpo de requisição
```js
    {
 	
    "crm": "654321",
    "nmMedico": "Dra. Jane Smith",
    "email": "jane.smith@example.com",
    "senha": "novasenha"

   }
```


 `DELETE` /api/v1/medicos/{id}
Descrição: Excluir um registro de médico específico.

## Endpoints para Diagnósticos

### Obter Todos os Diagnósticos
`GET` /api/diagnostico

Descrição: Obter uma lista de todos os diagnósticos.
Obter Diagnóstico por ID

`GET` /api/v1/diagnostico/{id}

Descrição: Obter informações sobre um diagnóstico específico.

 ### Criar Novo Diagnóstico
`POST` /api/v1/diagnostico

Descrição: Criar um novo registro de diagnóstico.

**Campos da Requisição**

```js
    {
 	"nrCep": "12345678",
    "dtDiagnostico": "2023-01-01",
    "medicoId": 1,
    "doencaId": 1,
    "localizacao": {
        "nmCidade": "Cidade",
        "nmEstado": "Estado",
        "nmRua": "Rua",
        "nmBairro": "Bairro"
   }
}
```

### Atualizar Diagnóstico
`PUT`/api/v1/diagnostico/{id}

Descrição: Atualizar informações sobre um diagnóstico específico.
Corpo da Requisição:

**Campos da Requisição**

```js
    {
 	 "nrCep": "87654321",
  "dtDiagnostico": "2023-02-01",
  "medicoId": 2,
  "doencaId": 2,
  "localizacao": {
    "nmCidade": "Cidade Atualizada",
    "nmEstado": "Estado Atualizado",
    "nmRua": "Rua Atualizada",
    "nmBairro": "Bairro Atualizado"
   }
}
```
### Excluir Diagnóstico
`DELETE` /api/v1/diagnostico/{id}
Descrição: Excluir um registro de diagnóstico específico.

## Endpoints para Doenças

### Obter Todas as Doenças
`GET` /api/v1/doenca
Descrição: Obter uma lista de todas as doenças

### Obter Doença por ID
`GET` /api/v1/doenca/{id}
Descrição: Obter informações sobre uma doença específica.

### Criar Nova Doença
`POST` /api/doencas

Descrição: Criar um novo registro de doença

**Corpo da Requisição**
```js
    {
    "nmDoenca": "Influenza",
    "dsSintomas": "Febre, tosse, fadiga"
   }
```

### Atualizar Doença
`PUT`  /api/v1/doencas/{id}

Descrição: Atualizar informações sobre uma doença específica.

**Corpo da Requisição**
```js
   {
  "nmDoenca": "Resfriado Comum",
  "dsSintomas": "Coriza, espirros"
    }
```
### Deletar Doença
`DELETE` /api/v1/doencas/{id}
Descrição: Excluir um registro de doença específica.

## Endpoints para Localizações

### Obter Todas as Localizações

`GET` /api/v1/localizacao
Descrição: Obter uma lista de todas as localizações.

### Obter Localização por ID

`GET` /api/localizacoes/{id}

Descrição: Obter informações sobre uma localização específica.

### Criar Nova Localização

 `POST` /api/v1/localizacoes

Descrição: Criar um novo registro de localização.

**Corpo da Requisição**
```js
   {
    "nmCidade": "Cidade",
    "nmEstado": "Estado",
    "nmRua": "Rua",
    "nmBairro": "Bairro"
    }
```
### Atualizar Localização

 `PUT` /api/localizacoes/{id}

Descrição: Atualizar informações sobre uma localização específica.
**Corpo da Requisição**
```js
   {
    "nmCidade": "Cidade Atualizada",
    "nmEstado": "Estado Atualizado",
    "nmRua": "Rua Atualizada",
    "nmBairro": "Bairro Atualizado"
    }
```

### Excluir LocalizaçãoR
`DELETE` /api/V1/localizacoes/{id}

Descrição: Excluir um registro de localização específica
