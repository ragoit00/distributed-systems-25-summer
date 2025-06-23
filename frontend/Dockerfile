# STAGE 1: Build
FROM node:18-alpine AS build

# Arbeitsverzeichnis im Container
WORKDIR /app

# Nur package.json kopieren und deps installieren
COPY package*.json ./
RUN npm install

# Restliche Dateien kopieren und Vue-Projekt bauen
COPY . .
RUN npm run build

# STAGE 2: Serve mit nginx
FROM nginx:alpine

# Nginx Standard HTML-Verzeichnis bereinigen
RUN rm -rf /usr/share/nginx/html/*

# Kopiere gebaute Dateien von vorherigem Build
COPY --from=build /app/dist /usr/share/nginx/html

# Eigene Nginx-Konfiguration hinzufügen (für SPA routing)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Offen legen des Ports
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
