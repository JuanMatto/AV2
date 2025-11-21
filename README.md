# 🎓 Sistema Acadêmico – Backend
Aplicação backend desenvolvida em **Spring Boot 3**, seguindo todos os requisitos da **Prática Avaliativa 2 (AV2)**, incluindo segurança, monitoramento, documentação, testes de carga e deploy.

---

# 📌 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **Spring Boot Actuator**
- **Springdoc OpenAPI (Swagger)**
- **H2 Database**
- **Prometheus**
- **Grafana**
- **Gatling** (ou JMeter)
- **Maven**

---

# 📁 Estrutura do Projeto
![img.png](img.png)

---

# 🧩 Modelagem das Entidades

## **Aluno**
- id
- nome
- email
- matricula
- Relacionamento **N:N** com Curso

## **Curso**
- id
- nome
- cargaHoraria
- Relacionamento **N:N** com Aluno

## **AlunoCurso**
- Entidade/tabela intermediária para relacionamento **N:N**

---
# ▶️ Como rodar o projeto localmente

### Pré-requisitos:
- Java 17+
- Maven 3.8+
- Git

### Passos:
```bash
git clone <URL_DO_REPOSITORIO>
cd backend
mvn spring-boot:run
```

---
# 🗄 Banco de Dados H2

A aplicação utiliza banco em memória.

Acesse o console:

📍 http://localhost:8080/h2-console

Configuração padrão:

JDBC URL: jdbc:h2:mem:academico

User: sa

Password: (vazio)

![img_1.png](img_1.png)

---

# 🔐 Autenticação – Spring Security

A aplicação possui autenticação configurada utilizando Spring Security.

Também é possível utilizar:

- Autenticação em memória
- Integração com Keycloak (sugerido pela prática)

User: admin
Password: admin

![img_2.png](img_2.png)
![img_3.png](img_3.png)
---

# 📄 Documentação da API — Swagger

O projeto possui documentação automática via Springdoc.

Acesse:

📘 Swagger UI:
---

http://localhost:8080/swagger-ui/index.html

![img_4.png](img_4.png)
![img_5.png](img_5.png)

📘 OpenAPI JSON:
---

http://localhost:8080/v3/api-docs

---

# 📡 Monitoramento — Actuator + Prometheus + Grafana

✔️ Actuator habilitado em:
---

/actuator
/actuator/health
/actuator/prometheus

![img_6.png](img_6.png)

✔️ Configurações no application.properties:
---

management.endpoints.web.exposure.include=health,info,prometheus
management.endpoint.prometheus.enabled=true