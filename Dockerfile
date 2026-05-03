# Build stage
FROM node:22-alpine AS build
WORKDIR /app

# Copiar archivos de dependencias
COPY package*.json ./
RUN npm install

# Copiar el resto del código
COPY . .

# Construir la aplicación
RUN npm run build

# Production stage
FROM nginx:alpine
# Copiar los archivos generados en la fase de build
COPY --from=build /app/dist /usr/share/nginx/html

# Exponer el puerto 80 para Nginx
EXPOSE 80

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
