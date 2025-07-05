# distributed-systems-25-summer
# 🛒 Shopping App (Vue.js + Micronaut)

---
# Projektübersicht

Dieses Projekt besteht aus:
- einem Backend (Micronaut (Java))
- einem Frontend (Nuxt 3 (Vue.js SSR))
- Docker und Docker Compose
- Kubernetes manifest

---

## Technologien
- Micronaut
- Vue.js
- Docker
- Docker Compose
- Swagger

---

## Anwendung starten
...
1. Make sure the file `gradlew` is set to `LF` instead of `CRLF` in VSCode (in the bottom right when `gradlew` is opened)
2. `docker compose run --build` in root directory
3. Frontend available at http://localhost:8081/
4. Backend available at e.g. http://localhost:8080/items/
---

## Projektstruktur
...

---

## 12-Faktor App Prinzipien

| Prinzip | Umsetzung |
|:---|:---|
| **1. Codebase** | Eine einzige Codebase in Git |
| **2. Dependencies** | Definiert in `package.json`, installiert mit `npm install` |
| **3. Config** | Nutzung von `.env` Dateien für Konfiguration |
| **4. Backing Services** | API ist unabhängig von Services wie DB |
| **5. Build, Release, Run** | Dockerfiles für saubere Trennung |
| **6. Processes** | Applikation läuft als stateless Prozesse |
| **7. Port binding** | Ports werden dynamisch über Docker gemappt |
| **8. Concurrency** | Mehrere Container möglich |
| **9. Disposability** | Container sind schnell start-/stoppbar |
| **10. Dev/Prod Parity** | Lokal und Deployment sind fast identisch (Dockerized) |
| **11. Logs** | Logs über Standard Output |
| **12. Admin processes** | Keine benötigt |

---

## Kubernetes Deployment

Das Projekt kann mit Kubernetes deployed werden. 
