# 🚀 API RESTful de Lista de Tarefas (To-Do List)

Este projeto demonstra uma arquitetura **Cloud-Native completa**, utilizando **Java/Spring Boot** para o backend e **Kubernetes** para orquestração. O objetivo principal é provar o domínio do ciclo de vida completo de uma aplicação moderna: **Código -> Contêiner -> Nuvem**.

## 🛠️ Resumo Técnico:

| Componente | Tecnologia | Valor para a Arquitetura |
| :--- | :--- | :--- |
| **Backend** | Java 21, Spring Boot, JPA | Microsserviço robusto, utilizando o padrão de mercado para construção de APIs REST (CRUD). |
| **Container** | Dockerfile (Multi-Stage Build) | Garante que o contêiner final seja leve e otimizado, pronto para qualquer ambiente Linux. |
| **Orquestração** | Kubernetes (Deployment) | Garante a **Alta Disponibilidade** (rodando 2 réplicas) e a **auto-recuperação** da aplicação. |
| **Acesso** | Kubernetes (Service) | Cria um ponto de entrada estável e balanceado de carga para as 2 cópias da aplicação. |

---

## 💻 Passos para Executar Localmente

Para rodar este projeto, você precisará ter o **Docker Desktop**, com Kubernetes ativado e o **kubectl** instalados.

### 1. Preparação (Build e Publicação da Imagem)

O **Build** compila o Java e o **Push** envia a imagem para o Docker Hub, de onde o Kubernetes irá puxá-la.

**(Execute na pasta raiz do projeto, onde está o Dockerfile):**


 Lembrete: Troque 'natasha0600' pelo SEU usuário do Docker Hub!
```bash

# 1. Construir a imagem (Containerização do código Java)
docker build --no-cache -t natasha0600/todo-java-k8s:v1 --file Dockerfile ./todolist

# 2. Enviar a imagem para o Docker Hub (Distribuição)
docker push natasha0600/todo-java-k8s:v1

-O comando kubectl apply lê o arquivo k8s.yaml e cria a infraestrutura no cluster local
# 3. Aplicar o manifesto de deploy (Cria Deployment e Service)
kubectl apply -f k8s.yaml

-Para acessar a aplicação que está rodando dentro do cluster K8s, é necessário abrir um túnel de conexão.
# 4. Abrir o túnel de acesso (Port-Forward)
kubectl port-forward service/todo-list-service 8080:8080

-Com o túnel ativo, acesse o endpoint:
Busca (GET): http://localhost:8080/api/tarefas (Irá retornar uma lista JSON)
