FROM node:22-alpine as builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build


FROM nginx:alpine

RUN rm -fr /usr/share/nginx/html/*
COPY --from=builder /app/dist/view-service/browser /usr/share/nginx/html

COPY ngnix.conf.template /etc/nginx/templates/default.conf.template

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
